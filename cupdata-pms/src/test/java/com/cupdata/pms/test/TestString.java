package com.cupdata.pms.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * Created by Wsork on 2021/1/29 13:56.
 **/
public class TestString {


    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("StringUtils");
        list.add("join");
        list.add("222");
        String join = StringUtils.join(list,"-");//传入String类型的List集合，使用"-"号拼接
        System.out.println(join);

        String[] s = new String[]{"333","444"};//传入String类型的数组，使用"-"号拼接
        String join2 = StringUtils.join(s,"-");
        System.out.println(join2);

    }
}
