package xupt.se.ttms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 剧目实体 - 对应数据库 play 表
 */
@TableName("play")
public class Play {

    @TableId(value = "play_id", type = IdType.AUTO)
    private Integer id;               // 剧目ID

    @TableField("dict_type_id")
    private Integer typeId;           // 剧目类型ID（关联 data_dict）

    @TableField("dict_lang_id")
    private Integer langId;           // 语言ID（关联 data_dict）

    @TableField("play_name")
    private String name;              // 剧目名称

    @TableField("play_introduction")
    private String introduction;      // 剧目简介

    @TableField("play_image")
    private String image;             // 封面图片路径

    @TableField("play_length")
    private Integer length;           // 时长（分钟）

    @TableField("play_ticket_price")
    private BigDecimal ticketPrice;   // 票价

    @TableField("play_status")
    private Integer status;           // 状态：0=已下线 1=已上线 -1=删除

    // ========== Getter/Setter ==========
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getTypeId() { return typeId; }
    public void setTypeId(Integer typeId) { this.typeId = typeId; }
    public Integer getLangId() { return langId; }
    public void setLangId(Integer langId) { this.langId = langId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }
    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
