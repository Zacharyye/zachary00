package top.zacharye.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.zacharye.entity.Category;
import top.zacharye.service.ArticleService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private ArticleService articleService;


    @RequestMapping(value="/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录界面
        return "login";
    }

    @RequestMapping(value="/toIndex",method = RequestMethod.POST)
    public String toIndex(HttpServletRequest request,HttpServletResponse response){
        //登录方法
//        System.out.println("toIndex");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession(true);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            if(!subject.isAuthenticated()){
                return "login";
            }
            Object randomCode = session.getAttribute("randomCode");
            if(randomCode == null){
                throw new RuntimeException();
            } else if(!verifycode.equals(randomCode)) {
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("login_error","3");
                return "login";
            }
            session.setAttribute("username",username);
        } catch (UnknownAccountException e) {
            //无此账号
            request.setAttribute("login_error","1");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码错误
            request.setAttribute("login_error","2");
            request.setAttribute("username",username);
            return "login";
        } catch (Exception e){
            return "login";
        }
        //查询相关数据
        List<Category> categoryList = articleService.findCategories(username);
        request.setAttribute("categoryList",categoryList);
        return "userIndex";
    }

    @RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
    public String showIndex(HttpServletRequest request,HttpServletResponse response){
        return "index";
    }

    @RequestMapping(value = "/userIndex",method = {RequestMethod.POST,RequestMethod.GET})
    public String showUserIndex(HttpServletRequest request,HttpServletResponse response){
        return "userIndex";
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        HttpSession session = request.getSession(false);
        request.removeAttribute("username");
        if(session != null)
            session.removeAttribute("username");
        return "userIndex";
    }


}
