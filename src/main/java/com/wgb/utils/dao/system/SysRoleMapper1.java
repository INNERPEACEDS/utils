package com.wgb.utils.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgb.utils.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author INNERPEACE
 */
public interface SysRoleMapper1 extends BaseMapper<SysRole> {

    List<SysRole> findUserRole(String userName);
}
