package com.common.modules.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.common.core.debug.Logger;
import com.common.modules.BaseModule;
import com.common.modules.ModuleManager;
import com.common.modules.network.NetworkState;
import com.common.modules.property.PhoneProperties;
import com.common.modules.property.SharePreferenceProperties;
import com.common.utils.StringUtils;


/**
 * 定位服务
 *
 * @author kevin
 * @version v1.0
 * @since 2014-11/7/14
 */
public class LocationServer extends BaseModule implements BDLocationListener {

    public static final int EXPIRED_TIME = 1 * 3600 * 1000;
    public static final String SEND_BROADCAST_SELECTED_CITY = "send_Broadcast_Selected_City";
    public static final String SEND_BROADCAST_INDEX_CITY = "sene_Broadcast_Index_City";
    public static String SELECTED_CITY = "selected_city";
    public static String INDEX_CITY = "index_city";
    private static LocationServer sLocationServer;
    private OnLocationChangedListener mListener;
    private OnLocationResultListener mResultListener;

    private LocationClient mLocationClient;
    private double mLon;
    private double mLat;

    private String mAddress;

    public static LocationServer asInstance() {
        if (sLocationServer == null) {
            sLocationServer = (LocationServer) ModuleManager.asInstance().getModule(LocationServer.class);
        }
        return sLocationServer;
    }


    @Override
    public void setup(Context context) {
        super.setup(context);
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
    }

    /**
     * 获得经度
     *
     * @return
     */
    public double getLon() {
        if (isExpiredTime()) {
            PhoneProperties.setLon(0);
        }
        return PhoneProperties.getLon();
    }

    /**
     * 获得纬度
     *
     * @return
     */
    public double getLat() {
        if (isExpiredTime()) {
            PhoneProperties.setLat(0);
        }
        return PhoneProperties.getLat();
    }

    public void setOnLocationChangedListener(OnLocationChangedListener listener) {
        mListener = listener;
    }

    /**
     * 定位
     *
     * @param listener
     */
    public void location(OnLocationResultListener listener) {
        mResultListener = listener;
        mLocationClient.start();
        if (!NetworkState.available()) {
            mLocationClient.requestOfflineLocation();
        } else {
            mLocationClient.requestLocation();
        }
    }

    /**
     * 定位
     */
    public void location() {
        mLocationClient.start();
        if (!NetworkState.available()) {
            mLocationClient.requestOfflineLocation();
        } else {
            mLocationClient.requestLocation();
        }
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null) {
            mLat = bdLocation.getLatitude();
            mLon = bdLocation.getLongitude();
            Logger.d("location", "bdLocation.getLocType() = " + bdLocation.getLocType() + " mLat = " + mLat + " mLon = " + mLon);
            if (mResultListener != null) {
                boolean result = (bdLocation.getLocType() == BDLocation.TypeGpsLocation
                        || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation
                        || bdLocation.getLocType() == BDLocation.TypeCacheLocation
                        || bdLocation.getLocType() == BDLocation.TypeOffLineLocation)
                        && mLon != 0.0d
                        && mLat != 0.0d;
                mLocationClient.stop();
                SharePreferenceProperties.set("location_latest_time", Long.parseLong(System.currentTimeMillis() + ""));
                if (result) {
                    PhoneProperties.setLat(mLat);
                    PhoneProperties.setLon(mLon);
                }

                mAddress = bdLocation.getCity() + bdLocation.getDistrict();
                Logger.d("location", "getProvince:" + bdLocation.getProvince());
                Logger.d("location", "getAddrStr:" + bdLocation.getAddrStr());
                Logger.d("location", "getStreet:" + bdLocation.getStreet());
                Logger.d("location", "getFloor:" + bdLocation.getFloor());
                Logger.d("location", "getStreetNumber:" + bdLocation.getStreetNumber());
                Logger.d("location", "getCity:" + bdLocation.getCity());
                Logger.d("location", "getDistrict:" + bdLocation.getDistrict());

                mResultListener.onLocationResult(result, bdLocation);
                mResultListener = null;
            }

            if (mListener != null) {
                mListener.onLocationChanged(mLon, mLat);
            }

        }
    }

    public boolean validate() {
        return getLon() != 0 && getLat() != 0 && !isExpiredTime() && !StringUtils.isEmpty(mAddress);
    }

    public boolean isExpiredTime() {
        Long latestTime = (Long) SharePreferenceProperties.get("location_latest_time", Long.parseLong("0"));
        long remainderTime = System.currentTimeMillis() - latestTime;
        return remainderTime > EXPIRED_TIME;
    }

    public String getLocationAddress() {
        return mAddress;
    }

    /**
     * 发布广播工具
     *
     * @param activity
     * @param bundle
     * @param actionName
     */
    public void sendLocationBroadcast(Activity activity, Bundle bundle, String... actionName) {
        Intent mIntent = new Intent();
        for (int i = 0; i < actionName.length; i++) {
            mIntent.setAction(actionName[i]);
        }
        mIntent.putExtras(bundle);
        //发送广播
        if (activity != null) {
            activity.sendBroadcast(mIntent);
        }
    }

    public interface OnLocationChangedListener {
        void onLocationChanged(double lon, double lat);
    }

    public interface OnLocationResultListener {
        void onLocationResult(boolean success, BDLocation location);
    }
}
