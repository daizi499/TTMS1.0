package xupt.se.ttms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xupt.se.ttms.common.R;
import xupt.se.ttms.entity.Play;
import xupt.se.ttms.mapper.PlayMapper;
import xupt.se.ttms.service.PlayService;

import java.util.List;

/**
 * 剧目管理 REST API
 */
@RestController
@RequestMapping("/api/play")
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private PlayMapper playMapper;

    @GetMapping
    public R list(@RequestParam(required = false) String name) {
        List<Play> list = playService.list(name);
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Play play = playService.getById(id);
        if (play == null) return R.error("剧目不存在");
        return R.ok(play);
    }

    /**
     * 新增剧目（含名称查重）
     */
    @PostMapping
    public R add(@RequestBody Play play) {
        if (play.getStatus() == null) play.setStatus(0);
        if (play.getName() == null || play.getName().trim().isEmpty()) return R.error("剧目名称不能为空");

        // 名称查重
        Long count = playMapper.selectCount(
                new QueryWrapper<Play>().eq("play_name", play.getName().trim()));
        if (count > 0) return R.error("已存在同名剧目，请更换名称");

        boolean ok = playService.add(play);
        return ok ? R.ok("添加成功", play) : R.error("添加失败");
    }

    /**
     * 修改剧目（含名称查重——排除自身）
     */
    @PutMapping
    public R update(@RequestBody Play play) {
        if (play.getId() == null) return R.error("ID不能为空");

        // 名称查重：检查是否存在同名但ID不同的剧目
        Long count = playMapper.selectCount(
                new QueryWrapper<Play>()
                        .eq("play_name", play.getName().trim())
                        .ne("play_id", play.getId()));
        if (count > 0) return R.error("已存在同名剧目，请更换名称");

        boolean ok = playService.update(play);
        return ok ? R.ok("修改成功", null) : R.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean ok = playService.delete(id);
        return ok ? R.ok("删除成功", null) : R.error("删除失败");
    }
}
