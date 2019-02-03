package top.zacharye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Text;
import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.entity.Comment;
import top.zacharye.service.ArticleService;
import top.zacharye.util.RequestUtil;
import top.zacharye.util.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 添加文章
     * @return
     */
    @RequestMapping(value="/editArticle",method = RequestMethod.POST)
    @ResponseBody
    public Result addArticle(HttpServletRequest request,HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String login_user = request.getSession().getAttribute("username") + "";
        Integer article_id = request.getParameter("article_id") == null ? null : Integer.parseInt(request.getParameter("article_id") + "");
        String title = request.getParameter("title") + "";
        String content =  request.getParameter("content");
        Blob c_blob = new SerialBlob(content.getBytes());
        Integer category = request.getParameter("category") == null ? null : Integer.parseInt(request.getParameter("category"));
        String status = request.getParameter("status");
        Integer isAllowComment = request.getParameter("isAllowComment") == null ? null : Integer.parseInt(request.getParameter("isAllowComment"));
        Article article = new Article(article_id,title,content,category,status,isAllowComment,login_user);
        return articleService.editArticle(article);
    }

    /**
     * 查找文章
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/findAllSimplifiedArticles", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Result findAllSimplifiedArticles(HttpServletRequest request,HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        int start = currentPage == null ? 1 : ((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
        int end = currentPage == null ? 1 : (Integer.valueOf(currentPage) * Integer.valueOf(pageSize));
        map.put("start",start);
        map.put("end",end);
        map.put("pageSize",Integer.valueOf(pageSize));
        return articleService.findAllSimplifiedArticles(map);
    }

    /**
     * 显示文章详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/showDetail/{articleId}",method = {RequestMethod.POST,RequestMethod.GET})
    public String showArticleDetail(HttpServletRequest request,HttpServletResponse response,@PathVariable String articleId) {
        //TODO 查找文章详情
        Article article = articleService.findArticleById(articleId);
        List<Article> articles = articleService.findHotArticles();
        request.setAttribute("article",article);
        request.setAttribute("hotArticles",articles);
        return "articleIndex";
    }

    /**
     * 添加评论
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addComment",method = {RequestMethod.POST})
    @ResponseBody
    public Result addComment(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = RequestUtil.getAllRequestParameters(request);
        return articleService.addComment(map);
    }

    /**
     * 查找评论
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findCommentsByArticleId/{articleId}" , method = {RequestMethod.POST})
    @ResponseBody
    public Result findCommentByArticleId (HttpServletRequest request,HttpServletResponse response,@PathVariable String articleId){
        return articleService.findCommentsByArticleIdV2(articleId);
    }

}
