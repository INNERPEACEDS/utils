package com.wgb.utils.controller.login;

import com.wgb.utils.common.domain.QueryRequest;
import com.wgb.utils.entity.oracle.SysUser;
import com.wgb.utils.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author INNERPEACE
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Resource
    private SysUserService userService;


    @ApiOperation(value = "用户列表")
    @GetMapping("list")
    //@RequiresPermissions("user:list")
    public List<SysUser> userList(QueryRequest request) {
        log.info("进入获取用户列表，pageNum={}，pageSize={}", request.getPageNum(), request.getPageSize());
        if (request.getPageSize() == 0) {
            // request.setPageSize(100);
            // 一页三个，第二页
            request.setPageNum(2);
            request.setPageSize(3);
        }
        List<SysUser> sysUserList = userService.selectByPage(request);
        log.info("列表：{}", sysUserList);
        return sysUserList;
    }

    @GetMapping("check/{username}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findByName(username) == null;
    }


}

