package com.liaoquanfeng.football.domain.dto;

import com.liaoquanfeng.football.validator.AddGameValidator;
import com.liaoquanfeng.football.validator.AddValidator;
import com.liaoquanfeng.football.validator.AnalysisGameValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class FootballGameDTO implements Serializable {
    /**
     * 足球比赛ID
     */
    @ApiModelProperty(value = "足球比赛ID")
    @NotNull(message = "足球比赛ID不能为空", groups = {})
    private Integer id;

    /**
     * 足球比赛类型
     */
    @ApiModelProperty(value = "足球比赛类型")
    @NotNull(message = "足球比赛类型不能为空", groups = {AddValidator.class, AddGameValidator.class, AnalysisGameValidator.class})
    private String gameType;

    @ApiModelProperty(value = "足球队伍类型")
    @NotNull(message = "足球队伍类型不能为空", groups = {AddValidator.class, AddGameValidator.class})
    private String teamType;

    /**
     * 主队ID
     */
    @ApiModelProperty(value = "主队ID")
    @NotNull(message = "主队ID不能为空", groups = {AnalysisGameValidator.class})
    private Integer homeTeamId;

    /**
     * 主队名称
     */
    @ApiModelProperty(value = "主队名称")
    @NotNull(message = "主队名称不能为空", groups = {AddValidator.class, AddGameValidator.class})
    private String homeTeamName;

    /**
     * 主队名称
     */
    @ApiModelProperty(value = "主队主要名称")
    @NotNull(message = "主队名称不能为空", groups = {AddGameValidator.class})
    private String homeTeamMainName;

    /**
     * 主队上半场进球数
     */
    @ApiModelProperty(value = "主队上半场进球数")
    @NotNull(message = "主队上半场进球数不能为空", groups = {})
    private Integer homeTeamFirsthalfGoals;

    /**
     * 主队总进球数
     */
    @ApiModelProperty(value = "主队总进球数")
    @NotNull(message = "主队总进球数不能为空", groups = {})
    private Integer homeTeamGoals;

    /**
     * 主队加时赛进球数
     */
    @ApiModelProperty(value = "主队加时赛进球数")
    @NotNull(message = "主队加时赛进球数不能为空", groups = {})
    private Integer homeTeamOvertimeGoals;

    /**
     * 客队ID
     */
    @ApiModelProperty(value = "客队ID")
    @NotNull(message = "客队ID不能为空", groups = {AnalysisGameValidator.class})
    private Integer visitTeamId;

    /**
     * 客队名称
     */
    @ApiModelProperty(value = "客队名称")
    @NotNull(message = "客队名称不能为空", groups = {AddValidator.class, AddGameValidator.class})
    private String visitTeamName;

    /**
     * 客队名称
     */
    @ApiModelProperty(value = "客队主要名称")
    @NotNull(message = "客队主要名称不能为空", groups = {AddGameValidator.class})
    private String visitTeamMainName;

    /**
     * 客队上半场进球数
     */
    @ApiModelProperty(value = "客队上半场进球数")
    @NotNull(message = "客队上半场进球数不能为空", groups = {})
    private Integer visitTeamFirsthalfGoals;

    /**
     * 客队总进球数
     */
    @ApiModelProperty(value = "客队总进球数")
    @NotNull(message = "客队总进球数不能为空", groups = {})
    private Integer visitTeamGoals;

    /**
     * 客队加时赛进球数
     */
    @ApiModelProperty(value = "客队加时赛进球数")
    @NotNull(message = "客队加时赛进球数不能为空", groups = {})
    private Integer visitTeamOvertimeGoals;

    /**
     * 比赛时间
     */
    @ApiModelProperty(value = "比赛时间")
    @NotNull(message = "比赛时间不能为空", groups = {AddValidator.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gameDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

}