package com.honghaizi.test.erweima;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.honghaizi.test.honghaizi.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class Erweima_Activity extends AppCompatActivity  implements QRCodeView.Delegate  {
    private static final String TAG = Erweima_Activity.class.getSimpleName();
    private QRCodeView mQRCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima_);
        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        mQRCodeView.setDelegate(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
    }
    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {Log.e(TAG, "打开相机出错");
    }



}
