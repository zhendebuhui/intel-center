package com.fk.core.controller.api;

import com.fk.common.result.R;
import com.fk.core.service.SysCompanyService;
import com.fk.core.service.SysElectricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "公司接口")
@RestController
@RequestMapping("/api/sysCompany/analyse")
@Slf4j
public class SysCompanyController {
}
