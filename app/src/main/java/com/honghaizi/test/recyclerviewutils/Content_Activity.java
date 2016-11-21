package com.honghaizi.test.recyclerviewutils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.honghaizi.test.adapter.MyFragmentViewPager;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.pay.AuthResult;
import com.honghaizi.test.pay.PayResult;
import com.honghaizi.test.util.OrderInfoUtil2_0;

import java.util.ArrayList;
import java.util.Map;

public class Content_Activity extends FragmentActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private ArrayList<TextView> tvlist;
    private TextView tv_liji;
//
//    /** 支付宝支付业务：入参app_id */
//    public static final String APPID = "2088901305856832";
//
//    /** 支付宝账户登录授权业务：入参pid值 */
//    public static final String PID = "8@qdbaiu.com";
//    /** 商户私钥，pkcs8格式 */
//    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuyovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHDZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaarBCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDVb2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/gX+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyXrGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPnJ+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdIrk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM604hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaDTqJxLy6AnQ+Q==\n";
//
 //   private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    // 商户PID
    public static final String APPID = "2088901305856832";
    // 商户收款账号
    public static final String SELLER = "8@qdbaiu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6V" +
            "lGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtA" +
            "DqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;
    //阿里云服务器，用真机测试
    private static final String URL_JSON = "http://101.200.142.201:8080/alipayServer/AlipayDemo";
    //本机服务器，用真机访问不到，用模拟器能访问，但是支付宝不允许模拟器支付
//	private static final String URL_JSON = "http://169.254.109.73:8080/alipayServer/AlipayDemo";

    //
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(Content_Activity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(Content_Activity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(Content_Activity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(Content_Activity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private TextView tv_jiaru_shopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content_);
        // 初始化控件
        initCtrl();
        // 设置设配器
        MyFragmentViewPager adapter = new MyFragmentViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tvlist.size(); i++) {
                    if (i == position) {
                        tvlist.get(i).setTextColor(Color.GREEN);
                    } else {
                        tvlist.get(i).setTextColor(Color.BLACK);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Button的点击事件
        //遍历Button
        for (TextView tv : tvlist) {
            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    switch (v.getId()) {
                        case R.id.tv_shopping:
                            viewPager.setCurrentItem(0);
                            break;

                        case R.id.tv_xiangxi:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.tv_comment:
                            viewPager.setCurrentItem(2);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    private void initCtrl() {
        TextView tv_shopping = (TextView)findViewById(R.id.tv_shopping);
        TextView tv_xiangxi = (TextView)findViewById(R.id.tv_xiangxi);
        TextView tv_comment = (TextView)findViewById(R.id.tv_comment);
        tv_jiaru_shopping = (TextView)findViewById(R.id.tv_jiaru_shopping);
        viewPager = (ViewPager)findViewById(R.id.content_vp);
        tv_liji = (TextView)findViewById(R.id.tv_liji);
        tv_liji.setOnClickListener(this);
        tv_jiaru_shopping.setOnClickListener(this);
        //使用ArrayList集合添加
        tvlist = new ArrayList<TextView>();
        tvlist.add(tv_shopping);
        tvlist.add(tv_xiangxi);
        tvlist.add(tv_comment);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_liji:
                payV2(view);
                break;
            case R.id.tv_jiaru_shopping:

               //startActivity(new Intent(Content_Activity.this, Fragment_Shopping.class));
                break;
        }
    }

    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(Content_Activity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
