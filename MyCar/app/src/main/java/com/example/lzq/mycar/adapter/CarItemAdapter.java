package com.example.lzq.mycar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lzq.mycar.R;
import com.example.lzq.mycar.bean.Goods;
import com.example.lzq.mycar.view.CicleAddAndSubView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by LYF on 2016/6/21.
 */
public class CarItemAdapter extends BaseAdapter {
    private Context context;
    private List<Goods> listBean;

    public CarItemAdapter(Context context, List<Goods> listBean) {
        this.context = context;
        this.listBean = listBean;
    }
    /*private OnShopCartCheckChangeListener mListener;

    public void setmListener(OnShopCartCheckChangeListener mListener) {
        this.mListener = mListener;
    }

    private OnShopCartTotalPriceLiatener mOnShopCartTotalPriceLiatener;

    public void setmOnShopCartTotalPriceLiatener(OnShopCartTotalPriceLiatener mOnShopCartTotalPriceLiatener) {
        this.mOnShopCartTotalPriceLiatener = mOnShopCartTotalPriceLiatener;
    }*/

    public OnShopingDataChangeListener getListDataChangeListener() {
        return listDataChangeListener;
    }

    public void setListDataChangeListener(OnShopingDataChangeListener listDataChangeListener) {
        this.listDataChangeListener = listDataChangeListener;
    }

    /**
     *定义数据改变接口
     */
    public interface OnShopingDataChangeListener{
        /**
         * 总价改变调用
         */
        void onTotalPriceChange(List<Goods> beanlist);
        /**
         * 选择改变调用
         */
        void onSelectedItemChange();

    }

    private OnShopingDataChangeListener listDataChangeListener;

    @Override
    public int getCount() {
        return listBean.size();
    }

    @Override
    public Object getItem(int position) {
        return listBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_shop_cart, null);

            viewHolder.name = (TextView) view.findViewById(R.id.tv_car_goods_name);
            viewHolder.goodsPrice = (TextView) view.findViewById(R.id.tv_car_goods_price);
//            viewHolder.addCound = (TextView) view.findViewById(R.id.add_count);
//            viewHolder.addCound.setTag(position);
//            viewHolder.delcount = (TextView) view.findViewById(R.id.del_count);
//            viewHolder.delcount.setTag(position);
//            viewHolder.delete = (TextView) view.findViewById(R.id.tv_delete);
//            viewHolder.delete.setTag(position);
            viewHolder.mCbCheceked = (CheckBox) view.findViewById(R.id.Cb_checekd);
            viewHolder.ciaddview=(CicleAddAndSubView) view.findViewById(R.id.commid_numberview);//商品数量
//            viewHolder.mCountNum = (EditText) view.findViewById(R.id.count_num);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(listBean.get(position).getName());
        viewHolder.goodsPrice.setText(listBean.get(position).getPicture());
//        viewHolder.mCbCheceked.setChecked(listBean.get(position).isChecked());
//        mOnShopCartTotalPriceLiatener.countTatalPrice(viewHolder.mCbCheceked.isChecked(), Double.parseDouble(listBean.get(position).getPicture()));

        int num= Integer.parseInt(listBean.get(position).getCommodNumber());
        int price = Integer.parseInt(listBean.get(position).getPicture());
        listBean.get(position).setTotal(String.valueOf(num*price));
        viewHolder.ciaddview.setMinValue(1);
        viewHolder.ciaddview.setInitial(Integer.valueOf(listBean.get(position).getCommodNumber()));
        viewHolder.ciaddview.setOnNumChangeListener(new CicleAddAndSubView.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                listBean.get(position).setCommodNumber(String.valueOf(num));
                int price = Integer.parseInt(listBean.get(position).getPicture());
                listBean.get(position).setTotal(String.valueOf(num*price));
                if (listDataChangeListener!=null) {
                    listDataChangeListener.onTotalPriceChange(listBean);
                }
            }
        });
        viewHolder.mCbCheceked.setChecked(listBean.get(position).isChecked());
        viewHolder.mCbCheceked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean choice) {
                listBean.get(position).setChecked(choice);
                if (listDataChangeListener!=null) {
                    listDataChangeListener.onSelectedItemChange();
                }
            }
        });
        /*viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = listBean.get(position).getId();
//                //lyf:根据id删除指定的数据，要注意
//                DataSupport.delete(Goods.class, id);
//                listBean.remove(position);
                double price = Double.parseDouble(viewHolder.goodsPrice.getText().toString());
                if (viewHolder.mCbCheceked.isChecked()){
                    mListener.noCheckBox(price);
                }
                //lyf:根据id删除指定的数据，要注意
                DataSupport.delete(Goods.class, id);
                listBean.remove(position);
//                homecourseAdapter.notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.addCound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(viewHolder.mCountNum.getText().toString());
                i = i + 1;
                viewHolder.mCountNum.setText(String.valueOf(i));
                double singleTotPrice;
                double singlePrice = Double.parseDouble(listBean.get(position).getPicture());
                singleTotPrice = singlePrice * i;
                viewHolder.goodsPrice.setText(getPrice(singleTotPrice));
                if (viewHolder.mCbCheceked.isChecked()) {
                    mListener.isCheckBox(Double.parseDouble(listBean.get(position).getPicture()));
                }
            }
        });
        viewHolder.delcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j = Integer.parseInt(viewHolder.mCountNum.getText().toString());
                if (j > 1) {
                    j = j - 1;
                }
                viewHolder.mCountNum.setText(String.valueOf(j));
                double singlePrice = Double.parseDouble(listBean.get(position).getPicture());
                double totapric = Double.parseDouble(viewHolder.goodsPrice.getText().toString());
                if (totapric > singlePrice) {
                    totapric = totapric - singlePrice;
                    viewHolder.goodsPrice.setText(getPrice(totapric));
                    if (viewHolder.mCbCheceked.isChecked()) {
                        mListener.noCheckBox(Double.parseDouble(listBean.get(position).getPicture()));
                    }
                }

            }
        });
        viewHolder.mCbCheceked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!viewHolder.mCbCheceked.isChecked()) {
                    double price = Double.parseDouble(viewHolder.goodsPrice.getText().toString());
                    //showToast(String.valueOf(singlePrice));
                    mListener.noCheckBox(price);
                }
                if (viewHolder.mCbCheceked.isChecked()) {
                    double price = Double.parseDouble(viewHolder.goodsPrice.getText().toString());
                    mListener.isCheckBox(price);
                }
            }
        });*/
        return view;
    }

    private class ViewHolder {
        private TextView name;
        private TextView goodsPrice;
        private TextView addCound;
        private TextView delcount;
        private TextView delete;
        private CheckBox mCbCheceked;
        private EditText mCountNum;
        private CicleAddAndSubView ciaddview;
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
