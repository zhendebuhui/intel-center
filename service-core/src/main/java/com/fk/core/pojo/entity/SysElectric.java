package com.fk.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysElectric对象", description="")
@Accessors(chain = true)
public class SysElectric implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业id")
    private Long comId;

    @ApiModelProperty(value = "电量")
    private Double electricity;

    @ApiModelProperty(value = "电表更新时间")
    private String updateTime;

    @ApiModelProperty(value = "删除标记（0：可用 1：已删除）")
    @TableLogic
    private Integer isDeleted;


}
