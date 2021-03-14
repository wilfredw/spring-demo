package com.wei.spring.demo.utils.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/14
 */
@Data
@Builder
public class UserDTO {
    private String id;
    private String name;
    private String desc;
}
