package com.wgb.utils.service.system;


import com.wgb.utils.entity.oracle.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author INNERPEACE
 */
public interface SysMenuService {

    List<SysMenu> findUserPermissions(String userName);
}
