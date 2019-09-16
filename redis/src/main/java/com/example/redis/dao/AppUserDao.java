package com.example.redis.dao;

import com.example.redis.bean.AppUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppUserDao {

    AppUserBean getAppUserById(@Param("appUserId") long appUserId);
}
