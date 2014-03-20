/**
 * FILE: NetUtil.java AUTHOR: CX DATE: 2012-4-24
 * 
 * Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.mobitide.lib.R;

/**
 * @author CX
 * 
 */
public class MNetUtil {

    /**
     * 检查网络状态
     * 
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        ConnectivityManager mConnectivity =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        if (!info.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 得到本网IP地址
     * 
     * @return 返回String 类型IP ，若为空返回null
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("exception", ex.toString());
        }
        return null;
    }

    /**
     * 检查端口绑定情况
     * 
     * @return
     */
    public static boolean checkPort(int port) {
        try {
            new DatagramSocket(port).close();
            return true;
        } catch (SocketException ex) {
            return false;
        }
    }

    /**
     * 打开网络设置
     * 
     * @param con
     */
    public static void openNetworkSettings(final Context con) {

        new AlertDialog.Builder(con).setTitle(MResUtil.getString(R.string.prompt))
                .setMessage(MResUtil.getString(R.string.error_no_net))
                .setPositiveButton(MResUtil.getString(R.string.setting), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Go to the activity of settings of wireless

                        // // 跳转到无线网络设置界面
                        con.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

}
