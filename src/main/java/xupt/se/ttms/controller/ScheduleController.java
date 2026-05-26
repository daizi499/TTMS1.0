package xupt.se.ttms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xupt.se.ttms.common.R;
import xupt.se.ttms.entity.Play;
import xupt.se.ttms.entity.Schedule;
import xupt.se.ttms.entity.Studio;
import xupt.se.ttms.mapper.PlayMapper;
import xupt.se.ttms.mapper.ScheduleMapper;
import xupt.se.ttms.mapper.StudioMapper;
import xupt.se.ttms.service.ScheduleService;
import java.util.List;

/**
 * 演出计划管理 REST API
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;  // 用于冲突检查

    @Autowired
    private StudioMapper studioMapper;      // 用于查询演出厅状态

    @Autowired
    private PlayMapper playMapper;          // 用于查询剧目信息

    @GetMapping
    public R list() {
        List<Schedule> list = scheduleService.listAll();
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Schedule s = scheduleService.getById(id);
        if (s == null) return R.error("演出计划不存在");
        return R.ok(s);
    }

    /**
     * 新增演出计划（含时间段重叠校验）
     */
    @PostMapping
    public R add(@RequestBody Schedule schedule) {
        if (schedule.getStudioId() == null) return R.error("请选择演出厅");
        if (schedule.getPlayId() == null) return R.error("请选择剧目");
        if (schedule.getSchedTime() == null) return R.error("请选择演出时间");

        // 查询剧目信息：校验是否存在、是否已上线、获取时长
        Play play = playMapper.selectById(schedule.getPlayId());
        if (play == null) return R.error("所选剧目不存在");
        if (play.getStatus() == null || play.getStatus() != 1) {
            return R.error("所选剧目「" + play.getName() + "」尚未上线，请先在剧目管理中将其改为【已上线】");
        }
        int playLen = play.getLength() != null ? play.getLength() : 120; // 默认120分钟

        // 检查同一演出厅时间段是否重叠冲突
        int count = scheduleMapper.countOverlap(
                schedule.getStudioId(),
                schedule.getSchedTime().toString(),
                playLen);
        if (count > 0) {
            return R.error("该演出厅在此时间段已被占用（从 " + schedule.getSchedTime() +
                    " 到 " + schedule.getSchedTime().plusMinutes(playLen) + " 存在冲突），请调整时间");
        }

        // 校验演出厅状态（禁用状态不允许排期）
        Studio studio = studioMapper.selectById(schedule.getStudioId());
        if (studio == null) return R.error("所选演出厅不存在");
        if (studio.getStatus() == null || studio.getStatus() != 1) {
            return R.error("所选演出厅【" + studio.getName() + "】已禁用，请先启用后再排期");
        }

        if (schedule.getStatus() == null) schedule.setStatus(0);
        boolean ok = scheduleService.add(schedule);
        return ok ? R.ok("添加成功", schedule) : R.error("添加失败");
    }

    /**
     * 修改演出计划（含时间段重叠校验）
     */
    @PutMapping
    public R update(@RequestBody Schedule schedule) {
        if (schedule.getId() == null) return R.error("ID不能为空");

        // 校验演出厅状态
        Studio studio = studioMapper.selectById(schedule.getStudioId());
        if (studio == null) return R.error("所选演出厅不存在");
        if (studio.getStatus() == null || studio.getStatus() != 1) {
            return R.error("所选演出厅【" + studio.getName() + "】已禁用，请先启用后再排期");
        }

        // 查询剧目信息
        Play play = playMapper.selectById(schedule.getPlayId());
        if (play == null) return R.error("所选剧目不存在");
        if (play.getStatus() == null || play.getStatus() != 1) {
            return R.error("所选剧目「" + play.getName() + "」尚未上线，请先改为【已上线】后再排期");
        }
        int playLen = play.getLength() != null ? play.getLength() : 120;

        // 检查重叠（排除自身）
        int count = scheduleMapper.countOverlap(
                schedule.getStudioId(),
                schedule.getSchedTime().toString(),
                playLen);
        if (count > 0) {
            // 如果只查到自己（修改不改变冲突状态），允许通过
            Schedule existing = scheduleService.getById(schedule.getId());
            if (existing == null ||
                !existing.getStudioId().equals(schedule.getStudioId()) ||
                !existing.getSchedTime().equals(schedule.getSchedTime())) {
                return R.error("该演出厅在此时间段已被占用，请调整时间");
            }
        }

        boolean ok = scheduleService.update(schedule);
        return ok ? R.ok("修改成功", null) : R.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean ok = scheduleService.delete(id);
        return ok ? R.ok("删除成功", null) : R.error("删除失败");
    }
}
