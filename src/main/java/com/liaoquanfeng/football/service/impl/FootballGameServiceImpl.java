package com.liaoquanfeng.football.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.liaoquanfeng.football.dao.FootballGameMapper;
import com.liaoquanfeng.football.dao.FootballTeamMapper;
import com.liaoquanfeng.football.domain.dto.FootballGameDTO;
import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.entity.FootballGame;
import com.liaoquanfeng.football.domain.entity.FootballTeam;
import com.liaoquanfeng.football.domain.vo.FootballGameVO;
import com.liaoquanfeng.football.domain.vo.FootballTeamVO;
import com.liaoquanfeng.football.error.BusinessException;
import com.liaoquanfeng.football.service.FootballGameService;
import com.liaoquanfeng.football.service.FootballTeamService;
import com.liaoquanfeng.football.util.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class FootballGameServiceImpl implements FootballGameService {

    private final FootballGameMapper footballGameMapper;

    private final FootballTeamMapper footballTeamMapper;

    private final FootballTeamService footballTeamService;

    @Override
    public FootballGameVO add(FootballGameDTO footballGameDTO) {
        Date now = new Date(System.currentTimeMillis());
        // 查询主队信息是否存在
        FootballTeamDTO homeFootballTeamDTO = new FootballTeamDTO();

        homeFootballTeamDTO.setType(footballGameDTO.getTeamType());
        homeFootballTeamDTO.setName(footballGameDTO.getHomeTeamName());
        homeFootballTeamDTO.setMainName(footballGameDTO.getHomeTeamMainName());
        FootballTeamVO homeFootballTeamVO = footballTeamService.getOrAddByName(homeFootballTeamDTO);

        // 查询客队信息是否存在
        FootballTeamDTO visitFootballTeamDTO = new FootballTeamDTO();
        visitFootballTeamDTO.setType(footballGameDTO.getTeamType());
        visitFootballTeamDTO.setName(footballGameDTO.getVisitTeamName());
        visitFootballTeamDTO.setMainName(footballGameDTO.getVisitTeamMainName());

        FootballTeamVO visitFootballTeamVO = footballTeamService.getOrAddByName(visitFootballTeamDTO);

        footballGameDTO.setHomeTeamId(homeFootballTeamVO.getId());
        footballGameDTO.setVisitTeamId(visitFootballTeamVO.getId());
        // 查询比赛是否存在
        List<FootballGame> footballGames = footballGameMapper.query(footballGameDTO);
        if(!CollectionUtil.isEmpty(footballGames)){
            throw new BusinessException(-110004, "比赛记录已存在");
        }

        footballGameDTO.setCreateTime(now);

        int flag = footballGameMapper.insert(footballGameDTO);
        if(flag <= 0){
            throw new BusinessException(-110002, "比赛记录新增失败");
        }
        return BeanCopyUtils.copy(footballGameDTO, new FootballGameVO());
    }

    @Override
    public void addByTeamName(FootballGameDTO footballGameDTO) {
        // 先根据主队名称查询主队信息
        FootballTeamDTO homeFootballTeamDTO = new FootballTeamDTO();
        homeFootballTeamDTO.setName(footballGameDTO.getHomeTeamName());
        List<FootballTeam> homeFootballTeams = footballTeamMapper.query(homeFootballTeamDTO);
        if(CollectionUtil.isEmpty(homeFootballTeams)){
            throw new BusinessException(-110000, "主场球队不存在");
        } else if (homeFootballTeams.size() > 1){
            throw new BusinessException(-110001, "主场球队有多个");
        }
        FootballTeam homeFootballTeam = homeFootballTeams.get(0);

        // 根据客队名称查询客队信息
        FootballTeamDTO visitFootballTeamDTO = new FootballTeamDTO();
        visitFootballTeamDTO.setName(footballGameDTO.getVisitTeamName());
        List<FootballTeam> visitFootballTeams = footballTeamMapper.query(visitFootballTeamDTO);
        if(CollectionUtil.isEmpty(visitFootballTeams)){
            throw new BusinessException(-110002, "客场球队不存在");
        } else if (visitFootballTeams.size() > 1){
            throw new BusinessException(-110003, "客场球队有多个");
        }
        FootballTeam visitFootballTeam = visitFootballTeams.get(0);

        footballGameDTO.setHomeTeamId(homeFootballTeam.getId());
        footballGameDTO.setVisitTeamId(visitFootballTeam.getId());

        int flag = footballGameMapper.insert(footballGameDTO);
    }

    @Override
    public void analysis(FootballGameDTO footballGameDTO) {
        if("1".equals(footballGameDTO.getGameType())){
            // 查询主队的主场所有比赛
            List<FootballGame> mainFootballGames = footballGameMapper.queryByMianTimes(footballGameDTO, 20);
            // 查询客队的客场所有比赛
            List<FootballGame> visitFootballGames = footballGameMapper.queryByVisitTimes(footballGameDTO, 20);
            // 计算相同比赛的主场进球平均数（同类型比赛的主队进攻平均数，客队防守平均数）

            // 计算相同比赛的客场进球平均数（同类型比赛的客队进攻平均数，主队防守平均数）

            // 主队进攻平均数*客队防守平均数*主队所有比赛平均数 = 主队进球系数


            // 客队进攻平均数*客队防守平均数*所有客队比赛平均数 = 客队进球系数

            // 泊松分布计算比分可能性,P(x,μ) = (e-μ)(μ*x)/x!
            // 1、进球范围取 [0-指定比赛场次的最大值，若值最大不超过5，则取5]

        }

        // 计算主队的平均进球数

        // 计算主队的平均失球

        // 计算客队的平均进球数

        // 计算客队的平均失球数
    }
}
