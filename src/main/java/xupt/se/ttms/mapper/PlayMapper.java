package xupt.se.ttms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xupt.se.ttms.entity.Play;

/**
 * 剧目 Mapper - 继承 BaseMapper 后自带 CRUD，无需写 SQL
 */
@Mapper
public interface PlayMapper extends BaseMapper<Play> {
}
