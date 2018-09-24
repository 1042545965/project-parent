package com.project.product.controller.text;

import com.project.product.annotation.ApiPara;

public class CustomerParameter {
	@ApiPara(required=false,maxlength="500",name="url",type="String",desc="链接")
	public String url;
	
	@ApiPara(required=false,maxlength="500",name="appId",type="String",desc="唯一编号")
	public String appId;
	
	@ApiPara(required=false,maxlength="500",name="secret",type="String",desc="唯一secret")
	public String secret;
	
	@ApiPara(required=false,maxlength="500",name="version",type="String",desc="法大大版本号2.0")
	public String version;
}
