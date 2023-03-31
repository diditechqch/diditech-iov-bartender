package com.diditech.iov.gps.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import dd.utils.Coordinate;
import dd.utils.GISFixUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mongo 行政区划库测试
 * @author zhjd <br>
 * @date 2020/9/4 <br>
 */
@SpringBootTest
public class ApiControllerTest {

    String url = "http://api.map.baidu.com/geocoding/v3/?address=%s&output=json&ak=qVknMdbVtFkolj8rxASrPr59tHxymY2k";

    String getRegions = "http://localhost:8081/api-test/regions?bdLng=%s&bdLat=%s";

    String[] special = {"北京市", "天津市", "重庆市", "上海市", "香港特别行政区", "澳门特别行政区", "台湾省", "香港"};

    List<String> regionList = new ArrayList<>();
    List<String> locationList = new ArrayList<>();

    @Before
    public void loadRegions() {
        try {
            File file = new ClassPathResource("regions_all.txt").getFile();
//            File file = new File("regions_all.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while (StrUtil.isNotBlank(line = br.readLine())) {
                regionList.add(line); //line feed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new ClassPathResource("location_ramdom.txt").getFile();
//            File file = new File("regions_all.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while (StrUtil.isNotBlank(line = br.readLine())) {
                locationList.add(line); //line feed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegionLocation() {
        String[] c;
        List<JSONObject> result;
        Coordinate bdCoord = null;
        for (String loc : locationList) {
            try {
                c = loc.split(",");
                Coordinate baseCoordinate = new Coordinate(Convert.toDouble(c[1]), Convert.toDouble(c[0])); // new Coordinate(lat,lng)
                bdCoord = GISFixUtil.convertGCJ02ToBD09(baseCoordinate);
                result = JSONObject.parseArray(HttpUtil.get(String.format(getRegions, bdCoord.lng, bdCoord.lat)), JSONObject.class);
                Assert.assertEquals(3, result.size());
                int check = 0;
                for (JSONObject item : result) {
                    if (item.getIntValue("regionLevel") == 1) {
                        check++;
                        continue;
                    }
                    if (item.getIntValue("regionLevel") == 2) {
                        check++;
                        continue;
                    }
                    if (item.getIntValue("regionLevel") == 3) {
                        check++;
                        continue;
                    }
                }
                Assert.assertTrue(!Arrays.asList(special).contains(loc) && check == 3);
                Assert.assertTrue(Arrays.asList(special).contains(loc) && check == 2);
            } catch (AssertionError e) {
                System.out.println(loc);
                System.out.println(bdCoord.lng + ", " + bdCoord.lat);
                throw e;
            } catch (Exception e) {
                System.out.println(loc);
                e.printStackTrace();
                throw e;
            }
        }
    }

    @Test
    public void testRegionDB() {
        for (String name : regionList) {
            getDistinction(name);
        }
    }

    public void getDistinction(String name) {
        try {
            JSONObject object = JSONObject.parseObject(HttpUtil.get(String.format(url, name)), JSONObject.class);
            JSONObject location = object.getJSONObject("result").getJSONObject("location");
            double lng = location.getDouble("lng");
            double lat = location.getDouble("lat");

            List<JSONObject> result = JSONObject.parseArray(HttpUtil.get(String.format(getRegions, lng, lat)), JSONObject.class);

            if (Arrays.asList(special).contains(name)) {
                Assert.assertEquals(2, result.size());
            } else {
                Assert.assertEquals(3, result.size());
            }

            int check = 0;
            boolean nameCheck = false;
            for (JSONObject item : result) {
                if (!nameCheck) {
                    nameCheck = item.getString("regionName").contains(name.substring(0, name.length() - 1));
                }
                if (item.getIntValue("regionLevel") == 1) {
                    check++;
                    continue;
                }
                if (item.getIntValue("regionLevel") == 2) {
                    check = check + 2;
                    continue;
                }
                if (item.getIntValue("regionLevel") == 3) {
                    check = check + 3;
                    continue;
                }
            }

            if (Arrays.asList(special).contains(name)) {
                Assert.assertTrue(nameCheck && check == 5);
            } else {
                Assert.assertTrue(nameCheck && check == 6);
            }
        } catch (AssertionError | Exception e) {
            System.out.println(name);
            e.printStackTrace();
            throw e;
        }
    }
}