package com.example.redis.service;

import com.example.redis.bean.AppUserBean;
import com.example.redis.dao.AppUserDao;
import com.example.redis.utils.RedisUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppUserService {

    Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Resource
    private AppUserDao appUserDao;
    @Resource
    private RedisUtils redisUtils;

    public AppUserBean getAppUserById(Long appUserId) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        AppUserBean appUserBean = (AppUserBean)redisUtils.get(appUserId.toString());
        if(appUserBean != null) {
            logger.info("The data got from redis");
            return appUserBean;
        }
        logger.info("The data got from mysql");
        appUserBean = appUserDao.getAppUserById(appUserId);
        redisUtils.set(appUserId.toString(), appUserBean);
        return appUserBean;
    }

    public boolean updateAppUser(AppUserBean appUserBean) {
        return redisUtils.update(appUserBean.getId().toString(), appUserBean);
    }

    public boolean delAppUserById(Long appUserId) {
        return redisUtils.delete(appUserId.toString());
    }
}
