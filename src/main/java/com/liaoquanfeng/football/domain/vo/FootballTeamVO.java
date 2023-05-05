package com.liaoquanfeng.football.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "足球队VO对象")
public class FootballTeamVO implements Serializable {

    @ApiModelProperty(value = "足球队ID")
    private Integer id;

    @ApiModelProperty(value = "足球队中文名称")
    private String name;

    @ApiModelProperty(value = "足球队主要名称")
    private String main_name;

    @ApiModelProperty(value = "足球队类型")
    private String type;

    /**
     * 足球队类型（0:国家队 1：俱乐部）
     */
    @ApiModelProperty(value = "足球队类型")
    private String teamType;

    private static final Long serialVersionUID = 1L;

}