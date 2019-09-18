package com.wgb.utils.service.system.impl;


import com.wgb.utils.dao.oracle.SysRoleMapper;
import com.wgb.utils.entity.oracle.SysRole;
import com.wgb.utils.service.system.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author INNERPEACE
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> findUserRole(String userName) {
        return roleMapper.findUserRole(userName);
    }
}
