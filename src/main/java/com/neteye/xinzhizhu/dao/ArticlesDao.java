package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.ArticlesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:28:15
 */
@Mapper
public interface ArticlesDao {

	ArticlesDO get(Integer articleId);
	
	List<ArticlesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ArticlesDO articles);
	
	int update(ArticlesDO articles);
	
	int remove(Integer article_id);
	
	int batchRemove(Integer[] articleIds);
	
	int updateplus(ArticlesDO articles);

}
