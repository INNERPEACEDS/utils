package com.wgb.utils.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wgb.utils.common.domain.QueryRequest;
import com.wgb.utils.entity.oracle.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author INNERPEACE
 */
public interface SysUserService {


    List<SysUser> selectByPage(QueryRequest request);

    SysUser findByName(String userName);
}
