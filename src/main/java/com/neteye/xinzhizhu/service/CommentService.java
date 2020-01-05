package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.CommentDO;

import java.util.List;
import java.util.Map;

/**
 * 用户评论
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:41:38
 */
public interface CommentService {
	
	CommentDO get(Integer commentId);
	
	List<CommentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommentDO comment);
	
	int update(CommentDO comment);
	
	int remove(Integer commentId);
	
	int batchRemove(Integer[] commentIds);
}
