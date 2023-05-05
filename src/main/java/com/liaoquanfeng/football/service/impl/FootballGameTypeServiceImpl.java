package com.liaoquanfeng.football.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.liaoquanfeng.football.dao.FootballGameTypeMapper;
import com.liaoquanfeng.football.domain.dto.FootballGameTypeDTO;
import com.liaoquanfeng.football.domain.entity.FootballGameType;
import com.liaoquanfeng.football.domain.entity.FootballTeam;
import com.liaoquanfeng.football.error.BusinessException;
import com.liaoquanfeng.football.service.FootballGameTypeService;
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
 * @创建时间: 3:22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FootballGameTypeServiceImpl implements FootballGameTypeService {

    private final FootballGameTypeMapper footballGameTypeMapper;

    @Override
    public void add(FootballGameTypeDTO footballGameTypeDTO) {
        List<FootballGameType> footballGameTypes = footballGameTypeMapper.query(footballGameTypeDTO);
        if(CollectionUtil.isEmpty(footballGameTypes)){
            int flag = footballGameTypeMapper.insert(footballGameTypeDTO);
        } else {
            throw new BusinessException(-100000, "比赛类型已存在");
        }
    }
}
