package com.liaoquanfeng.football.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FootballTeam implements Serializable {
    /**
     * 足球队ID
     */
    private Integer id;

    /**
     * 足球队名称
     */
    private String name;

    /**
     * 足球队主要名称
     */
    private String mainName;


    /**
     * 足球队类型（0:国家队 1：俱乐部）
     */
    private String type;

    private Date createTime;

    private static final Long serialVersionUID = 1L;

}