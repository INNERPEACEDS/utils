package com.wgb.utils.dao.oracle;

import com.wgb.utils.entity.oracle.SysMenu;
import com.wgb.utils.entity.oracle.SysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int countByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int deleteByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int insertSelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    List<SysMenu> selectByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    List<SysMenu> findUserPermissions(String userName);
}