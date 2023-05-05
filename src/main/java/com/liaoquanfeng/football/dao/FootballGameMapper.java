package com.liaoquanfeng.football.dao;

import com.liaoquanfeng.football.domain.dto.FootballGameDTO;
import com.liaoquanfeng.football.domain.entity.FootballGame;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface FootballGameMapper{

    @Insert({ "INSERT INTO T_FOOTBALL_GAME(ID, GAME_TYPE, HOME_TEAM_ID, HOME_TEAM_NAME, HOME_TEAM_FIRSTHALF_GOALS, HOME_TEAM_GOALS, HOME_TEAM_OVERTIME_GOALS," +
            " VISIT_TEAM_ID, VISIT_TEAM_NAME, VISIT_TEAM_FIRSTHALF_GOALS, VISIT_TEAM_GOALS, VISIT_TEAM_OVERTIME_GOALS, GAME_DATE, CREATE_TIME) values" +
            "(#{id,jdbcType=NUMERIC}, #{gameType}, #{homeTeamId}, #{homeTeamName}, #{homeTeamFirsthalfGoals}, #{homeTeamGoals}, #{homeTeamOvertimeGoals, jdbcType=NUMERIC}, " +
            " #{visitTeamId}, #{visitTeamName}, #{visitTeamFirsthalfGoals}, #{visitTeamGoals}, #{visitTeamOvertimeGoals, jdbcType=NUMERIC}," +
            " #{gameDate, jdbcType=TIMESTAMP}, #{createTime, jdbcType=TIMESTAMP})" })
    @SelectKey(before = true, resultType = Integer.class, keyProperty = "id", statement = "SELECT SEQ_FOOTBALL_GAME.nextval FROM DUAL")
    int insert(FootballGameDTO footballGameDTO);

    @Select({"<script> SELECT t.ID, t.GAME_TYPE, t.HOME_TEAM_ID, t.HOME_TEAM_NAME, t.HOME_TEAM_FIRSTHALF_GOALS, t.HOME_TEAM_GOALS, t.HOME_TEAM_OVERTIME_GOALS, " +
            " t.VISIT_TEAM_ID, t.VISIT_TEAM_NAME, t.VISIT_TEAM_FIRSTHALF_GOALS, t.VISIT_TEAM_GOALS, t.VISIT_TEAM_OVERTIME_GOALS, t.GAME_DATE, t.CREATE_TIME " +
            " FROM T_FOOTBALL_GAME t WHERE 1 = 1 " +
            " <if test='id != null'> AND t.id = #{id} </if> " +
            " <if test='gameType != null'> AND t.game_type = #{gameType} </if> " +
            " <if test='homeTeamId != null'> AND t.home_team_id = #{homeTeamId} </if> " +
            " <if test='homeTeamName != null'> AND t.home_team_name = #{homeTeamName} </if> " +
            " <if test='homeTeamFirsthalfGoals != null'> AND t.home_team_firsthalf_goals = #{homeTeamFirsthalfGoals} </if> " +
            " <if test='homeTeamGoals != null'> AND t.home_team_goals = #{homeTeamGoals} </if> " +
            " <if test='homeTeamOvertimeGoals != null'> AND t.home_team_overtime_goals = #{homeTeamOvertimeGoals} </if> " +
            " <if test='visitTeamId != null'> AND t.visit_team_id = #{visitTeamId} </if> " +
            " <if test='visitTeamName != null'> AND t.visit_team_name = #{visitTeamName} </if> " +
            " <if test='visitTeamFirsthalfGoals != null'> AND t.visit_team_firsthalf_goals = #{visitTeamFirsthalfGoals} </if> " +
            " <if test='visitTeamGoals != null'> AND t.visit_team_goals = #{visitTeamGoals} </if> " +
            " <if test='visitTeamOvertimeGoals != null'> AND t.visit_team_overtime_goals = #{visitTeamOvertimeGoals} </if> " +
            " <if test='gameDate != null'> AND t.game_date = #{gameDate} </if> " +
            "</script>"})
    List<FootballGame> query(FootballGameDTO footballGameDTO);

    /**
     * 查询主队指定比赛场次数据
     * @param footballGameDTO
     * @return
     */
    @Select({"<script> SELECT * FROM (SELECT t.ID, t.GAME_TYPE, t.HOME_TEAM_ID, t.HOME_TEAM_NAME, t.HOME_TEAM_FIRSTHALF_GOALS, t.HOME_TEAM_GOALS, t.HOME_TEAM_OVERTIME_GOALS, " +
            " t.VISIT_TEAM_ID, t.VISIT_TEAM_NAME, t.VISIT_TEAM_FIRSTHALF_GOALS, t.VISIT_TEAM_GOALS, t.VISIT_TEAM_OVERTIME_GOALS, t.GAME_DATE, t.CREATE_TIME " +
            " FROM T_FOOTBALL_GAME t WHERE 1 = 1 " +
            " <if test='id != null'> AND t.id = #{id} </if> " +
            " <if test='gameType != null'> AND t.game_type = #{gameType} </if> " +
            " <if test='homeTeamId != null'> AND (t.home_team_id = #{homeTeamId} OR t.visit_team_id = #{homeTeamId}) </if> " +
            " <if test='homeTeamName != null'> AND (t.home_team_name = #{homeTeamName} OR t.visit_team_name = #{homeTeamName}) </if> " +
            " <if test='homeTeamFirsthalfGoals != null'> AND t.home_team_firsthalf_goals = #{homeTeamFirsthalfGoals} </if> " +
            " <if test='homeTeamGoals != null'> AND t.home_team_goals = #{homeTeamGoals} </if> " +
            " <if test='homeTeamOvertimeGoals != null'> AND t.home_team_overtime_goals = #{homeTeamOvertimeGoals} </if> " +
            " <if test='gameDate != null'> AND t.game_date <![CDATA[ >= ]]> #{gameDate} </if> " +
            " ORDER BY GAME_DATE DESC ) WHERE = 1 = 1 " +
            " <if test='times != null'> AND ROWNUM <![CDATA[ <= ]] #{times} </if> " +
            "</script>"})
    List<FootballGame> queryByMianTimes(FootballGameDTO footballGameDTO, Integer times);

    @Select({"<script> SELECT * FROM (SELECT t.ID, t.GAME_TYPE, t.HOME_TEAM_ID, t.HOME_TEAM_NAME, t.HOME_TEAM_FIRSTHALF_GOALS, t.HOME_TEAM_GOALS, t.HOME_TEAM_OVERTIME_GOALS, " +
            " t.VISIT_TEAM_ID, t.VISIT_TEAM_NAME, t.VISIT_TEAM_FIRSTHALF_GOALS, t.VISIT_TEAM_GOALS, t.VISIT_TEAM_OVERTIME_GOALS, t.GAME_DATE, t.CREATE_TIME " +
            " FROM T_FOOTBALL_GAME t WHERE 1 = 1 " +
            " <if test='id != null'> AND t.id = #{id} </if> " +
            " <if test='gameType != null'> AND t.game_type = #{gameType} </if> " +
            " <if test='visitTeamId != null'> AND (t.visit_team_id = #{visitTeamId} OR t.main_team_id = #{visitTeamId}) </if> " +
            " <if test='visitTeamName != null'> AND (t.visit_team_name = #{visitTeamName} OR t.main_team_name = #{visitTeamName}) </if> " +
            " <if test='visitTeamFirsthalfGoals != null'> AND t.visit_team_firsthalf_goals = #{visitTeamFirsthalfGoals} </if> " +
            " <if test='visitTeamGoals != null'> AND t.visit_team_goals = #{visitTeamGoals} </if> " +
            " <if test='visitTeamOvertimeGoals != null'> AND t.visit_team_overtime_goals = #{visitTeamOvertimeGoals} </if> " +
            " ORDER BY GAME_DATE DESC ) WHERE = 1 = 1 " +
            " <if test='times != null'> AND ROWNUM <![CDATA[ <= ]] #{times} </if> " +
            "</script>"})
    List<FootballGame> queryByVisitTimes(FootballGameDTO footballGameDTO, int times);
}