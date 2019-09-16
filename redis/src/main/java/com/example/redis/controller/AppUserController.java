package com.example.redis.controller;

import com.example.redis.bean.AppUserBean;
import com.example.redis.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AppUserController {

    @Resource
    private AppUserService appUserService;

    /**
     * 查询
     * @param userId
     * @return
     */
    @GetMapping("/app_user/{userId}")
    public ResponseEntity getAppUserById(@PathVariable("userId") long userId) {

        try {
            return new ResponseEntity(appUserService.getAppUserById(userId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateAppUser(@RequestBody AppUserBean appUserBean) {
        try {
            return new ResponseEntity(appUserService.updateAppUser(appUserBean), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @DeleteMapping("/app_user/{userId}")
    public ResponseEntity delAppUserById(@PathVariable("userId") long userId) {
        try {
            return new ResponseEntity(appUserService.delAppUserById(userId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
