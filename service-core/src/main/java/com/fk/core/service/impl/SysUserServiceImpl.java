package com.fk.core.service.impl;

import com.fk.core.pojo.entity.SysUser;
import com.fk.core.mapper.SysUserMapper;
import com.fk.core.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
