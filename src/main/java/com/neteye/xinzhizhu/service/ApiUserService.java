package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.dao.ApiUserMapper;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.utils.RRException;
import com.neteye.xinzhizhu.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ApiUserService {
    @Autowired
    private ApiUserMapper userDao;

    public UserVo queryObject(Long userId) {
        return userDao.queryObject(userId);
    }

    public UserVo queryByOpenId(String openId) {
        return userDao.queryByOpenId(openId);
    }

    public List<UserVo> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    public void save(String mobile, String password) {
        UserVo user = new UserVo();
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setRegister_time(new Date());
        userDao.save(user);
    }

    public void save(UserVo userVo) {
        userDao.save(userVo);
    }

    public void update(UserVo user) {
        userDao.update(user);
    }

    public void delete(Long userId) {
        userDao.delete(userId);
    }

    public void deleteBatch(Long[] userIds) {
        userDao.deleteBatch(userIds);
    }

    public UserVo queryByMobile(String mobile) {
        return userDao.queryByMobile(mobile);
    }

    public long login(String mobile, String password) {
        UserVo user = queryByMobile(mobile);
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }

        return user.getUserId();
    }
}
