package com.fk.core.controller.admin;

import cn.hutool.core.util.RandomUtil;
import com.fk.common.result.R;
import com.fk.core.pojo.entity.SysElectric;
import com.fk.core.service.SysElectricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Api(tags = "电量后台")
@RestController
@RequestMapping("/admin/sysElectric")
@Slf4j
public class ElectricController {

    @Resource
    private SysElectricService sysElectricService;

    @ApiOperation("数据清洗")
    @PostMapping("/dataClean")
    public R DClean() {
        sysElectricService.DClean();
        return R.ok();
    }

    @ApiOperation("添加100条数据")
    @PostMapping("/addEle/{comId}")
    public R addEle(@PathVariable Long comId) {
        LocalDateTime time = LocalDateTime.now().withNano(0);
        Double numEle = 100.0;
        for (int i = 0; i < 100; i++) {
            SysElectric electric = new SysElectric()
                    .setComId(comId)
                    .setElectricity(numEle)
                    .setUpdateTime(time.toString());

            numEle += RandomUtil.randomDouble(5.0, 10.0);
            time = time.plusMinutes(15);
            sysElectricService.save(electric);
        }
        return R.ok().message("添加成功");
    }
    @ApiOperation("删除数据")
    @DeleteMapping("/delEle/{id}")
    public R delEle(@PathVariable Long id) {
        sysElectricService.removeById(id);
        return R.ok().message("删除成功");
    }
}

