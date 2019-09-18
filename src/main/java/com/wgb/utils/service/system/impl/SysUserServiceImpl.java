package com.wgb.utils.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wgb.utils.common.domain.QueryRequest;
import com.wgb.utils.dao.oracle.SysUserMapper;
import com.wgb.utils.entity.oracle.SysUser;
import com.wgb.utils.entity.oracle.SysUserExample;
import com.wgb.utils.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author INNERPEACE
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectByPage(QueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setOrderByClause("CRATE_TIME desc");
        // sysUserExample.createCriteria();
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        return sysUserList;
    }

    @Override
    public SysUser findByName(String userName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(userName);
        List<SysUser> sysUsers =  sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() == 1) {
            return sysUsers.get(0);
        }
        return null;
    }

}
