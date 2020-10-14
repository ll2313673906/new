package com.soft1851.springboot.contentcenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/28
 */

@Table(name = "share")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分享")
public class Share {

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(name = "id",value = "id")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId",value = "发布人id")
    private Integer userId;

    @Column(name = "title")
    @ApiModelProperty(name = "title",value = "标题")
    private String title;

    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime",value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(name = "updateTime",value = "修改时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Column(name = "is_original")
    @ApiModelProperty(name = "isOriginal",value = "是否原创 0:否 1:是")
    private Boolean isOriginal;

    @Column(name = "author")
    @ApiModelProperty(name = "author",value = "作者")
    private String author;

    @Column(name = "cover")
    @ApiModelProperty(name = "cover",value = "封面")
    private String cover;

    @Column(name = "summary")
    @ApiModelProperty(name = "summary",value = "概要信息")
    private String summary;

    @Column(name = "price")
    @ApiModelProperty(name = "price",value = "价格（需要的积分）")
    private Integer price;

    @Column(name = "download_url")
    @ApiModelProperty(name = "downloadUrl",value = "下载地址")
    private String downloadUrl;

    @Column(name = "buy_count")
    @ApiModelProperty(name = "buyCount",value = "下载数")
    private Integer buyCount;

    @Column(name = "show_flag")
    @ApiModelProperty(name = "showFlag",value = "是否显示 0:否 1:是")
    private Boolean showFlag;

    @Column(name = "audit_status")
    @ApiModelProperty(name = "auditStatus",value = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过")
    private String auditStatus;

    @Column(name = "reason")
    @ApiModelProperty(name = "reason",value = "审核不通过原因")
    private String reason;
}
