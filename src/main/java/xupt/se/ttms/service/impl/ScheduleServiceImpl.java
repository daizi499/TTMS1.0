package xupt.se.ttms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xupt.se.ttms.entity.Schedule;
import xupt.se.ttms.mapper.ScheduleMapper;
import xupt.se.ttms.service.ScheduleService;

import java.util.List;

/**
 * 演出计划业务实现
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Override
    public Schedule getById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Schedule> listAll() {
        // 使用自定义 JOIN 查询，关联演出厅名称和剧目名称
        return this.baseMapper.selectAllWithDetails();
    }

    @Override
    public boolean add(Schedule schedule) {
        return this.baseMapper.insert(schedule) > 0;
    }

    @Override
    public boolean update(Schedule schedule) {
        return this.baseMapper.updateById(schedule) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return this.baseMapper.deleteById(id) > 0;
    }
}
