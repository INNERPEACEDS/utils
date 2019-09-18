package com.wgb.utils.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgb.utils.entity.system.SysUser;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author INNERPEACE
 */
public interface SysUserMapper1 extends BaseMapper<SysUser> {
	SysUser selectOne(String username);
}
