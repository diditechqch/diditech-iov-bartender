package com.diditech.iov.gps.app.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Robbin.Ren
 * @description 线性增长数据异常噪点清洗工具测试类
 * @date 2022年10月12日 14:14
 */
public class IncrementalDataFilterTest {

    @Test
    public void test1(){
        int threshold = 500;
        //一个向上异常点
        List<Integer> list = Arrays.asList(109680,144830,109680,109680,109690);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690},IncrementalDataFilter.clean(list,threshold).toArray());

        //一个向下异常点
        list = Arrays.asList(109680,99830,109680,109680,109690);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690},IncrementalDataFilter.clean(list,threshold).toArray());

        //两个连续向上异常点
        list = Arrays.asList(109680,144830,144830,109680,109690);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690},IncrementalDataFilter.clean(list,threshold).toArray());

        //两个连续向下异常点
        list = Arrays.asList(109680,99830,99830,109680,109690);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690},IncrementalDataFilter.clean(list,threshold).toArray());

        //三个连续向上异常点
        list = Arrays.asList(109680,144830,144830,144830,109690,109710,109720);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690,109710,109720},IncrementalDataFilter.clean(list,threshold).toArray());

        //三个连续向下异常点
        list = Arrays.asList(109680,99830,99830,99830,109690,109710,109720);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109680,109680,109690,109710,109720},IncrementalDataFilter.clean(list,threshold).toArray());

        //间隔向上异常点
        list = Arrays.asList(109680,144830,109690,144830,109690,109710,109720);
//        System.out.println("处理前数据：" + list);
        List<Integer> result = IncrementalDataFilter.clean(list,threshold);
//        System.out.println("处理后数据：" + result);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109690,109690,109690,109710,109720},result.toArray());

        //间隔向下异常点
        list = Arrays.asList(109680,99830,109690,99830,109690,109710,109720);
        Assert.assertArrayEquals(new Integer[]{109680,109680,109690,109690,109690,109710,109720},IncrementalDataFilter.clean(list,threshold).toArray());

        //窗口期首个数据是向上异常点
        list = Arrays.asList(144830,109690,109690,109690,109690,109710,109720);
        Assert.assertArrayEquals(new Integer[]{109690,109690,109690,109690,109690,109710,109720},IncrementalDataFilter.clean(list,threshold).toArray());
        //窗口期首位连续数据是向上异常点
        list = Arrays.asList(144830,144830,144830,109690,109690,109710,109720,109730);
        result = IncrementalDataFilter.clean(list,threshold);
        Assert.assertArrayEquals(new Integer[]{109690,109690,109690,109690,109690,109710,109720,109730},result.toArray());

        //窗口期首位跳跃向上异常点数据
        list = Arrays.asList(144830,109690,144830,109690,109690,109710,109720,109730);
        result = IncrementalDataFilter.clean(list,threshold);
        Assert.assertArrayEquals(new Integer[]{109690,109690,109690,109690,109690,109710,109720,109730},result.toArray());

        //窗口期首尾向上异常点数据
        list = Arrays.asList(144830,109690,144830,109690,109690,109710,109720,144830);
        result = IncrementalDataFilter.clean(list,threshold);
        Assert.assertArrayEquals(new Integer[]{109690,109690,109690,109690,109690,109710,109720,109720},result.toArray());

        //首尾向上向下异常点数据
        list = Arrays.asList(222,109690,144830,109690,109690,109710,109720,94830);
        result = IncrementalDataFilter.clean(list,threshold);
        Assert.assertArrayEquals(new Integer[]{109690,109690,109690,109690,109690,109710,109720,109720},result.toArray());

//        System.out.println("处理前数据：" + list);
//
//        System.out.println("处理后数据：" + result);
    }
}
