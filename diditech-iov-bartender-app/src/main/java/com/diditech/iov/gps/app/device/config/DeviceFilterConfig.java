package com.diditech.iov.gps.app.device.config;

import dd.monitor.dm.device.IDevice;
import dd.monitor.dm.device.bk.bk200.BK200Device;
import dd.monitor.dm.device.bsj.a5c.A5CDevice;
import dd.monitor.dm.device.bsj.a9.A9Device;
import dd.monitor.dm.device.bsj.dm08d.DM08DDevice;
import dd.monitor.dm.device.bsj.gh01.GH01Device;
import dd.monitor.dm.device.bsj.km02.KM02Device;
import dd.monitor.dm.device.bsj.m11.M11Device;
import dd.monitor.dm.device.bsj.ocar.OcarDevice;
import dd.monitor.dm.device.chl.M230Device;
import dd.monitor.dm.device.gt.dg08.DG08Device;
import dd.monitor.dm.device.gt.gt02.GT02Device;
import dd.monitor.dm.device.gt.gt02d.GT02DDevice;
import dd.monitor.dm.device.gt.gt06.GT06Device;
import dd.monitor.dm.device.gt.gt371.GT371Device;
import dd.monitor.dm.device.gt.gt420.GT420Device;
import dd.monitor.dm.device.gt.gt520.GT520Device;
import dd.monitor.dm.device.gt.gt740.GT740Device;
import dd.monitor.dm.device.gt.gv25.GV25Device;
import dd.monitor.dm.device.gt.gw005a.GW005ADevice;
import dd.monitor.dm.device.gt.kks808.Kks808Device;
import dd.monitor.dm.device.hz.HZDevice;
import dd.monitor.dm.device.jt.jt1078.JT1078Device;
import dd.monitor.dm.device.jt.jt808.JT808Device;
import dd.monitor.dm.device.qyg.QYGDevice;
import dd.monitor.dm.device.sh.g21.G21Device;
import dd.monitor.dm.device.sh.lg30.LG30Device;
import dd.monitor.dm.device.sh.mg50.MG50Device;
import dd.monitor.dm.device.yw.CL10PDevice;
import dd.monitor.dm.device.yz.golo2g.Golo2gDevice;
import dd.monitor.dm.device.yz.golox.GoloxDevice;
import dd.monitor.dm.device.yz.hq.HQDevice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DeviceFilterConfig {

    @Bean("devices")
    public IDevice[] getDevices() {
        return new IDevice[]{
                new HQDevice(), new JT808Device(), new JT1078Device(), new MG50Device(), new BK200Device(),
                new LG30Device(), new A5CDevice(), new M11Device(), new G21Device(), new GT02Device(),
                new GT02DDevice(), new KM02Device(), new GT520Device(), new Golo2gDevice(), new GoloxDevice(),
                new GV25Device(), new DG08Device(), new GT740Device(), new GT371Device(), new GT06Device(), new GW005ADevice(),
                new OcarDevice(), new GH01Device(), new CL10PDevice(), new DM08DDevice(), new M230Device(),
                new HZDevice(), new GT420Device(), new Kks808Device(), new QYGDevice()};
    }

    @Bean("filters")
    public Map<String, IDevice> decodeFilters(@Qualifier("devices") IDevice[] devices) {
        Map<String, IDevice> filters = new HashMap<>();
        Arrays.stream(devices).forEach(iDevice -> filters.put(iDevice.getType(), iDevice));
        return filters;
    }

    @Bean("transcodeDevices")
    public List<String> getTranscodeDevices() {
        return Arrays.asList(
                HQDevice.DEVICE_TYPE_HQ, JT808Device.DEVICE_TYPE_JT808,
                MG50Device.DEVICE_TYPE_JT808, BK200Device.DEVICE_TYPE_BK200,
                LG30Device.DEVICE_TYPE_LG30, OcarDevice.DEVICE_TYPE_OCAR,
                GH01Device.DEVICE_TYPE_GH01, A9Device.DEVICE_TYPE_A9,
                JT1078Device.DEVICE_TYPE_JT1078, M230Device.DEVICE_TYPE_M230,
                HZDevice.DEVICE_TYPE_HZ, Kks808Device.DEVICE_TYPE_KKS808, QYGDevice.DEVICE_TYPE_QYG);
    }

    @Bean("transcodeRule")
    public Map<String, String> getTranscodeRule() {
        Map<String, String> transcodeRuleMapping = new HashMap<>();
        transcodeRuleMapping.put("7e", "7d02");
        transcodeRuleMapping.put("7d", "7d01");
        return transcodeRuleMapping;
    }
}
