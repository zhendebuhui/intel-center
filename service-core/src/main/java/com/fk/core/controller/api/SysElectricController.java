package com.fk.core.controller.api;

import com.fk.common.result.R;
import com.fk.core.service.SysElectricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Api(tags = "电量接口")
@RestController
@RequestMapping("/api/sysElectric/analyse")
@Slf4j
public class SysElectricController {

    @Resource
    private SysElectricService sysElectricService;

    @ApiOperation("某公司上班时间")
    @GetMapping("/getWorkTime/{comId}")
    public R getWorkTime(@PathVariable Long comId) {

        //TODO 早上五点更新一次
        String workTime = sysElectricService.workTime(comId);
        return R.ok().message(workTime);
    }

    @ApiOperation("某公司下班时间")
    @GetMapping("/getClosingTime/{comId}")
    public R getClosingTime(@PathVariable Long comId) {

        //TODO 早上五点更新一次
        String workTime = sysElectricService.closingTime(comId);
        return R.ok().message(workTime);
    }

    @ApiOperation("一天工作时长")
    @GetMapping("/getRunningHour/{comId}")
    public R getAttendanceTime(@PathVariable Long comId) {

        //TODO 公司某天工作时长

        //将String转为固定格式LocalDateTime
        LocalDateTime workTime = LocalDateTime.parse(
                getWorkTime(comId).getMessage(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime closingTime = LocalDateTime.parse(
                getClosingTime(comId).getMessage(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //获取秒数
        long nowSecond = workTime.toEpochSecond(ZoneOffset.ofHours(0));
        long endSecond = closingTime.toEpochSecond(ZoneOffset.ofHours(0));
        long absSeconds = Math.abs(nowSecond - endSecond);

        //获取小时数
        long h = absSeconds / 60 / 60 % 24;

        return R.ok().message("上班时长为：" + h + "小时");
    }

    @ApiOperation("判断公司类型")
    @GetMapping("/getCompanyType/{comId}")
    public R getCompanyType(@PathVariable Long comId) {
        String type = sysElectricService.companyType(comId);
        return R.ok().message(type);
    }

    @ApiOperation("周末情况")
    @GetMapping("/getWeek")
    public R getWeek(String date) {

        boolean result = sysElectricService.getWeekend(date);
        return R.ok().build(result);
    }

    @ApiOperation("工作周期")
    @GetMapping("/productionCycle/{comId}")
    public R getCycle(@PathVariable Long comId) {
        return R.ok();
    }
}
