package com.mobitide.common.net;

import com.mobitide.common.result.ResultModel;



/**
 * @author CX 数据请求 返回结果 的处理类
 * 
 */
public abstract class RequestDataHandler {

	/**
	 * 返回请求到的数据
	 * 
	 * @param obj
	 */
	public abstract void onSuccess(Object obj);

	/**
	 * 返回请求失败的error
	 * 
	 * @param error
	 */
	public abstract void onFail(ResultModel error);

	public abstract void onPreExecute();

	public abstract void onFinish();
}
