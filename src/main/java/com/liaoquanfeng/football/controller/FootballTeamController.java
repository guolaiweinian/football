package com.liaoquanfeng.football.controller;

import com.liaoquanfeng.football.controller.response.ResponseModel;
import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.vo.FootballTeamVO;
import com.liaoquanfeng.football.service.FootballTeamService;
import com.liaoquanfeng.football.validator.AddValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @描述:
 * @版权: Copyright (c) 2021
 * @公司: 思迪信息
 * @作者: 廖泉峰
 * @版本: 1.0
 * @创建日期: 2022年12月15日
 * @创建时间: 11:01
 */
@Api(tags = "足球队")
@Validated
@RestController
@RequestMapping("/footballTeam")
@RequiredArgsConstructor
public class FootballTeamController {

    private final FootballTeamService footballTeamService;

    @ApiOperation(value = "新增足球队信息", notes = "查询足球队信息")
    @PostMapping("/add")
    public ResponseModel<List<FootballTeamVO>> add(@Validated(AddValidator.class) FootballTeamDTO footballTeamDTO){
        footballTeamService.add(footballTeamDTO);
        return ResponseModel.ok();
    }

    @ApiOperation(value = "查询足球队列表信息", notes = "查询足球队列表信息")
    @PostMapping("/getList")
    public ResponseModel<List<FootballTeamVO>> getList(FootballTeamDTO footballTeamDTO){
        return ResponseModel.ok(footballTeamService.getFootballTeamById(footballTeamDTO));
    }
}
