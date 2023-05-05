package com.liaoquanfeng.football.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FootballGame implements Serializable {
    /**
     * 足球比赛ID
     */
    private Integer id;

    /**
     * 足球比赛类型
     */
    private String gameType;

    /**
     * 主队ID
     */
    private Integer homeTeamId;

    /**
     * 主队名称
     */
    private String homeTeamName;

    /**
     * 主队上半场进球数
     */
    private Short homeTeamFirsthalfGoals;

    /**
     * 主队总进球数
     */
    private Short homeTeamGoals;

    /**
     * 主队加时赛进球数
     */
    private Short homeTeamOvertimeGoals;

    /**
     * 客队ID
     */
    private Integer visitTeamId;

    /**
     * 客队名称
     */
    private String visitTeamName;

    /**
     * 客队上半场进球数
     */
    private Short visitTeamFirsthalfGoals;

    /**
     * 客队总进球数
     */
    private Short visitTeamGoals;

    /**
     * 客队加时赛进球数
     */
    private Short visitTeamOvertimeGoals;

    /**
     * 比赛时间
     */
    private Date gameDate;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

}