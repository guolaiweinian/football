package com.liaoquanfeng.football.dao;

import com.liaoquanfeng.football.domain.dto.FootballGameTypeDTO;
import com.liaoquanfeng.football.domain.entity.FootballGameType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface FootballGameTypeMapper {

    @Insert({ "INSERT INTO T_FOOTBALL_GAME_TYPE(ID, NAME, FULL_NAME, ENGLISH_NAME, TEAM_TYPE) values(#{id,jdbcType=NUMERIC}, #{name}, #{fullName}, #{englishName}, #{teamType})" })
    @SelectKey(before = true, resultType = Integer.class, keyProperty = "id", statement = "SELECT SEQ_FOOTBALL_GAME_TYPE.nextval FROM DUAL")
    int insert(FootballGameTypeDTO footballGameTypeDTO);

    @Select({"<script> SELECT t.ID, t.NAME, t.FULL_NAME, t.ENGLISH_NAME, T.TEAM_TYPE " +
            " FROM T_FOOTBALL_GAME_TYPE t WHERE 1 = 1 " +
            " <if test='id != null'> AND t.id = #{id} </if> " +
            " <if test='name != null'> AND t.name = #{name} </if> " +
            " <if test='fullName != null'> AND t.full_name = #{fullName} </if> " +
            " <if test='englishName != null'> AND t.english_name = #{englishName} </if> " +
            " <if test='teamType != null'> AND t.team_type = #{teamType} </if> " +
            "</script>"})
    List<FootballGameType> query(FootballGameTypeDTO footballGameTypeDTO);

}