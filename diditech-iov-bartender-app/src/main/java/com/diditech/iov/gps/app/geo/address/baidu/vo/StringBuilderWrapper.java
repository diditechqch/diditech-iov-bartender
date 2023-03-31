package com.diditech.iov.gps.app.geo.address.baidu.vo;

import com.alibaba.druid.util.StringUtils;

public class StringBuilderWrapper {

	private StringBuilder _this = new StringBuilder();
	
	private String seperator;
	
	private boolean isSuffix;
	
	public StringBuilderWrapper(){
	}
	
	public StringBuilderWrapper(StringBuilder target){
		if (target != null) {
			_this = target;
		}
	}
	
	public StringBuilderWrapper(String seperator, boolean isSuffix){
		this.seperator = seperator;
		this.isSuffix = isSuffix;
	}
	
	public StringBuilderWrapper(StringBuilder target, String seperator, boolean isSuffix){
		if (target != null) {
			_this = target;
		}
		this.seperator = seperator;
		this.isSuffix = isSuffix;
	}
	
	public StringBuilderWrapper append(String str) {
		if (!StringUtils.isEmpty(str)) {
			_this.append(str);			
		}
		return this;
	}
	
	public StringBuilderWrapper append(Object obj) {
		if (obj != null) {
			_this.append(obj);
		}
		return this;
	}
	
	public StringBuilderWrapper appendWithSeperator(String str) {

		return append(str, seperator, isSuffix);
	}
	
	public StringBuilderWrapper appendWithSeperator(Object obj) {
		
		return append(obj, seperator, isSuffix);
	}
	
	public StringBuilderWrapper append(String str, String seperator) {
		if (!StringUtils.isEmpty(str)) {
			_this.append(seperator).append(str);			
		}
		return this;
	}
	
	public StringBuilderWrapper append(String str, String seperator, boolean isSuffix) {
		if (!isSuffix) {
			return append(str, seperator);
		}
		
		if (!StringUtils.isEmpty(str)) {
			_this.append(str).append(seperator);			
		}
		return this;
	}
	
	public StringBuilderWrapper append(Object obj, String seperator) {
		if (obj != null) {
			_this.append(seperator).append(obj);			
		}
		return this;
	}
	
	public StringBuilderWrapper append(Object obj, String seperator, boolean isSuffix) {
		if (!isSuffix) {
			return append(obj, seperator);
		}
		
		if (obj != null) {
			_this.append(obj).append(seperator);			
		}
		return this;
	}
	
	public String generatorString() {
		return _this.toString();
	}
	
	public String toString() {
		return _this.toString();
	}
}
