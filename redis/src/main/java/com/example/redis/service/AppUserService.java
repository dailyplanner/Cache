package com.example.redis.service;

import com.example.redis.bean.AppUserBean;
import com.example.redis.dao.AppUserDao;
import com.example.redis.utils.RedisUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.List;

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
        String json;
        json = redisUtils.get(appUserId.toString());
        if(json != null) {
            logger.info("The data got from redis");
            return gson.fromJson(json, AppUserBean.class);
        }
        logger.info("The data got from mysql");
        AppUserBean userBean = appUserDao.getAppUserById(appUserId);
        redisUtils.add(appUserId.toString(), gson.toJson(userBean));
        return userBean;
    }

    public boolean updateAppUser(AppUserBean appUserBean) {
        Gson gson = new GsonBuilder().create();
        return redisUtils.update(appUserBean.getId().toString(), gson.toJson(appUserBean));
    }

    public boolean delAppUserById(Long appUserId) {
        return redisUtils.delete(appUserId.toString());
    }
}
