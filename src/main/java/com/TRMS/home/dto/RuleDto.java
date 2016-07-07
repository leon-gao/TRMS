package com.TRMS.home.dto;

public class RuleDto {

	private String url;
	private String[] params;
	private String resultTagName;
	private String[] values;
	private Integer type;
    private Integer requestMethod;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getResultTagName() {
		return resultTagName;
	}
	public void setResultTagName(String resultTagName) {
		this.resultTagName = resultTagName;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public Integer getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(Integer requestMethod) {
		this.requestMethod = requestMethod;
	}
	
}