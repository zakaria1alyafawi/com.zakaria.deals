package com.zakaria.deals.deals.pojo.base;

import java.io.Serializable;

public class BaseDealPojo implements Serializable {

	private static final long serialVersionUID = -6885950441033362774L;

	private String errorCode;
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
