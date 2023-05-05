package com.liaoquanfeng.football.controller;

import com.liaoquanfeng.football.controller.response.ResponseModel;
import com.liaoquanfeng.football.domain.dto.FootballGameDTO;
import com.liaoquanfeng.football.domain.vo.FootballGameVO;
import com.liaoquanfeng.football.service.FootballGameService;
import com.liaoquanfeng.football.validator.AddValidator;
import com.liaoquanfeng.football.validator.AnalysisGameValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @创建日期: 2022年12月27日
 * @创建时间: 4:13
 */
@Api(tags = "足球比赛")
@Slf4j
@Validated
@RestController
@RequestMapping("/footballGame")
@RequiredArgsConstructor
public class FootballGameController {

    private final FootballGameService footballGameTypeService;

    @ApiOperation(value = "新增足球比赛", notes = "新增足球比赛")
    @PostMapping("/addByTeamName")
    public ResponseModel<List<FootballGameVO>> addByTeamName(@Validated(AddValidator.class) FootballGameDTO footballGameDTO){
        footballGameTypeService.addByTeamName(footballGameDTO);
        return ResponseModel.ok();
    }

    @ApiOperation(value = "新增足球比赛", notes = "新增足球比赛")
    @PostMapping("/add")
    public ResponseModel<List<FootballGameVO>> add(@Validated(AddValidator.class) FootballGameDTO footballGameDTO){
        log.info("新增足球比赛入参：" + footballGameDTO);
        footballGameTypeService.add(footballGameDTO);
        return ResponseModel.ok();
    }

    @ApiOperation(value = "足球比赛分析", notes = "足球比赛分析")
    @PostMapping("/analysis")
    public ResponseModel<List<FootballGameVO>> analysis(@Validated(AnalysisGameValidator.class) FootballGameDTO footballGameDTO){
        log.info("足球比赛分析：" + footballGameDTO);
        footballGameTypeService.analysis(footballGameDTO);
        return ResponseModel.ok();
    }
}
