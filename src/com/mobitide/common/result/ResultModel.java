package com.mobitide.common.result;

import java.io.Serializable;

/**
 * 数据请求结果结果 实体类
 * 
 * @author CX
 * 
 */
public class ResultModel implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 请求结果代码
     */
    public int err_code;

    /**
     * 请求结果信息
     */
    public String err_msg;

    @Override
    public String toString() {
        return "ResultModel [err_code=" + err_code + ", err_msg=" + err_msg + "]";
    }



}
