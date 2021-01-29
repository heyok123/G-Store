package com.cupdata.pms.test;

import com.alibaba.fastjson.JSON;
import com.cupdata.pms.entity.User;
import com.cupdata.pms.entity.UserNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: Stream流.. lambda
 * Created by Wsork on 2021/1/29 10:46.
 **/
public class TestStream {

    @Test
    public void testCreate() {
        List<User> users = Arrays.asList(
                new User(1, "jack"),
                new User(2, "tom"),
                new User(3, "jolin")
        );
        // 1.串行流
//        Stream<User> stream = users.stream();
        // 2.并行流
//        Stream<User> userStream = users.parallelStream();

        // filter: 对Stream中的元素进行过滤操作
        List<User> usersFilter = users.stream().filter(user -> user.getId() > 1).collect(Collectors.toList());
        usersFilter.forEach(System.out::println);

        // map: 对Stream中的元素进行转换处理后获取
        List<Integer> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toList());
        userIds.forEach(System.out::println);

        // limit: 从Stream中获取指定数量的元素
        List<User> userLimit = users.stream().limit(2).collect(Collectors.toList());
        userLimit.forEach(System.out::println);

        //count: 仅获取Stream中元素的个数
        System.out.println("i = " + users.stream().count());
        long j = users.stream()
                .filter(user -> user.getName().contains("j"))
                .count();
        System.out.println("j = " + j);

        //anyMatch: 判断的条件里，任意一个元素成功，返回true
        boolean b1 = users.stream().anyMatch(user -> user.getName().contains("j"));
        System.out.println("b1 = " + b1);

        //allMatch: 判断条件里的元素，所有的都是，返回true
        boolean b2 = users.stream().allMatch(user -> user.getName().length() > 3);
        System.out.println("b2 = " + b2);

        //noneMatch: 跟allMatch相反，判断条件里的元素，所有的都不是，返回true
        boolean b3 = users.stream().noneMatch(user -> user.getName().isEmpty());
        System.out.println("b3 = " + b3);

        // sorted: 对Stream中元素按指定规则进行排序
        users.stream()
                .sorted((o1,o2) -> {return o2.getId().compareTo(o1.getId());})
                .collect(Collectors.toList());
        users.forEach(System.out::println);

        // skip
        List<User> usersSkip = users.stream()
                .skip(2).collect(Collectors.toList());
        usersSkip.forEach(System.out::println);

        // 用collect方法将List转成map
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        System.out.println("userMap = " + userMap);
        System.out.println("userStr = " + JSON.toJSONString(userMap));

    }

    /**
     * @Description: 定义获取树形结构的方法
     */
    public List<UserNode> treeNode(){
        List<User> users = Arrays.asList(
                new User(1, "jack",0),
                new User(2, "tom",1),
                new User(3, "jolin", 1),
                new User(4, "jay", 2),
                new User(5, "tomic", 3)
        );
        return users.stream()
                .filter(user -> user.getPid().equals(0))
                .map(user -> convert(user, users))
                .collect(Collectors.toList());
    }

    /**
     * @Description: 为每个用户设置子用户
     */
    private UserNode convert(User user, List<User> users) {
        UserNode userNode = new UserNode();
        BeanUtils.copyProperties(user,userNode);
        List<UserNode> list = users.stream()
                .filter(subUser -> subUser.getPid().equals(userNode.getId()))
                .map(subUser -> convert(subUser, users))
                .collect(Collectors.toList());
        userNode.setChildren(list);
        return userNode;
    }

    @Test
    public void testTree() {
        List<UserNode> userNodes = this.treeNode();
//        userNodes.forEach(System.out::println);
        System.out.println("JSON.toJSONString(userNodes) = " + JSON.toJSONString(userNodes));
    }


}
