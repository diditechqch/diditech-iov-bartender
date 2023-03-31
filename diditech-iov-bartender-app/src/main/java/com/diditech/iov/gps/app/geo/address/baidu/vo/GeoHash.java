package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class GeoHash {

    private String id;

    private double lng;

    private double lat;

    private String geoCode8;

    private String address;

    private String source;

    private String addrType;

    private String addrVers;

    private Date createTime;


    public GeoHash(double lng, double lat, String geoCode8, String address, String source, String addrType,
                   String addrVers, Date createTime) {
        super();
        this.lng = lng;
        this.lat = lat;
        this.geoCode8 = geoCode8;
        this.address = address;
        this.source = source;
        this.addrType = addrType;
        this.addrVers = addrVers;
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addrType == null) ? 0 : addrType.hashCode());
        result = prime * result + ((addrVers == null) ? 0 : addrVers.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((geoCode8 == null) ? 0 : geoCode8.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GeoHash other = (GeoHash) obj;
        if (addrType == null) {
            if (other.addrType != null)
                return false;
        } else if (!addrType.equals(other.addrType))
            return false;
        if (addrVers == null) {
            if (other.addrVers != null)
                return false;
        } else if (!addrVers.equals(other.addrVers))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (geoCode8 == null) {
            if (other.geoCode8 != null)
                return false;
        } else if (!geoCode8.equals(other.geoCode8))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
            return false;
        if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
            return false;
        if (source == null) {
            return other.source == null;
        } else return source.equals(other.source);
    }

}
