package com.soft1851.springboot.contentcenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/4
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "notice")
@ApiModel("公告")
public class Notice {
    @ApiModelProperty(name = "id",value = "公共id")
    private Integer id;

    @ApiModelProperty(name = "content",value = "公共内容")
    private String content;

    @ApiModelProperty(name = "showFlag",value = "是否显示 0：否 1：是")
    private Boolean showFlag;

    @ApiModelProperty(name = "createTime",value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
