package com.project.product.controller.customer;

import com.project.product.annotation.ReturnCodePara;

public class CustomerCode {
	@ReturnCodePara(code="1000",desc="成功")
	public String code_1000;
	
	@ReturnCodePara(code="2001",desc="参数缺失或者不合法")
	public String code_2001;
	
	@ReturnCodePara(code="2002",desc="业务异常，失败原因见 msg")
	public String code_2002;
	
	@ReturnCodePara(code="2003",desc="其他错误，请联系法大大")
	public String code_2003;
}
