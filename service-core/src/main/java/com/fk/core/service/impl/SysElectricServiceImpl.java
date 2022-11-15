package com.fk.core.service.impl;

import com.fk.core.pojo.entity.SysElectric;
import com.fk.core.mapper.SysElectricMapper;
import com.fk.core.service.SysElectricService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysElectricServiceImpl extends ServiceImpl<SysElectricMapper, SysElectric> implements SysElectricService {

    @Override
    public void DClean() {

    }
}
