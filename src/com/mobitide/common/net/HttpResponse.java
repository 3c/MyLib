/**
 * FILE: HttpResponese.java AUTHOR: CX DATE: 2012-7-5
 * 
 * Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.net;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mobitide.common.data.MGlobalDataCache;
import com.mobitide.common.data.MGlobalConstants;
import com.mobitide.common.data.MGlobalConstants.ResultConstans;
import com.mobitide.common.log.APPLOG;
import com.mobitide.common.result.ResultModel;
import com.mobitide.common.utils.MNetUtil;
import com.mobitide.common.utils.MProgressDialogUtil;
import com.mobitide.common.utils.MResUtil;
import com.mobitide.lib.R;

/**
 * eg: HttpResponse httpResponse=new HttpResponse(this); //调用get方法，一般需 三个 参数。1.module类。2.params。3
 * ，回调handler httpResponse.get(LoginModule.class, params, new RequestDataHandler() {
 * 
 * @Override public void onSuccess(Object obj) { //数据请求成功后 强制转型 获得数据 LoginModule
 *           module=(LoginModule) obj; APPLOG.log("success "+module); } });
 * 
 *           网络请求处理类
 * @author CX
 */
public class HttpResponse {
    RequestDataHandler mRequestDataListener = null;
    // 第三方网络库，用于发送post
    private AsyncHttpClient client = new AsyncHttpClient();
    Object obj;
    Context mContext;
    Class cls;

    String url;
    public static final String SERVER_URL = "http://112.124.17.156:30000/";
    // the key made by appId,modId,cateId,page,verId, it is uniqueness ,use for
    // cache
    String key;
    // cache is save sdcard or not
    boolean saveSdcardEnable = false;
    // refresh data by user force ,this is not done
    boolean refreshEnable = false;
    // show progressBar is or not when data load
    boolean progressBarEnable = false;

    /**
     * 处理数据
     */
    private void handleData() {
        if (MGlobalDataCache.getShare(key) == null || refreshEnable) {
            // 如果没有缓存的key，或者是强制刷新，就请求数据
            // 判断是否请求网络数据。模块数据有更新，本地缓存文件不存在，强制刷新 中任一条件成立，都请求
            if (MNetUtil.checkNet(mContext)) {
                requestHttpData();
            } else {
                // 没有网络
                sendNoNetError();
                // 没有网络默认输出 Toast　提示
//                MToastUtil.show(R.string.error_no_net);
            }

        } else {
            // 有缓存，从缓存中读取数据
            APPLOG.d("global cache has it ");
            obj = MGlobalDataCache.getShare(key);
            this.mRequestDataListener.onSuccess(obj);
        }
    }

    /**
     * 请求网络数据
     */
    private void requestHttpData() {
        // 进度提示框
        if (progressBarEnable) {
            MProgressDialogUtil.show(mContext);
        }

        if (mRequestDataListener != null) {
            mRequestDataListener.onPreExecute();
        }

        APPLOG.v("--------------------- request url --------------------- ");
        APPLOG.d(url);
        APPLOG.v("-------------------------------------------------------");
        // 发送Post
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                APPLOG.d("response data: " + content);
                onLoadFinish();
                handleSuccessData(content);
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                if (progressBarEnable) {
                    MProgressDialogUtil.cancel();
                }
                onLoadFinish();
                // 请求失败，
                sendDontKnowError();
            }
        });

    }

    private void onLoadFinish() {
        if (mRequestDataListener != null) {
            mRequestDataListener.onFinish();
        }
    }

    /**
     * 处理成功请求的数据
     */
    protected void handleSuccessData(String content) {


        try {

            // 数据请求成功
            obj = new Gson().fromJson(content, cls);
            // 什么时候resultModel为null呢？ 解析失败的时候，那估计就挂掉了,但我不try
            // catch,这样方便发现问题。若上线的话应该try cath (传过来的class必须继承ResultModel)
            if (obj != null) {

                ResultModel resultModel = (ResultModel) obj;
                // ecode == 0 意味着请求成功
                if (resultModel.err_code == MGlobalConstants.ResultConstans.ERROR_00) {
                    // 放进内存的缓存
                    MGlobalDataCache.putShare(key, obj);
                    mRequestDataListener.onSuccess(obj);
                } else {
                    mRequestDataListener.onFail(resultModel);
                }

            } else {
                APPLOG.e("maybe parse error . have you extends ResultModel?");
                sendDontKnowError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            APPLOG.e("maybe parse error . have you extends ResultModel?");
            sendDontKnowError();
        }

        if (progressBarEnable) {
            MProgressDialogUtil.cancel();
        }
    }

    /**
     * 没有网络
     */
    private void sendNoNetError() {
        ResultModel errorModule = new ResultModel();
        errorModule.err_code = ResultConstans.ERROR_NO_NET;
        errorModule.err_msg = MResUtil.getString(R.string.error_no_net);
        HttpResponse.this.mRequestDataListener.onFail(errorModule);
    }

    /**
     * 未知错误
     */
    private void sendDontKnowError() {
        ResultModel errorModule = new ResultModel();
        errorModule.err_code = ResultConstans.ERROR_DONT_KNOW;
        // errorModule.errMsg = API.getResSring(R.string.error_dont_know);
        if (mRequestDataListener != null) {
            mRequestDataListener.onFail(errorModule);

        }
    }

    public HttpResponse(Context context) {
        this.mContext = context;
    }

    public void setProgressBarEnable(boolean progressBarEnable) {
        this.progressBarEnable = progressBarEnable;
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
    }

    public void setSaveSdcardEnable(boolean saveSdcardEnable) {
        this.saveSdcardEnable = saveSdcardEnable;
    }

    /**
     * 请求数据
     * 
     * @param cls
     * @param params
     * @param modId
     * @param mRequestDataHandler
     */
    public void getData(Class cls, String url, RequestDataHandler mRequestDataHandler) {
        this.cls = cls;
        this.mRequestDataListener = mRequestDataHandler;
        obj = null;
        this.url = url;
        key = String.valueOf(url.hashCode());
        if (this.mRequestDataListener != null) {
            handleData();
        } else {
            APPLOG.e("no RequestDataHandler");
        }

    }



    public String getKey() {
        return key;
    }

}
