package com.wgb.utils.entity.oracle.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author INNERPEACE
 * @date 2019/1/28 18:37
 **/
@Data
public class UserDTO {
    @NotNull
    private String name;

    @NotNull
    private String age;

    @NotNull
    private String sex;
}
