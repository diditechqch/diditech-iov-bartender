package com.diditech.iov.gps.app.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Robbin.Ren
 * @date 2022年10月12日 13:51
 * @description 线性增长数据异常噪点清洗工具类
 * 算法说明：
 * 1.计算待清洗数据集合中位数，
 * 2.每个数据和中位数做差，形成n*3矩阵，
 * 3.差值和线性临值阈值比较(如油耗两个采集点间正常为0~200左右，大部分在10~50，可以将阈值设定为500防止油耗过高或定位点确实情况)
 * 4.过滤ABS(差值)>阈值的数据，并赋给上下相邻点的正确值
 * 如下案例，中位数(median)=10050，阈值threshold=500
 * 原始数据：
 * index    value   diffvalue
 * 0        10000       -55
 * 1        10020       -35
 * 2        10050       -5
 * 3        19000       8945
 * 4        10060       5
 * 5        10070       15
 * 处理后数据：
 * index    value   diffvalue
 * 0        10000       -50
 * 1        10020       -30
 * 2        10050       0
 * 3        10050       0
 * 4        10060       10
 * 5        10070       20
 */
@Slf4j
public class IncrementalDataFilter {


    /**
     * 清洗线性增长数据
     * @param intArr 待清洗集合
     * @param threshold 和中位数差阈值
     * @return
     */
    public static List<Integer> clean(List<Integer> intArr, int threshold){
        //参数空判断
        Optional.ofNullable(intArr).orElseThrow(() -> new IllegalArgumentException("intArr参数为空"));
        //构建增量矩阵
        IncrementalData data = IncrementalData.build(intArr);
        //算法处理
        return process(data, threshold).getSrcDatas();
    }

    /**
     * 清晰算法
     * @param data
     * @param threshold
     * @return
     */
    private static IncrementalData process(IncrementalData data, int threshold){
        for (int i = 0; i < data.getSize(); i++) {
            //差值和阈值比较
            if(Math.abs(data.getDiffData(i)) > threshold){
                //窗口期数据首位是噪点的情况，向下取到第一个正确的值，并赋值
                if(i == 0){
                    for (int j = 0; j < data.getDiffDatas().size(); j++) {
                        if(Math.abs(data.getDiffData(j))<=threshold){
                            data.setSrcData(i, data.getSrcData(j));
                            break;
                        }
                    }
                } else {
                    //窗口期数据不是首位噪点的情况，向上取正确的值，并赋值
                    data.setSrcData(i, data.getSrcData(i-1));
                }
            }
        }
        return data;
    }

    /**
     * 特殊设备类型需要对油耗做处理
     * @param obdFuels 待清洗集合
     * @param threshold 和中位数差阈值
     * @return
     */
    public static int doDiff(List<Integer> obdFuels, int threshold){
        int max = 0;
        int min = 0;
        if (obdFuels.size() <= 20) {
            List<Integer> result = IncrementalDataFilter.clean(obdFuels, threshold);
            max = Collections.max(result);
            min = Collections.min(result);
        } else {
            List<Integer> begin = obdFuels.subList(0, 10);
            List<Integer> end = obdFuels.subList(obdFuels.size() - 10, obdFuels.size());
            List<Integer> beginResult = IncrementalDataFilter.clean(begin, threshold);
            List<Integer> endResult = IncrementalDataFilter.clean(end, threshold);
            max = Collections.max(endResult);
            min = Collections.min(beginResult);
        }
        return max - min;
    }
}

/**
 * 增量数据矩阵内部类
 */
class IncrementalData {

    //原始数据集
    private List<Integer> srcData;

    //数据集长度
    private int size;

    //差值数据集
    private List<Integer> diffData;

    // 中位数
    private Integer median;

    private IncrementalData(){}

    /**
     * 构建方法
     * @param srcData  待清洗数据集
     * @return
     */
    public static IncrementalData build(List<Integer> srcData){
        Optional.ofNullable(srcData).orElseThrow(() -> new IllegalArgumentException("srcData参数为空"));
        IncrementalData data = new IncrementalData();
        data.srcData = srcData;
        data.diffData = new ArrayList<>(srcData.size());
        //计算中位数
        int[] array = data.srcData.stream().mapToInt(i->i).toArray();
        Integer[] integers = Arrays.stream(array).boxed().toArray(Integer[]::new);
        data.median = median(integers);
        //计算差值
        for (Integer srcDatum : srcData) {
            data.diffData.add(srcDatum - data.median);
        }

        return data;
    }

    /**
     * 获取数据集中位数
     * @param numArray 数据集
     * @return 中位数
     */
    private static Integer median(Integer[] numArray){
        Arrays.sort(numArray);
        Integer median;
        if (numArray.length % 2 == 0)
            median = (numArray[numArray.length / 2] + numArray[numArray.length / 2 - 1]) / 2;
        else
            median = numArray[numArray.length / 2];
        return median;
    }

    /**
     * 重新构建差分数据
     * 监听原始数据集，如有变化重新从变化点index开始构建以下的差分数据集
     * @param index 监听变化的原始数据集索引
     */
    private void rebuidDiffData(int index){
        //重新计算中位数
        int[] array = this.srcData.stream().mapToInt(i->i).toArray();
        Integer[] integers = Arrays.stream(array).boxed().toArray(Integer[]::new);
        this.median = median(integers);
        //重新计算差值
        for (int i = index; i < srcData.size(); i++) {
            diffData.set(i,srcData.get(i) - this.median);
        }
    }

    /**
     * 获取集合大小
     * @return
     */
    public int getSize(){
        return srcData.size();
    }

    /**
     * 根据索引获取差分数据
     * @param index
     * @return
     */
    public Integer getDiffData(int index){
        return diffData.get(index);
    }

    /**
     * 设置差分数据
     * @param index 差分数据索引
     * @param value 差分数据值
     */
    public void setDiffData(int index, Integer value){
        diffData.set(index, value);
    }

    /**
     * 获取值数据
     * @param index 值数据索引
     * @return
     */
    public Integer getSrcData(int index){
        return srcData.get(index);
    }

    /**
     * 设置值数据
     * @param index 值数据索引
     * @param value 值
     */
    public void setSrcData(int index, Integer value){
        srcData.set(index,value);
        rebuidDiffData(index);
    }

    /**
     * 获取值数据集合
     * @return
     */
    public List<Integer> getSrcDatas(){
        return srcData;
    }

    /**
     * 获取差分数据集合
     * @return
     */
    List<Integer> getDiffDatas(){
        return diffData;
    }

}
