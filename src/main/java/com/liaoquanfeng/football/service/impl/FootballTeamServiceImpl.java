package com.liaoquanfeng.football.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.liaoquanfeng.football.dao.FootballTeamMapper;
import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.entity.FootballTeam;
import com.liaoquanfeng.football.domain.vo.FootballTeamVO;
import com.liaoquanfeng.football.error.BusinessException;
import com.liaoquanfeng.football.service.FootballTeamService;
import com.liaoquanfeng.football.util.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author liaoqf
 * @date $ $
 * @description
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FootballTeamServiceImpl implements FootballTeamService {

    private final FootballTeamMapper footballTeamMapper;

    @Override
    public void add(FootballTeamDTO footballTeamDTO) {
        List<FootballTeam> footballTeams = footballTeamMapper.query(footballTeamDTO);
        if(CollectionUtil.isEmpty(footballTeams)){
            footballTeamDTO.setCreateTime(new Date(System.currentTimeMillis()));
            int flag = footballTeamMapper.insert(footballTeamDTO);
            log.info("新增的主队信息：" + footballTeamDTO);
        } else {
            throw new BusinessException(-100000, "球队已存在");
        }
    }

    @Override
    public List<FootballTeamVO> getFootballTeamById(FootballTeamDTO footballTeamDTO) {
        List<FootballTeam> footballTeams = footballTeamMapper.query(footballTeamDTO);
        List<FootballTeamVO> footballTeamVO = BeanCopyUtils.copy(footballTeams, FootballTeamVO::new);
        return footballTeamVO;
    }

    @Override
    public FootballTeamVO getOrAddByName(FootballTeamDTO footballTeamDTO) {
        List<FootballTeam> footballTeams = footballTeamMapper.query(footballTeamDTO);
        if(StrUtil.isEmpty(footballTeamDTO.getName())){
            throw new BusinessException(-100000, "球队名称为空");
        }
        if(StrUtil.isEmpty(footballTeamDTO.getMainName())){
            throw new BusinessException(-100000, "球队主名称为空");
        }
        if(CollectionUtil.isEmpty(footballTeams)){
            // 未查询到主队信息，新增主队
            log.info("未查询到队伍{}信息,进行新增", footballTeamDTO.getName());
            footballTeamDTO.setCreateTime(new Date(System.currentTimeMillis()));
            int flag = footballTeamMapper.insert(footballTeamDTO);
            if(flag <= 0){
                throw new BusinessException(-100001, "球队" + footballTeamDTO.getName() + "新增失败");
            }
            return BeanCopyUtils.copy(footballTeamDTO, new FootballTeamVO());
        }
        return BeanCopyUtils.copy(footballTeams.get(0), new FootballTeamVO());
    }
}
