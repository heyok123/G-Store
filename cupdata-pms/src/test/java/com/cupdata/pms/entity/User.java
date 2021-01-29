package com.cupdata.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @Author: wsz
 * @Date: 2021/1/19 13:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer pid;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
