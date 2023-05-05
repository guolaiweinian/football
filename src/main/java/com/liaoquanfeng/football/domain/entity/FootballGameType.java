package com.liaoquanfeng.football.domain.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
public class FootballGameType implements Serializable {
    /**
     * 足球比赛ID
     */
    private Integer id;

    /**
     * 足球比赛名称
     */
    private String name;

    /**
     * 足球比赛全称
     */
    private String fullName;

    /**
     * 足球比赛英文名称
     */
    private String englishName;

    /**
     * 足球比赛类型（0:国家队 1：俱乐部）
     */
    private String type;

    private static final Long serialVersionUID = 1L;

}