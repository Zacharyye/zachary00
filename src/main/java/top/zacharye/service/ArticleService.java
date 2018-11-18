package top.zacharye.service;

import top.zacharye.entity.Category;
import top.zacharye.util.Result;
import java.util.List;

public interface ArticleService {
    Result addCategory(Category category);
    List<Category> findCategories(String username);
}
