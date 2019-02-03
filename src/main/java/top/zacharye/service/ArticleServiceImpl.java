package top.zacharye.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zacharye.dao.ArticleDao;
import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.entity.Comment;
import top.zacharye.util.Result;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    /**
     * 添加分类
     * @param category
     * @return
     */
    public Result addCategory(Category category) {
        int num = articleDao.addCategory(category);
        if(num == 0){
            return new Result().failure();
        }
        return new Result().success(category);
    }

    /**
     * 查询已创建的分类信息
     * @param username
     * @return
     */
    public List<Category> findCategories(String username){
        return articleDao.findCategories(username);
    }

    /**
     * 编辑或添加文章
     * @param article
     * @return
     */
    public Result editArticle(Article article) {
        Result result = null;
        int num = 0;
        if(article.getId() == null){
            //新增文章
            num = articleDao.addArticle(article);
        } else {
            //修改文章信息
            num = articleDao.editArticle(article);
        }
        if(num > 0) {
            result = new Result().success();
        } else {
            result = new Result().failure();
        }
       return result;
    }

    /**
     * 查找显示全部文章
     * @param
     * @return
     */
    public Result findAllSimplifiedArticles(){
        List<Article> articles = articleDao.findAllSimplifiedArticles();
        return new Result().success(articles);
    }

    public Result findAllSimplifiedArticles(Map<String, Object> mapData) {
        List<Article> articles = articleDao.findAllSimplifiedArticlesByCondition(mapData);
        Map<String,Object> result_cal = articleDao.calculateTheAmountOfRecords(mapData);
        Map<String,Object> result = new HashMap<String, Object>();
        List<Article> hot_articles = articleDao.findHotArticles();
        result.put("articles",articles);
        result.put("r_cal",result_cal);
        result.put("hot_articles",hot_articles);
        return new Result().success(result);
    }

    /**
     * 查找文章：根据指定id
     * @param id
     * @return
     */
    public Article findArticleById (String id) {
        articleDao.updateArticleInfo(id);
        return articleDao.findArticleById(id);
    }

    /**
     * 根据给定参数查找文章
     * @param mapData
     * @return
     */
    public Article findArticleByParams(Map<String, Object> mapData) {
        return null;
    }

    /**
     * 查询热门文章
     * @return
     */
    public List<Article> findHotArticles() {
        return articleDao.findHotArticles();
    }

    /**
     * 添加评论
     * @param dataMap
     * @return
     */
    public Result addComment(Map<String, Object> dataMap) {
        Result result = null;
        String author = dataMap.get("commentAuthor") + "";
        String email = dataMap.get("commentEmail") + "";
        String article_id = dataMap.get("articleId") + "";
        String content = dataMap.get("commentContent") + "";
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setEmail(email);
        comment.setArticle_id(Integer.valueOf(article_id));
        comment.setContent(content);
        int num = articleDao.addComment(comment);
        if(num > 0){
            result = new Result().success();
        } else {
            result = new Result().failure();
        }
        return result;
    }

    /**
     * 根据文章id查询其所有评论
     * @param id
     * @return
     */
    public List<Comment> findCommentsByArticleId(String id) {
        return articleDao.findCommentsByArticleId(id);
    }

    public Result findCommentsByArticleIdV2(String id) {
        List<Comment> comments = articleDao.findCommentsByArticleId(id);
        return new Result().success(comments);
    }

}
