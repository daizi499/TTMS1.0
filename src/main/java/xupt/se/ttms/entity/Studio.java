package xupt.se.ttms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("studio")
public class Studio {
    @TableId(value = "studio_id", type = IdType.AUTO)
    private Integer id;

    @TableField("studio_name")
    private String name;

    @TableField("studio_row_count")
    private Integer rowCount;

    @TableField("studio_col_count")
    private Integer colCount;

    @TableField("studio_introduction")
    private String introduction;

    @TableField("studio_status")
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getRowCount() { return rowCount; }
    public void setRowCount(Integer rowCount) { this.rowCount = rowCount; }
    public Integer getColCount() { return colCount; }
    public void setColCount(Integer colCount) { this.colCount = colCount; }
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
