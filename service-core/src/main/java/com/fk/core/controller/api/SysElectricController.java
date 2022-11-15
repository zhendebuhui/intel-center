package com.fk.core.controller.api;

import cn.hutool.core.date.DateTime;
import com.fk.common.result.R;
import com.fk.core.service.SysElectricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "电量接口")
@RestController
@RequestMapping("/api/sysElectric/analyse")
@Slf4j
public class SysElectricController {

    @Resource
    private SysElectricService sysElectricService;

    @ApiOperation("上班时间")
    @GetMapping("/getWorkTime")
    public R getWorkTime() {
        return null;
    }

    @ApiOperation("工作时长")
    @GetMapping("/getRunningHour")
    public R getAttendanceTime() {
        DateTime dateTime = new DateTime();

        return R.ok().message(dateTime.toString());
    }

}
