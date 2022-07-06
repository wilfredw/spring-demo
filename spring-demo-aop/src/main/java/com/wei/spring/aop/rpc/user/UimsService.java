package com.wei.spring.aop.rpc.user;

import com.wei.spring.aop.rpc.model.UimsDTO;
import org.springframework.stereotype.Service;

@Service
public class UimsService {
    public UimsDTO get(String id) {
        return new UimsDTO(id, "uims-" + id);
    }
}
