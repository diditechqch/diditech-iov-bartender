package com.diditech.iov.gps.app.geo.wifi.service.impl;

import com.diditech.iov.gps.app.core.util.Const;
import org.springframework.stereotype.Service;

@Service("CelLocationWifiService")
public class CelLocationWifiServiceImpl extends GaodeWifiServiceImpl {

    @Override
    public String getSeparator() {
        return Const.SEP_SEMICOLON;
    }

}
