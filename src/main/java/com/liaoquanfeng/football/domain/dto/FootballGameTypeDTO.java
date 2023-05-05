package com.liaoquanfeng.football.domain.dto;

import com.liaoquanfeng.football.validator.AddValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FootballGameTypeDTO implements Serializable {
    /**
     * 足球比赛ID
     */
    @ApiModelProperty(value = "足球比赛类型ID")
    @NotNull(message = "足球比赛类型ID不能为空", groups = {})
    private Integer id;

    /**
     * 足球比赛名称
     */
    @ApiModelProperty(value = "足球比赛类型名称")
    @NotNull(message = "足球比赛类型名称不能为空", groups = {AddValidator.class})
    private String name;

    /**
     * 足球比赛全称
     */
    @ApiModelProperty(value = "足球比赛类型全称")
    @NotNull(message = "足球比赛类型全称不能为空", groups = {AddValidator.class})
    private String fullName;

    /**
     * 足球比赛英文名称
     */
    @ApiModelProperty(value = "足球比赛类型英文名")
    @NotNull(message = "足球比赛类型英文名不能为空", groups = {AddValidator.class})
    private String englishName;

    /**
     * 足球比赛类型（0:国家队 1：俱乐部）
     */
    @ApiModelProperty(value = "足球比赛主体类型")
    @NotNull(message = "足球比赛主体类型不能为空", groups = {AddValidator.class})
    private String teamType;

    private static final Long serialVersionUID = 1L;

}