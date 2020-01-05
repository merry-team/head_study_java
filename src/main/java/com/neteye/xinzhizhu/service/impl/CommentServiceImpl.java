package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.ArticlesDao;
import com.neteye.xinzhizhu.dao.CommentDao;
import com.neteye.xinzhizhu.dao.CourseDao;
import com.neteye.xinzhizhu.dao.FmsDao;
import com.neteye.xinzhizhu.dao.VodsDao;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.service.CommentService;



@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
    @Autowired
    private ArticlesDao articlesDao; //1
    @Autowired
    private CourseDao courseDao; //2
    @Autowired
    private FmsDao fmsDao; //3
    @Autowired
    private VodsDao vodsDao; //4
	@Override
	public CommentDO get(Integer commentId){
		return commentDao.get(commentId);
	}
	
	@Override
	public List<CommentDO> list(Map<String, Object> map){
		return commentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commentDao.count(map);
	}
	
	@Override
	public int save(CommentDO comment){
		if(comment.getCommentType().equals(1)) {
			ArticlesDO articles = new ArticlesDO();
			articles.setArticleId(comment.getObjectId());
			articles.setCommentCount(1);
			articlesDao.updateplus(articles);
		}else if(comment.getCommentType().equals(2)) {
//			CourseDO course = new CourseDO();
//			course.setCommentCount(1);
//			course.setCourseId(comment.getObjectId());
//			courseDao.updateplus(course);
		}else if(comment.getCommentType().equals(3)) {
			FmsDO fms = new FmsDO();
			fms.setCommentCount(1);
			fms.setFmId(comment.getObjectId());
			fmsDao.updateplus(fms);
		}else if(comment.getCommentType().equals(4)) {
			VodsDO vods = new VodsDO();
			vods.setCommentCount(1);
			vods.setVodId(comment.getObjectId());
			vodsDao.updateplus(vods);
		}
		return commentDao.save(comment);
	}
	
	@Override
	public int update(CommentDO comment){
		return commentDao.update(comment);
	}
	
	@Override
	public int remove(Integer commentId){
		return commentDao.remove(commentId);
	}
	
	@Override
	public int batchRemove(Integer[] commentIds){
		return commentDao.batchRemove(commentIds);
	}
	
}
