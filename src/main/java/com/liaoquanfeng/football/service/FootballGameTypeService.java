package com.liaoquanfeng.football.service;

import com.liaoquanfeng.football.domain.dto.FootballGameTypeDTO;

/**
 * @描述:
 * @版权: Copyright (c) 2021
 * @公司: 思迪信息
 * @作者: 廖泉峰
 * @版本: 1.0
 * @创建日期: 2022年12月27日
 * @创建时间: 3:22
 */
public interface FootballGameTypeService {

    /**
     * 新增足球比赛类型
     * @param footballGameTypeDTO
     */
    void add(FootballGameTypeDTO footballGameTypeDTO);
}
