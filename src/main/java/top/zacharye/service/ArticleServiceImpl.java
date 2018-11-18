package top.zacharye.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zacharye.dao.ArticleDao;
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
}
