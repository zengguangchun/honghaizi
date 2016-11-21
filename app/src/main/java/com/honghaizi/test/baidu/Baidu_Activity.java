package com.honghaizi.test.baidu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.honghaizi.test.honghaizi.R;

public class Baidu_Activity extends AppCompatActivity {
    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE =1 ;
    public MapView mapView = null;
    public BaiduMap baiduMap = null;
public  String address;
    // 定位相关声明
    public LocationClient locationClient = null;
    //自定义图标
    BitmapDescriptor mCurrentMarker = null;
    boolean isFirstLoc = true;// 是否首次定位
    public BDLocationListener myListener = new BDLocationListener() {//定位结果回调，重写onReceiveLocation方法
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null)
                return;

            //显示定位地址
           address=location.getAddrStr();
           tv_dituname.setText("点击回传定位地址："+address);

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            baiduMap.setMyLocationData(locData);    //设置定位数据

            if (isFirstLoc) {
                isFirstLoc = false;


                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
               MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);   //设置地图中心点以及缩放级别
       //      MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(u);
            }
        }
    };
    private TextView tv_dituname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注意该方法要再setContentView方法之前实现
        setContentView(R.layout.activity_baidu_);
        getSupportActionBar().hide();
        //百度地图定位6.0权限配置
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_COARSE_LOCATION_REQUEST_CODE);
        } else {
            init();
        }
        ///

    }

    private void init() {
        tv_dituname = (TextView)this.findViewById(R.id.tv_dituname);
        tv_dituname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(66,new Intent().setAction(address));
                finish();

            }
        });
        mapView = (MapView) this.findViewById(R.id.bmapView); // 获取地图控件引用
        baiduMap = mapView.getMap();
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
        locationClient.registerLocationListener(myListener); // 注册监听函数
        this.setLocationOption();   //设置定位参数
        locationClient.start(); // 开始定位
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图

        // baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图
        baiduMap.setTrafficEnabled(true); //开启交通图
    }

    // 三个状态实现地图生命周期管理
    @Override
    protected void onDestroy() {
        //退出时销毁定位
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        // TODO Auto-generated method stub
        super.onDestroy();
        mapView.onDestroy();
        mapView = null;

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mapView.onPause();
    }

    /**
     * 设置定位参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

        locationClient.setLocOption(option);
    }



}
