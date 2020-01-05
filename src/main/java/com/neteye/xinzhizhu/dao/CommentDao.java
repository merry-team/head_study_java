package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.CommentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户评论
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:41:38
 */
@Mapper
public interface CommentDao {

	CommentDO get(Integer commentId);
	
	List<CommentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CommentDO comment);
	
	int update(CommentDO comment);
	
	int remove(Integer comment_id);
	
	int batchRemove(Integer[] commentIds);
}
