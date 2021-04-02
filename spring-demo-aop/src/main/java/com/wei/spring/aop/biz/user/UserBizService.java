package com.wei.spring.aop.biz.user;

import com.wei.spring.aop.biz.model.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public class UserBizService {
    public UserDO get(String id) {
        return new UserDO(id, "name-"+id);
    }
}
