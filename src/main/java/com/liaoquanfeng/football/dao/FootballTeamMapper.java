package com.liaoquanfeng.football.dao;

import com.liaoquanfeng.football.domain.dto.FootballTeamDTO;
import com.liaoquanfeng.football.domain.entity.FootballTeam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface FootballTeamMapper {

    @Insert({ "INSERT INTO T_FOOTBALL_TEAM(ID, NAME, MAIN_NAME, TYPE, CREATE_TIME) values(#{id,jdbcType=NUMERIC}, #{name}, #{mainName}, #{type}, #{createTime, jdbcType=TIMESTAMP})" })
    @SelectKey(before = true, resultType = Integer.class, keyProperty = "id", statement = "SELECT SEQ_FOOTBALL_TEAM.nextval FROM DUAL")
    int insert(FootballTeamDTO footballTeamDTO);

    @Select({"<script> SELECT t.ID, t.NAME, t.TYPE, t.CREATE_TIME " +
            " FROM T_FOOTBALL_TEAM t WHERE 1 = 1 " +
            " <if test='id != null'> AND t.id = #{id} </if> " +
            " <if test='name != null'> AND t.name = #{name} </if> " +
            " <if test='mainName != null'> AND t.main_name = #{mainName} </if> " +
            " <if test='type != null'> AND t.type = #{type} </if> " +
            "</script>"})
    List<FootballTeam> query(FootballTeamDTO footballTeamDTO);

}