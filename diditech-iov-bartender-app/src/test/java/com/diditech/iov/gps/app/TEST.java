package com.diditech.iov.gps.app;

import cn.hutool.core.collection.CollUtil;
import com.diditech.iov.gps.api.gpslog.domain.GpsLog;
import com.diditech.iov.gps.app.gpslog.repository.GpsLogMapper;
import dd.utils.CRC;
import dd.utils.StrUtil;
import dd.utils.Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/5/10 <br>
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TEST {

    String GV25_22_LOGIN_FORMAT = "78781101%sa00132020006%s0d0a";

    @Autowired
    private GpsLogMapper gpsLogMapper;

    @SneakyThrows
    @Test
    public void test() {
        String beginTime = "230510070000";
        String endTime = "230510150000";
        String tableName = "gps_log.gps_log_2305";


        File file = org.springframework.util.ResourceUtils.getFile("classpath:date1.txt");
        FileReader fr = new FileReader(file);   //reads the file
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String thisLine;
        String deviceNum = "";
        while ((thisLine = br.readLine()) != null) {
            deviceNum = thisLine;
            List<GpsLog> logs = gpsLogMapper.getGpsLogList(deviceNum, beginTime, endTime, tableName);
            List<String> msg = logs.stream().map(item -> item.getMessage()).collect(Collectors.toList());
            if (CollUtil.isEmpty(msg)) {
                continue;
            }
            BufferedWriter out = new BufferedWriter(new FileWriter("D:\\My Desktop\\易企用车\\" + deviceNum + ".txt"));
            out.write(generateLogin(Util.asLong(deviceNum)) + "\r\n");

            for (int i = 0; i < msg.size(); i++) {
                String item = msg.get(i);
                if (i % 2 == 0) {
                    out.write(item + "\r\n");
                } else {
                    out.write(item);
                }
            }
            out.close();
        }
    }

    public String generateLogin(Long deviceNum) {
        String num = StrUtil.padLeft(Util.asString(deviceNum), 16, '0');
        String pkg = String.format(GV25_22_LOGIN_FORMAT, num, "xx");
        int crcResult = CRC.crc_itu(StrUtil.hexStringToBytes(pkg.substring(4, pkg.length() - 6)));
        String crc = StrUtil.decIntToHexStr(crcResult, 4);
        pkg = String.format(GV25_22_LOGIN_FORMAT, num, crc);
        return pkg;
    }
}
