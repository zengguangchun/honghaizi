package com.honghaizi.test.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.pay.AuthResult;
import com.honghaizi.test.pay.PayResult;
import com.honghaizi.test.util.OrderInfoUtil2_0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2016/11/7.
 */
public class Fragment_Shopping extends Fragment implements View.OnClickListener{

    private View view;
    private ListView lvshopping;
    private CheckBox cbox_all;
    private TextView tv_totalprice;
    private Button btn_account;
    private ShoppingAdapter adapter;
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
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getActivity(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_shopping,null);
        //初始化控件
        initCtrl();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            list.add("喜朗 婴儿滋润鳄梨面霜52g+婴儿清爽");
            list.add("花 王（Merries）秒免俗大号L54止尿促");
        }
        adapter = new ShoppingAdapter(getActivity(),list);
        lvshopping.setAdapter(adapter);
        return view;
    }

    private void initCtrl() {

      //  CheckBox cbox_shoppig = (CheckBox)view.findViewById(R.id.cbox_shoppig);
        lvshopping = (ListView)view.findViewById(R.id.lv_shopping);
        lvshopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"商品"+i,Toast.LENGTH_LONG).show();
            }
        });
        cbox_all = (CheckBox)view.findViewById(R.id.cbox_all);
        tv_totalprice = (TextView) view.findViewById(R.id.tv_totalprice_shopping);
        btn_account = (Button)view.findViewById(R.id.cart_but_jie);
        cbox_all.setOnClickListener(this);
        btn_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cbox_all://全选，要么全true，要么全false
                boolean flag = cbox_all.isChecked();

                for (int i = 0; i < adapter.getSelect().size(); i++) {
                    adapter.getSelect().set(i, flag);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.cart_but_jie:
                payV2(view);
                break;
            default:
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
            new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                           getActivity(). finish();
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
                PayTask alipay = new PayTask(getActivity());
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

    class ShoppingAdapter extends BaseAdapter {
      //  private String[] name = {"喜朗 婴儿滋润鳄梨面霜52g+婴儿清爽","花 王（Merries）秒免俗大号L54止尿促"};
        private int[] image = {R.mipmap.a,R.mipmap.b};
        private List<String> list;
        private Context context;
        private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

        public ShoppingAdapter(Context context, List<String> list) {
            // TODO Auto-generated constructor stub
            this.list = list;
            this.context = context;
            //初始化
            for (int i = 0; i < list.size(); i++) {
                getSelect().add(false);
            }
        }

        private List<Boolean> getSelect() {
            return linkedList;
        }


        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view1 = View.inflate(context,R.layout.shopping_item,null);
            ImageView imageView = (ImageView)view1.findViewById(R.id.shopping_image);
            TextView shopping_tv = (TextView)view1.findViewById(R.id.tv_namea);
            CheckBox cbox = (CheckBox)view1.findViewById(R.id.cbox);
            imageView.setImageResource(image[i]);
            shopping_tv.setText(list.get(i));
            cbox.setChecked(linkedList.get(i));
            cbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linkedList.set(i, ! linkedList.get(i));
                    if (linkedList.contains(false)) {
                        cbox_all.setChecked(false);
                    }else {
                        cbox_all.setChecked(true);
                    }
                    //刷新
                    notifyDataSetChanged();
                }
            });
            return view1;
        }


    }

}
