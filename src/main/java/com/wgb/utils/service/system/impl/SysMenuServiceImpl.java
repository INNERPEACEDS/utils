package com.wgb.utils.service.system.impl;

import com.wgb.utils.dao.oracle.SysMenuMapper;
import com.wgb.utils.entity.oracle.SysMenu;
import com.wgb.utils.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author INNERPEACE
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findUserPermissions(String userName) {
        return menuMapper.findUserPermissions(userName);
    }
}
