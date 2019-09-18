package com.wgb.utils.service.system;

import com.wgb.utils.entity.oracle.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjy
 * @since 2019-03-18
 */
public interface SysRoleService {

    List<SysRole> findUserRole(String userName);
}
