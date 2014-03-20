/**
 * Filename : GlobalContext.java Author : CX Date : 2013-8-12
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.data;

import android.content.Context;

import com.mobitide.common.application.MGlobalApplication;

/**
 * 
 * MGlobalContext.getContext() 获取全局实例
 * @author CX
 * 
 */
public class MGlobalContext {

    /**
     * 获得全局的context
     * @return
     */
    public static Context getContext() {
        if(MGlobalApplication.getInstance()==null){
            throw new NullPointerException("MGlobalApplication.mInstance==null. Please init first");
        }
        //这么搞是为了方便切换context
        return MGlobalApplication.getInstance();
    }
}
