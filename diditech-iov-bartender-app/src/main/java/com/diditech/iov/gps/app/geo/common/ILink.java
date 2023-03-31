package com.diditech.iov.gps.app.geo.common;

import com.diditech.iov.gps.app.core.util.Const;
import org.springframework.util.StringUtils;

public interface ILink {

    default String getSeparator() {
        return Const.SEP_LINE;
    }

    default boolean isInvalid(String lbsInfo) {
        return !StringUtils.isEmpty(lbsInfo)
                && !Const.LBS_FORMAT.matcher(lbsInfo).matches();
    }

    /**
     * 目前只用来校验主基站信息
     * @author hefan
     * @date 2021/10/22 10:46
     */
    default void isInvalidThrowE(String lbsInfo) {
        if (isInvalid(lbsInfo)) {
            throw new RuntimeException("lbs parameters is invalidate");
        }
    }

}
