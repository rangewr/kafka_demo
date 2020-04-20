package com.kafka.demo.kafka_demo.utils;

import org.springframework.util.StringUtils;

public class ResultJSON {

	// code 状态码： 成功：200，失败：500, 202登录失效
	private String code;
	// 错误信息
	private String msg;
	// 返回的数据
	private Object responseData;

	private int currentPage;// 当前页码
	private int pageSize;// 每页数据条数
	private int recordCount; // 总记录数
	// 计算
	private int pageCount; // 总页数

	// 成功返回<无返回数据>
	public static ResultJSON success() {
		ResultJSON result = new ResultJSON("200", "操作成功", null);
		return result;
	}

	// 成功返回<有返回数据>
	public static ResultJSON success(Object responseData) {
		ResultJSON result = new ResultJSON("200", "操作成功", responseData);
		return result;
	}

	// 成功返回<返回分页数据>
	public static ResultJSON success(int currentPage, int pageSize, int recordCount, Object responseData) {
		ResultJSON result = new ResultJSON("200", "操作成功", responseData, currentPage, pageSize, recordCount);
		return result;
	}

	// 代码抛异常
	public static ResultJSON error(String string) {
		ResultJSON result = new ResultJSON("200", string, null);
		result.setCode("500");
		if (StringUtils.isEmpty(string)) {
			result.setMsg("操作失败");
		}
		return result;
	}

	// 自定义返回状态及返回数据
	public ResultJSON(String code, String msg, Object responseData) {
		this.code = code;
		this.msg = msg;
		this.responseData = responseData;
	}

	// 自定义返回分页状态及返回数据
	public ResultJSON(String code, String msg, Object responseData, int currentPage, int pageSize, int recordCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.code = code;
		this.msg = msg;
		this.responseData = responseData;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		if (this.recordCount > 0) {
			if (this.recordCount % this.pageSize == 0) {
				this.pageCount = this.recordCount / this.pageSize;
				return pageCount;
			}
			this.pageCount = this.recordCount / this.pageSize + 1;
			return pageCount;
		}
		return 0;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
