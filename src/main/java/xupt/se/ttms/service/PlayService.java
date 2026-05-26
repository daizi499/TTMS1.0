package xupt.se.ttms.service;

import xupt.se.ttms.entity.Play;
import java.util.List;

/**
 * 剧目业务接口
 */
public interface PlayService {
    Play getById(Integer id);           // 根据ID查询
    List<Play> list(String name);       // 列表查询（支持按名称模糊搜索）
    boolean add(Play play);             // 新增
    boolean update(Play play);          // 修改
    boolean delete(Integer id);         // 删除
}
