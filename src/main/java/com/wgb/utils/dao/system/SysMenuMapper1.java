package com.wgb.utils.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgb.utils.entity.system.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author INNERPEACE
 */
public interface SysMenuMapper1 extends BaseMapper<SysMenu> {

    List<SysMenu> findUserPermissions(String userName);
}
