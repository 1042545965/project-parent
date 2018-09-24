package com.project.product.controller.text;

import com.project.product.annotation.ResponsePara;

public class CustomerResult {
	@ResponsePara(field="downloadUrl",name="合同地址",required=true,type="String",maxlength="1800")
	public String downloadUrl;
}
