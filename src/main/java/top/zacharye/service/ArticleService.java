package top.zacharye.service;

import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.util.Result;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    Result addCategory(Category category);
    List<Category> findCategories(String username);
    Result editArticle(Article article);
    Result findAllSimplifiedArticles();
    Result findAllSimplifiedArticles(Map<String,Object> mapData);
    Article findArticleById(String id);
    List<Article> findHotArticles();
}
