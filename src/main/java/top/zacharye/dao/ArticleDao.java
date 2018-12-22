package top.zacharye.dao;

import top.zacharye.entity.Article;
import top.zacharye.entity.Category;

import java.util.List;

public interface ArticleDao {
    int addCategory(Category category);
    List<Category> findCategories(String login_user);
    int addArticle(Article article);
    int editArticle(Article article);
    List<Article> findArticlesByLoginUser(String login_user);
    List<Article> findAllSimplifiedArticles();
    Article findArticleById(String id);
}
