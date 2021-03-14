package com.wei.spring.demo.utils.converter;

import com.wei.spring.demo.utils.model.dto.LoginCmd;
import com.wei.spring.demo.utils.model.dto.UserDTO;
import com.wei.spring.demo.utils.model.dto.UserVO;
import com.wei.spring.demo.utils.service.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/15
 */
@Mapper(componentModel = "spring")
public abstract class UserConverter {
    @Autowired
    protected UserRepository userRepository;

    @Mappings({
            @Mapping(target = "name",
                    expression = "java(userRepository.getName(loginCmd.getId()))")
    })
    public abstract UserVO toVO(LoginCmd loginCmd);


    public UserDTO toDTO(LoginCmd loginCmd) {
        return UserDTO.builder()
                .id(loginCmd.getId())
                .name(userRepository.getName(loginCmd.getId()))
                .desc("haha")
                .build();
    }
}
