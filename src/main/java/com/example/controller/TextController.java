package com.example.controller;import com.alibaba.fastjson.JSON;import com.alibaba.fastjson.annotation.JSONField;import com.alibaba.fastjson.annotation.JSONType;import com.alibaba.fastjson.serializer.SerializerFeature;import lombok.Data;import lombok.experimental.Accessors;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;/** * 测试控制器. * * @author 易富军 */public class TextController {    public static void main(String[] args) {        List<String> list = new ArrayList<>();        try {            if (null == list && list.isEmpty()) {                throw new Exception("报错了");            }            System.out.println("List判空成功");        } catch (Exception e) {            System.out.println(e);        }    }    public static int[] twoSum(int[] nums, int target) {        // 如果只有两个值就直接返回        if (nums.length == 2) {            return new int[]{0, 1};        }        Map<Integer, Integer> map = new HashMap<>();        Integer i = 0;        for (Integer integer : nums) {            map.put(integer, i);            if (map.containsKey(target - integer)) {                return new int[]{map.get(target - integer), i};            }            i++;        }        return null;    }    @Data    @Accessors(chain = true)    public static class CameraVO {        @JSONField(name = "序号aaa")        private Integer id;        @JSONField(name = "服务器bbb")        private String instance;    }}