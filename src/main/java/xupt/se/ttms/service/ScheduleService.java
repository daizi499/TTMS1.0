package xupt.se.ttms.service;

import xupt.se.ttms.entity.Schedule;
import java.util.List;

/**
 * 演出计划业务接口
 */
public interface ScheduleService {
    Schedule getById(Integer id);         // 根据ID查询
    List<Schedule> listAll();             // 列表（含演出厅和剧目名称）
    boolean add(Schedule schedule);       // 新增
    boolean update(Schedule schedule);    // 修改
    boolean delete(Integer id);           // 删除
}
