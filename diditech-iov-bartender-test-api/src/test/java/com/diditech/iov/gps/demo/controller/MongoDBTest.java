package com.diditech.iov.gps.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.GisUtils;
import com.diditech.iov.gps.common.domain.AccSiteInfo;
import com.diditech.iov.gps.demo.repository.AccMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2021/9/2 <br>
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoDBTest {

    private static final String collection_name = "geo";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AccMapper mapper;

    @Test
    public void write() {
        String jsonArea = "[{\"lng\":120.454362,\"lat\":36.121823},{\"lng\":120.455355,\"lat\":36.12222},{\"lng\":120.455984,\"lat\":36.121615},{\"lng\":120.454879,\"lat\":36.121043}]";
        List<Coordinate> coors = JSON.parseArray(jsonArea, Coordinate.class);
        // 区域必须为封闭区域，即开始坐标与最后坐标必须相同
        coors.add(coors.get(0));
        List<Point> pointList = coors.stream()
                .map(item -> new Point(item.getLng(), item.getLat()))
                .collect(Collectors.toList());
        GreenArea greenArea = new GreenArea();
        greenArea.setAreaId(101);
        greenArea.setAreaName("区域名称2");
        greenArea.setType(2);
        greenArea.setPoints(new GeoJsonPolygon(pointList));
        greenArea.setLocBd(new GeoJsonPoint(120.455984, 36.121615));
        mongoTemplate.insert(greenArea, collection_name);
    }

    @Test
    public void intersect() {
        GeoJsonPoint point = new GeoJsonPoint(120.455984, 36.121615);
        Criteria criteria = Criteria.where("points").intersects(point);
        Query query = new Query();
        query.addCriteria(criteria);
        List<GreenArea> theList = mongoTemplate.find(query, GreenArea.class, collection_name);
        GreenArea theOne = mongoTemplate.findOne(query, GreenArea.class, collection_name);
        System.out.println(JSON.toJSONString(theList));
        System.out.println(JSON.toJSONString(theOne));
    }

    @Test
    public void saveLinYi() {
        try {
            File file = new ClassPathResource("临沂市行政区划.txt").getFile();
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            GreenArea greenArea;
            AccSiteInfo siteInfo;
            int i = 1;
            while (StrUtil.isNotBlank(line = br.readLine())) {
                String[] items = line.split("\\|");
                String[] pointStr = items[2].split(",");
                String areaStr = items[3];
                List<String[]> list = Arrays.stream(areaStr.split(";"))
                        .map(item -> item.split(","))
                        .collect(Collectors.toList());
                List<Point> coordinates = list.stream()
                        .map(item -> new Point(Convert.toDouble(item[0]), Convert.toDouble(item[1])))
                        .collect(Collectors.toList());

                List<GisUtils.Point> area = list.stream()
                        .map(item -> new GisUtils.Point(Convert.toDouble(item[0]), Convert.toDouble(item[1])))
                        .collect(Collectors.toList());

                greenArea = new GreenArea();
                greenArea.setAreaId(i);
                greenArea.setAreaName(items[0]);
                greenArea.setType(1);
                greenArea.setLocBd(new GeoJsonPoint(Convert.toDouble(pointStr[0]), Convert.toDouble(pointStr[1])));
                greenArea.setPoints(new GeoJsonPolygon(coordinates));
                mongoTemplate.insert(greenArea, collection_name);

                siteInfo = new AccSiteInfo();
                siteInfo.setId(i);
                siteInfo.setHubAddress(items[2]);
                siteInfo.setHubArea(items[0]);
                siteInfo.setHubName(items[1]);
                siteInfo.setHubLat(Convert.toDouble(pointStr[1]));
                siteInfo.setHubLng(Convert.toDouble(pointStr[0]));
                siteInfo.setHubType(1);
                siteInfo.setPoints(area.toString());
                mapper.insertSiteInfo(siteInfo);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveChargers() {
        try {
            File file = new ClassPathResource("临沂停靠站.txt").getFile();
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            GreenArea greenArea;
            AccSiteInfo siteInfo;
            int i = 200;
            while (StrUtil.isNotBlank(line = br.readLine())) {
                String[] items = line.split("\\|");
                String[] pointStr = items[3].split(",");
                GisUtils.Point gisPoint = new GisUtils.Point(Convert.toDouble(pointStr[0]), Convert.toDouble(pointStr[1]));
                List<GisUtils.Point> area = GisUtils.getInstance().getCircularAreaMapPoints(gisPoint, 0.5, 40);
                area.add(area.get(0));
                List<Point> coordinates = area.stream()
                        .map(item -> new Point(item.getLng(), item.getLat()))
                        .collect(Collectors.toList());
                greenArea = new GreenArea();
                greenArea.setAreaId(i);
                greenArea.setAreaName(items[0]);
                greenArea.setType(3); // 区域类型 1:补贴 2:充电站 3:交通枢纽 4:停靠站
                greenArea.setLocBd(new GeoJsonPoint(Convert.toDouble(pointStr[0]), Convert.toDouble(pointStr[1])));
                greenArea.setPoints(new GeoJsonPolygon(coordinates));
                mongoTemplate.insert(greenArea, collection_name);

                siteInfo = new AccSiteInfo();
                siteInfo.setId(i);
                siteInfo.setHubAddress(items[2]);
                siteInfo.setHubArea(items[0]);
                siteInfo.setHubName(items[1]);
                siteInfo.setHubLat(Convert.toDouble(pointStr[1]));
                siteInfo.setHubLng(Convert.toDouble(pointStr[0]));
                siteInfo.setHubType(4);
                siteInfo.setPoints(area.toString());
                mapper.insertSiteInfo(siteInfo);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Data
    @AllArgsConstructor
    public class Coordinate {
        private double lng;
        private double lat;
    }

    @Data
    public class GreenArea {
        private Integer areaId;
        private String areaName;
        private Integer type;
        private GeoJsonPolygon points;
        private GeoJsonPoint locBd;
    }


}
