package xupt.se.ttms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xupt.se.ttms.entity.Studio;
import xupt.se.ttms.mapper.StudioMapper;
import xupt.se.ttms.service.StudioService;

import java.util.List;

@Service
public class StudioServiceImpl extends ServiceImpl<StudioMapper, Studio> implements StudioService {

    @Override
    public Studio getById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Studio> list(String name) {
        QueryWrapper<Studio> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("studio_id");
        if (name != null && !name.trim().isEmpty()) {
            wrapper.like("studio_name", name.trim());
        }
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean add(Studio studio) {
        return this.baseMapper.insert(studio) > 0;
    }

    @Override
    public boolean update(Studio studio) {
        return this.baseMapper.updateById(studio) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return this.baseMapper.deleteById(id) > 0;
    }
}
