package com.hello.starter.satoken.core;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.hello.common.core.enums.UserType;
import com.hello.common.core.exception.BusinessException;
import com.hello.common.core.pojo.LoginUser;
import com.hello.common.core.service.PermissionService;
import com.hello.starter.satoken.util.LoginHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 权限管理实现类
 *
 * @author Lion Li
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || !loginUser.getLoginId().equals(loginId)) {
            PermissionService permissionService = getPermissionService();
            if (ObjectUtil.isNotNull(permissionService)) {
                List<String> list = StrUtil.split(loginId.toString(), ":");
                return new ArrayList<>(permissionService.getMenuPermission(Long.parseLong(list.get(1))));
            } else {
                throw new BusinessException(500, "PermissionService 实现类不存在");
            }
        }
        if (UserType.APP_USER.equals(loginUser.getUserType())) {
            // 其他端 自行根据业务编写
        }
        // SYS_USER 默认返回权限
        return new ArrayList<>(loginUser.getMenuPermission());
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || !loginUser.getLoginId().equals(loginId)) {
            PermissionService permissionService = getPermissionService();
            if (ObjectUtil.isNotNull(permissionService)) {
                List<String> list = StrUtil.split(loginId.toString(), ":");
                return new ArrayList<>(permissionService.getRolePermission(Long.parseLong(list.get(1))));
            } else {
                throw new BusinessException(500, "PermissionService 实现类不存在");
            }
        }
        if (UserType.APP_USER.equals(loginUser.getUserType())) {
            // 其他端 自行根据业务编写
        }
        // SYS_USER 默认返回权限
        return new ArrayList<>(loginUser.getRolePermission());
    }

    private PermissionService getPermissionService() {
        try {
            return SpringUtil.getBean(PermissionService.class);
        } catch (Exception e) {
            return null;
        }
    }

}
