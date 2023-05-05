package com.liaoquanfeng.football.controller;

import com.liaoquanfeng.football.controller.response.ResponseModel;
import com.liaoquanfeng.football.domain.dto.FootballGameTypeDTO;
import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.vo.FootballTeamVO;
import com.liaoquanfeng.football.service.FootballGameTypeService;
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
 * @创建日期: 2022年12月27日
 * @创建时间: 3:21
 */
@Api(tags = "足球比赛类型")
@Validated
@RestController
@RequestMapping("/footballGameType")
@RequiredArgsConstructor
public class FootballGameTypeController {

    private final FootballGameTypeService footballGameTypeService;

    @ApiOperation(value = "新增足球比赛类型", notes = "新增足球比赛类型")
    @PostMapping("/add")
    public ResponseModel<List<FootballTeamVO>> add(@Validated(AddValidator.class) FootballGameTypeDTO footballGameTypeDTO){
        footballGameTypeService.add(footballGameTypeDTO);
        return ResponseModel.ok();
    }
}
