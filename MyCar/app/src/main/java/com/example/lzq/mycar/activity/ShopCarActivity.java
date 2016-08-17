package com.example.lzq.mycar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzq.mycar.R;
import com.example.lzq.mycar.adapter.CarItemAdapter;
import com.example.lzq.mycar.bean.Goods;

import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class ShopCarActivity extends Activity implements CarItemAdapter.OnShopingDataChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private ListView carList;
    private CarItemAdapter carItemAdapter;
    private List<Goods> goodsbean;
    private TextView submit;
    private TextView mTvTotalValue;
    private TextView deleteAll;
    private CheckBox checkbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop_car);
        carList = (ListView) findViewById(R.id.rv_cart);
        submit = (TextView) findViewById(R.id.tv_submit);
        deleteAll = (TextView) findViewById(R.id.tv_delete_all);
        checkbox = (CheckBox) findViewById(R.id.Cb_checekd);
        mTvTotalValue = (TextView) findViewById(R.id.tv_total_value);

        goodsbean = DataSupport.findAll(Goods.class);

        Log.i("goodsbean==", String.valueOf(goodsbean));
        carItemAdapter = new CarItemAdapter(this, goodsbean);

        carList.setAdapter(carItemAdapter);
//        carItemAdapter.setmOnCheckClick(this);
        initListener();
    }

    private void initListener() {
        submit.setOnClickListener(this);
        deleteAll.setOnClickListener(this);
        carItemAdapter.setListDataChangeListener(this);
        /*carItemAdapter.setmListener(new OnShopCartCheckChangeListener() {
                @Override
                public void isCheckBox(double price) {
                    //double t = TotalPrice() + price;
                    double t = Double.parseDouble(mTvTotalValue.getText().toString());
                    t = t + price;
                    mTvTotalValue.setText(getPrice(t));
                }

                @Override
                public void noCheckBox(double price) {
                    double t = Double.parseDouble(mTvTotalValue.getText().toString());
                    t = t - price;
                    mTvTotalValue.setText(getPrice(t));
                }
            });*/

        /*carItemAdapter.setmOnShopCartTotalPriceLiatener(new OnShopCartTotalPriceLiatener() {
                @Override
                public void countTatalPrice(boolean status, double price) {
                    int count = carItemAdapter.getCount();
                    double total = 0;
                    for (int i = 0; i < count; i++) {
                        if (status == true) {
                            price = Double.parseDouble(goodsbean.get(i).getPicture());
                            total = total + price;
                            mTvTotalValue.setText(getPrice(total));
                        }
                    }
                }
            });
        carList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox= (CheckBox) view.findViewById(R.id.Cb_checekd);
                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }else{
                    checkBox.setChecked(true);
                }
            }
        });
        }*/

        // sqlite 中查询全部 进行查询放入一个list集合
//        for (int i = 0; i < dao.getAllShopPing().size(); i++) {
//            ShopPingBean bean = new ShopPingBean();
//            bean = dao.getAllShopPing().get(i);
//            beanlist.add(bean);
//        }


        checkbox.setOnCheckedChangeListener(this);

//        if (beanlist != null && beanlist.size() != 0) {
//            cartAdapter = new CartAdapter(getApplicationContext(), beanlist);
//            cartAdapter.setListDataChangeListener(this);
//            listview.setAdapter(cartAdapter);
//        }
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                // /点击每个item 调到按个页面
//            }
//        });
    }

    /**
     * 列表中某一项点击改变商品数量的时候会调用
     **/
//    @Override
//    public void onTotalPriceChange(List<ShopPingBean> beanlist) {
//        total_money.setText("合计：￥" + getSelectedTotalPrice(beanlist));
//    }


    private boolean isNeedChangeAllState = true;

    @Override
    public void onTotalPriceChange(List<Goods> beanlist) {
        mTvTotalValue.setText("合计：￥" + getSelectedTotalPrice(beanlist));
    }

    /**
     * 当list列表某一项选中状态改变会调用这个方法
     */
    @Override
    public void onSelectedItemChange() {
        if (checkListState(goodsbean, true)) {
            checkbox.setChecked(true);
        } else if (checkListState(goodsbean, false)) {
            checkbox.setChecked(false);
        } else {
            isNeedChangeAllState = false;
            checkbox.setChecked(false);
            isNeedChangeAllState = true;
        }
        mTvTotalValue.setText("合计：￥" + getSelectedTotalPrice(goodsbean));
//        tried_item.setText("结算(" + getSelectedTotal(goodsbean) + ")");
    }

    /**
     * 监测列表是否都是某种状态
     *
     * @param beanlist
     * @param isAllSelected
     * @return
     */
    private boolean checkListState(List<Goods> beanlist,
                                   boolean isAllSelected) {
        if (beanlist != null) {
            for (Goods bean : beanlist) {
                boolean itemState = bean.isChecked();
                if (itemState != isAllSelected) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 获取选择的数量
     *
     * @param beanlist
     * @return
     */
    private int getSelectedTotal(List<Goods> beanlist) {
        int seletedTotal = 0;
        if (beanlist != null) {
            for (Goods bean : beanlist) {
                boolean itemState = bean.isChecked();
                if (itemState) {
                    seletedTotal += 1;

                }
            }
        }
        return seletedTotal;
    }

    /**
     * 选择的总价
     *
     * @param beanlist
     * @return
     */
    private int getSelectedTotalPrice(List<Goods> beanlist) {
        int seletedTotalPrice = 0;
        if (beanlist != null) {
            for (Goods bean : beanlist) {
                boolean itemState = bean.isChecked();
                if (itemState) {
                    seletedTotalPrice += Integer.valueOf(bean.getTotal());
                }
            }
        }
        return seletedTotalPrice;
    }

    /**
     * 点击全选或者全不选
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {

            if (isNeedChangeAllState) {
                changeListDataState(goodsbean, isChecked);
                carItemAdapter.notifyDataSetChanged();
            }
            mTvTotalValue.setText("合计：￥" + getSelectedTotalPrice(goodsbean));
//            tried_item.setText("结算(" + getSelectedTotal(goodsbean) + ")");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 全选或者全部不选
     *
     * @param beanlist
     * @param state
     * @return
     */
    private void changeListDataState(List<Goods> beanlist,
                                     boolean state) {
        if (beanlist != null) {
            for (Goods bean : beanlist) {
                bean.setChecked(state);
            }
        }
    }

    /**
     * 点击事件处理
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete_all:
                checkBeforeDelete();
                break;

        }
    }

    /**
     * 删除前检测
     */
    private void checkBeforeDelete() {
        if (checkListState(goodsbean, false)) {
            Toast.makeText(this, "请先选择要删除的项", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("删除提示").setMessage("您确定要删除么？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete();
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //取消
                }
            });
            AlertDialog ad = builder.create();
            ad.show();
        }
    }

    /**
     * 删除
     */
    private void delete() {
        if (goodsbean != null) {
            Iterator<Goods> iterator = goodsbean.iterator();
            while (iterator.hasNext()) {
                Goods bean = iterator.next();
                boolean itemState = bean.isChecked();
                if (itemState) {
                    iterator.remove();
//                    dao.deleteCommod(bean.getCommodityId());
                    int id = bean.getId();
//                    int id = goodsbean.get(iterator.hashCode()).getId();
                //lyf:根据id删除指定的数据，要注意
                DataSupport.delete(Goods.class, id);
                }
            }
            carItemAdapter.notifyDataSetChanged();
            checkbox.setChecked(checkListState(goodsbean, true));
            mTvTotalValue.setText("合计：￥" + getSelectedTotalPrice(goodsbean));
//            tried_item.setText("结算(" + getSelectedTotal(goodsbean) + ")");
        }
    }

    /**
     * lyf小数点后两位
     *
     * @param db
     * @return
     */
    public String getPrice(double db) {
        DecimalFormat df = new DecimalFormat("0.00");
        String st = df.format(db);
        return st;
    }



}
