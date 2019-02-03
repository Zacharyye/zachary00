package top.zacharye.dao;

import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.entity.Comment;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    int addCategory(Category category);
    List<Category> findCategories(String login_user);
    int addArticle(Article article);
    int editArticle(Article article);
    List<Article> findArticlesByLoginUser(String login_user);
    List<Article> findAllSimplifiedArticles();
    List<Article> findAllSimplifiedArticlesByCondition(Map<String,Object> mapData);
    Map<String,Object> calculateTheAmountOfRecords(Map<String,Object> mapData);
    Article findArticleById(String id);
    Article findArticleByParams(Map<String,Object> mapData);
    void updateArticleInfo(String id);
    List<Article> findHotArticles();
    int addComment(Comment comment);
    List<Comment> findCommentsByArticleId(String id);
}
