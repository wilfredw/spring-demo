package com.wei.spring.demo.utils.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/15
 */
@Data
@Builder
public class UserVO {
    private String id;
    private String name;
}
