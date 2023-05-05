package com.liaoquanfeng.football.service;

import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.entity.FootballTeam;
import com.liaoquanfeng.football.domain.vo.FootballTeamVO;

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
public interface FootballTeamService {

    void add(FootballTeamDTO footballTeamDTO);

    List<FootballTeamVO> getFootballTeamById(FootballTeamDTO footballTeamDTO);

    /**
     * 查询足球队信息，若足球队信息不存在则新增
     * @param footballTeamDTO
     * @return
     */
    FootballTeamVO getOrAddByName(FootballTeamDTO footballTeamDTO);
}
