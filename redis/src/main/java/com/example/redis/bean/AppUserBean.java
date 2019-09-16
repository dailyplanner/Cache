package com.example.redis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AppUserBean {

    private Long id;

    private String nickname;

    private Integer gender;

    private String phone;

    private String email;

    private String weChartId;

    private Date gmtCreate;

    private Date gmtModified;

}
