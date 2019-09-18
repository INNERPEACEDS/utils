package com.wgb.utils.entity.oracle;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.USER_ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.USERNAME
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.PASSWORD
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.EMAIL
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.MOBILE
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.STATUS
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.LAST_LOGIN_TIME
     *
     * @mbggenerated
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.CRATE_TIME
     *
     * @mbggenerated
     */
    private Date crateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.MODIFY_TIME
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.USER_ID
     *
     * @return the value of SYS_USER.USER_ID
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.USER_ID
     *
     * @param userId the value for SYS_USER.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.USERNAME
     *
     * @return the value of SYS_USER.USERNAME
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.USERNAME
     *
     * @param username the value for SYS_USER.USERNAME
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.PASSWORD
     *
     * @return the value of SYS_USER.PASSWORD
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.PASSWORD
     *
     * @param password the value for SYS_USER.PASSWORD
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.EMAIL
     *
     * @return the value of SYS_USER.EMAIL
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.EMAIL
     *
     * @param email the value for SYS_USER.EMAIL
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.MOBILE
     *
     * @return the value of SYS_USER.MOBILE
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.MOBILE
     *
     * @param mobile the value for SYS_USER.MOBILE
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.STATUS
     *
     * @return the value of SYS_USER.STATUS
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.STATUS
     *
     * @param status the value for SYS_USER.STATUS
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.LAST_LOGIN_TIME
     *
     * @return the value of SYS_USER.LAST_LOGIN_TIME
     *
     * @mbggenerated
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.LAST_LOGIN_TIME
     *
     * @param lastLoginTime the value for SYS_USER.LAST_LOGIN_TIME
     *
     * @mbggenerated
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.CRATE_TIME
     *
     * @return the value of SYS_USER.CRATE_TIME
     *
     * @mbggenerated
     */
    public Date getCrateTime() {
        return crateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.CRATE_TIME
     *
     * @param crateTime the value for SYS_USER.CRATE_TIME
     *
     * @mbggenerated
     */
    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.MODIFY_TIME
     *
     * @return the value of SYS_USER.MODIFY_TIME
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.MODIFY_TIME
     *
     * @param modifyTime the value for SYS_USER.MODIFY_TIME
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}