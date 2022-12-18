package com.fk.core.service;

import com.fk.core.pojo.entity.SysElectric;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysElectricService extends IService<SysElectric> {

    //上班时间
    String workTime(Long comId);

    //下班时间
    String closingTime(Long comId);

    //周末信息
    boolean getWeekend(String date);

    //公司类型
    String  companyType(Long comId);
}
