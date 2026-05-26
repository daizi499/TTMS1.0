package xupt.se.ttms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xupt.se.ttms.entity.Schedule;

import java.util.List;

/**
 * 演出计划 Mapper
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    /**
     * 查询所有演出计划——连表查出演出厅名称和剧目名称
     */
    @Select("SELECT s.sched_id AS id, s.studio_id AS studioId, s.play_id AS playId, " +
            "s.sched_time AS schedTime, s.sched_ticket_price AS ticketPrice, s.sched_status AS status, " +
            "st.studio_name AS studioName, p.play_name AS playName, p.play_length AS playLength " +
            "FROM schedule s " +
            "LEFT JOIN studio st ON s.studio_id = st.studio_id " +
            "LEFT JOIN play p ON s.play_id = p.play_id " +
            "ORDER BY s.sched_time DESC")
    List<Schedule> selectAllWithDetails();

    /**
     * 检查时间段是否冲突
     * 冲突条件：同一演出厅，且两个时间段有重叠
     *   A开始 < B结束 AND A结束 > B开始
     *   DATE_ADD(sched_time, INTERVAL play_length MINUTE) 计算结束时间
     */
    @Select("SELECT COUNT(*) FROM schedule s " +
            "JOIN play p ON s.play_id = p.play_id " +
            "WHERE s.studio_id = #{studioId} " +
            "AND s.sched_time < DATE_ADD(#{schedTime}, INTERVAL #{playLength} MINUTE) " +
            "AND DATE_ADD(s.sched_time, INTERVAL p.play_length MINUTE) > #{schedTime}")
    int countOverlap(@Param("studioId") Integer studioId,
                     @Param("schedTime") String schedTime,
                     @Param("playLength") Integer playLength);
}
