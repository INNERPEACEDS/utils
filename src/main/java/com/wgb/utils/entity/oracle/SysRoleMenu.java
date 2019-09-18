package com.wgb.utils.entity.oracle;

import java.io.Serializable;

public class SysRoleMenu implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ROLE_MENU.ROLE_ID
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ROLE_MENU.MENU_ID
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ROLE_MENU.ROLE_ID
     *
     * @return the value of SYS_ROLE_MENU.ROLE_ID
     *
     * @mbggenerated
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ROLE_MENU.ROLE_ID
     *
     * @param roleId the value for SYS_ROLE_MENU.ROLE_ID
     *
     * @mbggenerated
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ROLE_MENU.MENU_ID
     *
     * @return the value of SYS_ROLE_MENU.MENU_ID
     *
     * @mbggenerated
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ROLE_MENU.MENU_ID
     *
     * @param menuId the value for SYS_ROLE_MENU.MENU_ID
     *
     * @mbggenerated
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}