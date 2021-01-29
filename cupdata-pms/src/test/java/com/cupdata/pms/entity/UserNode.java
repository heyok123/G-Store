package com.cupdata.pms.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 树形结构数据
 * @Author: wsz
 * @Date: 2021/1/19 14:21
 **/
@Data
public class UserNode extends User {

    private List<UserNode> children;
}
