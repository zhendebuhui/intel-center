package com.fk.core.controller.admin;


import com.fk.common.result.R;
import com.fk.core.pojo.entity.SysUser;
import com.fk.core.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户后台")
@RestController
@RequestMapping("/admin/sysUser")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation("查询全部用户")
    @GetMapping("/findAll")
    public R<List<SysUser>> findAll() {
        List<SysUser> list = sysUserService.list();
        return R.ok(list);
    }
}

