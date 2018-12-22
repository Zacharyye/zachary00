package top.zacharye.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zacharye.dao.ArticleDao;
import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.util.Result;
import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 查找文章：根据指定id
     * @param id
     * @return
     */
    public Article findArticleById (String id) {
        return articleDao.findArticleById(id);
    }
}
