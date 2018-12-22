package top.zacharye.entity;

import java.sql.Timestamp;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer category;
    private String category_name;
    private String status;
    private Integer isAllowComment;
    private String login_user;
    private Timestamp created_at;

    public Article(){}

    public Article(Integer id, String title, String content, Integer category, String status, Integer isAllowComment, String login_user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.isAllowComment = isAllowComment;
        this.login_user = login_user;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsAllowComment() {
        return isAllowComment;
    }

    public void setIsAllowComment(Integer isAllowComment) {
        this.isAllowComment = isAllowComment;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
