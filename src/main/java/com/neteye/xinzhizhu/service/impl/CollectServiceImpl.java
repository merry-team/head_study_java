package com.neteye.xinzhizhu.service.impl;

import com.neteye.xinzhizhu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.ArticlesDao;
import com.neteye.xinzhizhu.dao.CollectDao;
import com.neteye.xinzhizhu.dao.CourseDao;
import com.neteye.xinzhizhu.dao.FmsDao;
import com.neteye.xinzhizhu.dao.VodsDao;
import com.neteye.xinzhizhu.service.CollectService;




@Service
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectDao collectDao;
    @Autowired
    private ArticlesDao articlesDao; //1
    @Autowired
    private CourseDao courseDao; //2
    @Autowired
    private FmsDao fmsDao; //3
    @Autowired
    private VodsDao vodsDao; //4
	@Override
	public CollectDO get(Integer collecttId){
		return collectDao.get(collecttId);
	}
	
	@Override
	public List<CollectDO> list(Map<String, Object> map){
		return collectDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return collectDao.count(map);
	}

	@Override
	public int save(CollectDO collect){
		if(collect.getCollectType().equals(1)) {
			ArticlesDO articles = new ArticlesDO();
			articles.setArticleId(collect.getObjectId());
			articles.setCollectCount(1);
			articlesDao.updateplus(articles);
		}else if(collect.getCollectType().equals(2)) {
			CourseDO course = new CourseDO();
			course.setCollectCount(1);
			course.setCourseId(collect.getObjectId());
			courseDao.updateplus(course);
		}else if(collect.getCollectType().equals(3)) {
			FmsDO fms = new FmsDO();
			fms.setCollectCount(1);
			fms.setFmId(collect.getObjectId());
			fmsDao.updateplus(fms);
		}else if(collect.getCollectType().equals(4)) {
			VodsDO vods = new VodsDO();
			vods.setCollectCount(1);
			vods.setVodId(collect.getObjectId());
			vodsDao.updateplus(vods);
		}
		return collectDao.save(collect);
	}
	
	@Override
	public int update(CollectDO collect){
		int status = 1;
		if(collect.getStatus().equals(-1)) {
			status = -1;
		}
		if(collect.getCollectType().equals(1)) {
			ArticlesDO articles = new ArticlesDO();
			articles.setArticleId(collect.getObjectId());
			articles.setCollectCount(status);
			articlesDao.updateplus(articles);
		}else if(collect.getCollectType().equals(2)) {
			CourseDO course = new CourseDO();
			course.setCollectCount(status);
			course.setCourseId(collect.getObjectId());
			courseDao.updateplus(course);
		}else if(collect.getCollectType().equals(3)) {
			FmsDO fms = new FmsDO();
			fms.setCollectCount(status);
			fms.setFmId(collect.getObjectId());
			fmsDao.updateplus(fms);
		}else if(collect.getCollectType().equals(4)) {
			VodsDO vods = new VodsDO();
			vods.setCollectCount(status);
			vods.setVodId(collect.getObjectId());
			vodsDao.updateplus(vods);
		}
		return collectDao.update(collect);
	}
	
	@Override
	public int remove(Integer collecttId){
		return collectDao.remove(collecttId);
	}
	
	@Override
	public int batchRemove(Integer[] collecttIds){
		return collectDao.batchRemove(collecttIds);
	}

	@Override
	public int read(CollectDO collect){
		if(collect.getCollectType().equals(1)) {
			ArticlesDO articles = new ArticlesDO();
			articles.setArticleId(collect.getObjectId());
			articles.setReadCount(1);
			articlesDao.updateplus(articles);
		}else if(collect.getCollectType().equals(3)) {
			FmsDO fms = new FmsDO();
			fms.setReadCount(1);
			fms.setFmId(collect.getObjectId());
			fmsDao.updateplus(fms);
		}else if(collect.getCollectType().equals(4)) {
			
			VodsDO vods = new VodsDO();
			vods.setReadCount(1);
			vods.setVodId(collect.getObjectId());
			vodsDao.updateplus(vods);
			//视频增加了 修改视频对应的课程的点击率
			vods = vodsDao.get(collect.getObjectId());
			CourseDO course = new CourseDO();
			course.setReadCount(1);
			course.setCourseId(vods.getCourseId());
			courseDao.updateplus(course);
		}
		return 1;
	}

	@Override
	public List<UserCollectDO> getCollectsByUserId(Map<String, Object> map) {
		return collectDao.getCollectsByUserId(map);
	}

	@Override
	public int countByUserId(Map<String, Object> map) {
		return collectDao.countByUserId(map);
	}

	@Override
	public CollectDO getCollectByUniqueId(Map<String, Object> map) {
		return collectDao.getCollectByUniqueId(map);
	}
}
