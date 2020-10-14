package com.soft1851.springboot.usercenter.domain.entity;

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
 * @Data 2020/10/8
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bonus_event_log")
public class BonusEventLog {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "value")
    private Integer value;

    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "description")
    private String description;
}
