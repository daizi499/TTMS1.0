package xupt.se.ttms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 演出计划实体 - 对应数据库 schedule 表
 */
@TableName("schedule")
public class Schedule {

    @TableId(value = "sched_id", type = IdType.AUTO)
    private Integer id;                    // 计划ID

    @TableField("studio_id")
    private Integer studioId;              // 演出厅ID（外键）

    @TableField("play_id")
    private Integer playId;                // 剧目ID（外键）

    @TableField("sched_time")
    private LocalDateTime schedTime;       // 演出时间

    @TableField("sched_ticket_price")
    private BigDecimal ticketPrice;        // 票价

    @TableField("sched_status")
    private Integer status;                // 状态

    // 以下字段不存数据库，仅用于列表展示（JOIN查询结果）
    @TableField(exist = false)
    private String studioName;             // 演出厅名称

    @TableField(exist = false)
    private String playName;               // 剧目名称

    @TableField(exist = false)
    private Integer playLength;            // 剧目时长（分钟）

    // ========== Getter/Setter ==========
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudioId() { return studioId; }
    public void setStudioId(Integer studioId) { this.studioId = studioId; }
    public Integer getPlayId() { return playId; }
    public void setPlayId(Integer playId) { this.playId = playId; }
    public LocalDateTime getSchedTime() { return schedTime; }
    public void setSchedTime(LocalDateTime schedTime) { this.schedTime = schedTime; }
    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getStudioName() { return studioName; }
    public void setStudioName(String studioName) { this.studioName = studioName; }
    public String getPlayName() { return playName; }
    public void setPlayName(String playName) { this.playName = playName; }
    public Integer getPlayLength() { return playLength; }
    public void setPlayLength(Integer playLength) { this.playLength = playLength; }
}
