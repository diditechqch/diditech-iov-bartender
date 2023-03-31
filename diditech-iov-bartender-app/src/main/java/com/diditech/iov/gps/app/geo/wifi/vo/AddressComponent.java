package com.diditech.iov.gps.app.geo.wifi.vo;

public class AddressComponent {

	
	/**
	 * 定位类型，0:没有得到定位结果；其他值:正常获得位置(3:WIFI定位，4:基站定位)
	 */
	private String type;
	
	/**
	 * 手机imei号
	 */
	private String imei;
	
	/**
	 * 定位经纬度
	 */
	private String location;
	
	/**
	 * 定位精度半径，单位：米
	 */
	private int radius;
	
	/**
	 * 位置描述
	 */
	private String desc;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 城市编号（不一定是国家标准）
	 */
	private String citycode;
	
	/**
	 * 区域编号
	 */
	private String adcode;
	
	/**
	 * 道路名
	 */
	private String road;
	
	/**
	 * 定位附近的poi名称
	 */
	private String poi;
	
	private String street;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adcode == null) ? 0 : adcode.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((citycode == null) ? 0 : citycode.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((imei == null) ? 0 : imei.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((poi == null) ? 0 : poi.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((road == null) ? 0 : road.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AddressComponent other = (AddressComponent) obj;
		if (adcode == null) {
			if (other.adcode != null)
				return false;
		} else if (!adcode.equals(other.adcode))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (citycode == null) {
			if (other.citycode != null)
				return false;
		} else if (!citycode.equals(other.citycode))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (imei == null) {
			if (other.imei != null)
				return false;
		} else if (!imei.equals(other.imei))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (poi == null) {
			if (other.poi != null)
				return false;
		} else if (!poi.equals(other.poi))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		if (road == null) {
			if (other.road != null)
				return false;
		} else if (!road.equals(other.road))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (type == null) {
			return other.type == null;
		} else return type.equals(other.type);
	}

	@Override
	public String toString() {
		return "AddressComponent [type=" + type + ", imei=" + imei + ", location=" + location + ", radius=" + radius + ", desc=" + desc + ", country=" + country + ", province=" + province + ", city=" + city + ", citycode=" + citycode + ", adcode=" + adcode + ", road=" + road + ", street=" + street + ", poi=" + poi + "]";
	}

}
