package com.liaoquanfeng.football.domain.dto;

import com.liaoquanfeng.football.validator.AddValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@ApiModel(value = "足球队DTO对象")
public class FootballTeamDTO implements Serializable {

    @ApiModelProperty(value = "足球队ID")
    @NotNull(message = "足球队ID不能为空", groups = {})
    private Integer id;

    @ApiModelProperty(value = "足球队中文名称")
    @NotNull(message = "足球队中文名称不能为空", groups = {AddValidator.class})
    private String name;

    @ApiModelProperty(value = "足球队主要名称")
    @NotNull(message = "足球队主要名称不能为空", groups = {AddValidator.class})
    private String mainName;

    /**
     * 足球队类型（0:国家队 1：俱乐部）
     */
    @ApiModelProperty(value = "足球队类型")
    @NotNull(message = "足球队类型不能为空", groups = {AddValidator.class})
    private String type;

    private Date createTime;

    private static final Long serialVersionUID = 1L;

}