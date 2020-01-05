package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.ArticlesDao;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.service.ArticlesService;



@Service
public class ArticlesServiceImpl implements ArticlesService {
	@Autowired
	private ArticlesDao articlesDao;
	
	@Override
	public ArticlesDO get(Integer articleId){
		return articlesDao.get(articleId);
	}
	
	@Override
	public List<ArticlesDO> list(Map<String, Object> map){
		return articlesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return articlesDao.count(map);
	}
	
	@Override
	public int save(ArticlesDO articles){
		return articlesDao.save(articles);
	}
	
	@Override
	public int update(ArticlesDO articles){
		return articlesDao.update(articles);
	}
	
	@Override
	public int remove(Integer articleId){
		return articlesDao.remove(articleId);
	}
	
	@Override
	public int batchRemove(Integer[] articleIds){
		return articlesDao.batchRemove(articleIds);
	}
	
	@Override
	public int updateplus(ArticlesDO articles){
		return articlesDao.updateplus(articles);
	}
}
