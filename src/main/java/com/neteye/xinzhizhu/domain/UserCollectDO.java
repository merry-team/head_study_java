package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户收藏详细
 *
 * @author zhengbigbig
 */
public class UserCollectDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer collectId;
    //评论类型1：文章；2：课程；3：fm；4：vod
    private Integer collectType;
    // 不同类型对应的id
    private Integer objectId;
    private Date collectCreateTime;
    private String title;
    private Object categoryId;
    private String content;
    private String imageUrl;
    private String author;
    private Integer readCount;
    private Integer collectCount;
    private Integer commentCount;
    private String fileUrl;
    private Object vodTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Date getCollectCreateTime() {
        return collectCreateTime;
    }

    public void setCollectCreateTime(Date collectCreateTime) {
        this.collectCreateTime = collectCreateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Object getVodTime() {
        return vodTime;
    }

    public void setVodTime(Object vodTime) {
        this.vodTime = vodTime;
    }
}
