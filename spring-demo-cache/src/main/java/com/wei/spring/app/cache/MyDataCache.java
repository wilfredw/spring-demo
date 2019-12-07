package com.wei.spring.app.cache;

import com.wei.spring.app.model.dto.MyDataDTO;

import java.util.Map;

/**
 * Created by viruser on 2019/12/5.
 */
public class MyDataCache extends BaseCache<MyDataDTO> {
    private volatile MyDataDTO myDataDTO;
    @Override
    public MyDataDTO getData() {
        return myDataDTO;
    }

    @Override
    protected void load() {
        ;
    }


}
