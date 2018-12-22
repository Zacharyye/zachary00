package top.zacharye.service;

import top.zacharye.entity.Article;
import top.zacharye.entity.Category;
import top.zacharye.util.Result;
import java.util.List;

public interface ArticleService {
    Result addCategory(Category category);
    List<Category> findCategories(String username);
    Result editArticle(Article article);
    Result findAllSimplifiedArticles();
    Article findArticleById(String id);
}
