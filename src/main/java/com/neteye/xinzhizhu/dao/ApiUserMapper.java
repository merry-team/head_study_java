package com.neteye.xinzhizhu.dao;


import com.neteye.xinzhizhu.entity.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:22:06
 */
public interface ApiUserMapper extends BaseDao<UserVo> {

    UserVo queryByMobile(String mobile);

    UserVo queryByOpenId(@Param("openId") String openId);

}
