package top.zacharye.dao;

import top.zacharye.entity.Category;

import java.util.List;

public interface ArticleDao {
    int addCategory(Category category);
    List<Category> findCategories(String login_user);
}
