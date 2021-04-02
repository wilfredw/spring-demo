package com.wei.spring.aop.service.user;

import com.wei.spring.aop.biz.model.dataobject.UserDO;
import com.wei.spring.aop.biz.user.UserBizService;
import com.wei.spring.aop.rpc.model.UimsDTO;
import com.wei.spring.aop.rpc.user.UimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserBizService userBizService;

    @Autowired
    private UimsService uimsService;

    public String get(String id) {
        UserDO userDO = userBizService.get(id);
        String name = userDO.getName();
        UimsDTO uimsDTO = uimsService.get(id);
        return name + " " + uimsDTO.getName();
    }
}
