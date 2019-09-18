package com.wgb.utils.dao.oracle;

import com.wgb.utils.entity.oracle.SysRole;
import com.wgb.utils.entity.oracle.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int countByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int deleteByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int insert(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int insertSelective(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    List<SysRole> selectByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    SysRole selectByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRole record);

    List<SysRole> findUserRole(String userName);
}