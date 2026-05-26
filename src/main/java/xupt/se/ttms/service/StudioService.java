package xupt.se.ttms.service;

import xupt.se.ttms.entity.Studio;
import java.util.List;

public interface StudioService {
    Studio getById(Integer id);
    List<Studio> list(String name);
    boolean add(Studio studio);
    boolean update(Studio studio);
    boolean delete(Integer id);
}
