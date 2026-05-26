package xupt.se.ttms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xupt.se.ttms.entity.Play;
import xupt.se.ttms.mapper.PlayMapper;
import xupt.se.ttms.service.PlayService;

import java.util.List;

/**
 * 剧目业务实现 - 继承 ServiceImpl 后自带 save/update/remove/page 等方法
 */
@Service
public class PlayServiceImpl extends ServiceImpl<PlayMapper, Play> implements PlayService {

    @Override
    public Play getById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Play> list(String name) {
        // 构建查询条件
        QueryWrapper<Play> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("play_id");  // 按ID升序排列
        // 如果有搜索关键字，按名称模糊匹配
        if (name != null && !name.trim().isEmpty()) {
            wrapper.like("play_name", name.trim());
        }
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean add(Play play) {
        return this.baseMapper.insert(play) > 0;
    }

    @Override
    public boolean update(Play play) {
        return this.baseMapper.updateById(play) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return this.baseMapper.deleteById(id) > 0;
    }
}
