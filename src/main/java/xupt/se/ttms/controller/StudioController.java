package xupt.se.ttms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xupt.se.ttms.common.R;
import xupt.se.ttms.entity.Studio;
import xupt.se.ttms.mapper.StudioMapper;
import xupt.se.ttms.service.StudioService;

import java.util.List;

@RestController
@RequestMapping("/api/studio")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @Autowired
    private StudioMapper studioMapper;

    @GetMapping
    public R list(@RequestParam(required = false) String name) {
        List<Studio> list = studioService.list(name);
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Studio studio = studioService.getById(id);
        if (studio == null) return R.error("演出厅不存在");
        return R.ok(studio);
    }

    /**
     * 新增演出厅（含名称查重）
     */
    @PostMapping
    public R add(@RequestBody Studio studio) {
        if (studio.getStatus() == null) studio.setStatus(1);
        if (studio.getName() == null || studio.getName().trim().isEmpty()) return R.error("演出厅名称不能为空");
        if (studio.getRowCount() == null || studio.getRowCount() <= 0) return R.error("排数必须大于0");
        if (studio.getColCount() == null || studio.getColCount() <= 0) return R.error("列数必须大于0");

        // 名称查重：检查是否已存在同名演出厅
        Long count = studioMapper.selectCount(
                new QueryWrapper<Studio>().eq("studio_name", studio.getName().trim()));
        if (count > 0) return R.error("已存在同名演出厅，请更换名称");

        boolean ok = studioService.add(studio);
        return ok ? R.ok("添加成功", studio) : R.error("添加失败");
    }

    /**
     * 修改演出厅（含名称查重——排除自身）
     */
    @PutMapping
    public R update(@RequestBody Studio studio) {
        if (studio.getId() == null) return R.error("ID不能为空");

        // 名称查重：检查是否存在同名但ID不同的演出厅
        Long count = studioMapper.selectCount(
                new QueryWrapper<Studio>()
                        .eq("studio_name", studio.getName().trim())
                        .ne("studio_id", studio.getId()));
        if (count > 0) return R.error("已存在同名演出厅，请更换名称");

        boolean ok = studioService.update(studio);
        return ok ? R.ok("修改成功", null) : R.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean ok = studioService.delete(id);
        return ok ? R.ok("删除成功", null) : R.error("删除失败");
    }
}
