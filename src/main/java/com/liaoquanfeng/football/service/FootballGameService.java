package com.liaoquanfeng.football.service;

import com.liaoquanfeng.football.domain.dto.FootballGameDTO;
import com.liaoquanfeng.football.domain.vo.FootballGameVO;

/**
 * @描述:
 * @版权: Copyright (c) 2021
 * @公司: 思迪信息
 * @作者: 廖泉峰
 * @版本: 1.0
 * @创建日期: 2022年12月27日
 * @创建时间: 4:13
 */
public interface FootballGameService {

    /**
     * 新增比赛记录
     * @param footballGameDTO
     * @return
     */
    FootballGameVO add(FootballGameDTO footballGameDTO);

    /**
     * 根据足球队名称新增比赛记录
     * @param footballGameDTO
     */
    void addByTeamName(FootballGameDTO footballGameDTO);

    /**
     * 分析比赛记录
     * @param footballGameDTO
     */
    void analysis(FootballGameDTO footballGameDTO);
}
