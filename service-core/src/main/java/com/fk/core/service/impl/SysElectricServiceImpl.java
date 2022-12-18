package com.fk.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fk.common.exception.Assert;
import com.fk.core.pojo.entity.SysElectric;
import com.fk.core.mapper.SysElectricMapper;
import com.fk.core.service.SysElectricService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysElectricServiceImpl extends ServiceImpl<SysElectricMapper, SysElectric> implements SysElectricService {

    @Resource
    private SysElectricMapper sysElectricMapper;

    @Resource
    private RedisTemplate redisTemplate;

    //非工作时间一小时用电限制
    //TODO 应设置两种公司类型的非工作时间一小时用电
    Double offTimeE = 5.0;

    //TODO 应设置两种公司类型的全天电量限制
    Double maxE0 = 20.0; //办公型
    Double maxE1 = 30.0; //生产型

    @Override
    public String workTime(Long comId) {

        //预定义
        StringBuilder workTime = null;

        QueryWrapper<SysElectric> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id", comId);
        List<SysElectric> electricList = sysElectricMapper.selectList(wrapper);

        //如果一天用电量少于限制值，直接返回 不上班
        if (electricList != null && electricList.size() > 0) {
            Double e0 = electricList.get(0).getElectricity();
            Double e1 = electricList.get(electricList.size() - 1).getElectricity();
            if (e1 - e0 < maxE0) {
                return String.valueOf(workTime);
            }
        }

        //简单处理
        //分析 0:00 到 9:15 的波动情况
        //TODO 获取 车间/写字楼 不工作时的电量 offTimeE
        for (int i = 0; i < 36; i++) {
            Double electricity0 = electricList.get(i).getElectricity();
            Double electricity1 = electricList.get(i + 1).getElectricity();
            Double electricity  = electricity1 - electricity0;
            if (electricity > offTimeE) {
                return electricList.get(i).getUpdateTime();
            }
        }
        return String.valueOf(workTime);
    }

    @Override
    public String closingTime(Long comId) {

        StringBuilder closingTime = null;

        //如果上班时间为空则不上班
        if (workTime(comId).equals(null)) return String.valueOf(closingTime);

        QueryWrapper<SysElectric> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id", comId);
        List<SysElectric> electricList = sysElectricMapper.selectList(wrapper);

        //分析 16:45 到 24:00 的波动情况
        //TODO 获取 车间/写字楼 不工作时的电量 offTimeE
        for (int i = 66; i < electricList.size() - 1; i++) {
            Double electricity0 = electricList.get(i).getElectricity();
            Double electricity1 = electricList.get(i + 1).getElectricity();
            Double electricity  = electricity1 - electricity0;
            if (electricity < offTimeE) {
                return electricList.get(i).getUpdateTime();
            }
        }
        return String.valueOf(closingTime);
    }

    @Override
    public boolean getWeekend(String date) {

        //查询当天所有信息
        QueryWrapper<SysElectric> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("update_time", date);
        List<SysElectric> sysElectricList = sysElectricMapper.selectList(queryWrapper);

        //获取电量
        if (sysElectricList != null && sysElectricList.size() > 0) {
            Double electricity0 = sysElectricList.get(0).getElectricity();
            Double electricity1 = sysElectricList.get(sysElectricList.size() - 1).getElectricity();

            //用电量小于预计值则休息，否则
            if (electricity1 - electricity0 <= maxE0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String companyType(Long comId) {

        String workTime = workTime(comId);
        String closingTime = closingTime(comId);

        //获取上班时间的电量
        QueryWrapper<SysElectric> wrapper0 = new QueryWrapper<>();
        wrapper0.like("update_time", workTime);
        List<SysElectric> sysElectrics0 = sysElectricMapper.selectList(wrapper0);
        Double electricity0 = sysElectrics0.get(0).getElectricity();

        //获取下班时间的电量
        QueryWrapper<SysElectric> wrapper1 = new QueryWrapper<>();
        wrapper1.like("update_time", closingTime);
        List<SysElectric> sysElectrics1 = sysElectricMapper.selectList(wrapper1);
        Double electricity1 = sysElectrics1.get(0).getElectricity();

        //根据用电量判断类型
        //TODO 获取两种类型判断标准
        if(electricity1 - electricity0 > 100) {
            return "生产型";
        }else {
            return "办公型";
        }
    }
}
