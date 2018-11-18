package top.zacharye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zacharye.entity.Category;
import top.zacharye.service.ArticleService;
import top.zacharye.util.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 添加类别
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST)
    @ResponseBody
    public Result addCategoryName(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        String categoryName = request.getParameter("categoryName");
        String login_user = (String) request.getSession().getAttribute("username");
        Category category = new Category(login_user,categoryName);
        return articleService.addCategory(category);
    }
}
