package com.example.lzq.mycar.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lzq.mycar.R;
import com.example.lzq.mycar.adapter.GoodsAdapter;
import com.example.lzq.mycar.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Goods> list;
    private ListView mShopGoodsLv;
    private GoodsAdapter goodsAdapter;
    private TextView mShopCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initWindow();
        setContentView(R.layout.activity_main);
        mShopGoodsLv = (ListView) findViewById(R.id.lv_shop_goods);
        mShopCar = (TextView) findViewById(R.id.tv_shop_car);
        mShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShopCarActivity.class));
            }
        });
        Goods goods1 = new Goods("1",1, "iphone", "6000", "销量全球第一的手机，性价比最高","6000");
        Goods goods2 = new Goods("1",2, "小米", "2000", "销量全球第二的手机，性价比最高","2000");
        Goods goods3 = new Goods("1",3, "华为", "4000", "销量全球第三的手机，性价比最高","4000");
        Goods goods4 = new Goods("1",4, "三星", "6000", "销量全球第四的手机，性价比最高","6000");
        Goods goods5 = new Goods("1",5, "乐视", "1500", "销量全球第五的手机，性价比最高","1500");
        Goods goods6 = new Goods("1",6, "oppo", "2000", "销量全球第六的手机，性价比最高","2000");
        Goods goods7 = new Goods("1",7, "vivo", "2000", "销量全球第七的手机，性价比最高","2000");
        Goods goods8 = new Goods("1",8, "酷派", "1000", "销量全球第八的手机，性价比最高","1000");
        Goods goods9 = new Goods("1",9, "联想", "1600", "销量全球第九的手机，性价比最高","1600");
        Goods goods10 = new Goods("1",10, "金立", "1200", "销量全球第十的手机，性价比最高","1200");
        list = new ArrayList<Goods>();
        list.add(goods1);
        list.add(goods2);
        list.add(goods3);
        list.add(goods4);
        list.add(goods5);
        list.add(goods6);
        list.add(goods7);
        list.add(goods8);
        list.add(goods9);
        list.add(goods10);

        goodsAdapter = new GoodsAdapter(this,list);
        mShopGoodsLv.setAdapter(goodsAdapter);
    }
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
