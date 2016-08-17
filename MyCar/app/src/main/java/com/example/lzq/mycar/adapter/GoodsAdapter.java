package com.example.lzq.mycar.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzq.mycar.R;
import com.example.lzq.mycar.bean.Goods;

import java.util.List;

/**
 * Created by LYF on 2016/6/20.
 */
public class GoodsAdapter extends BaseAdapter {
    private SQLiteDatabase db;
    private Context context;
    private List<Goods> list;
    private List<Goods> goodsbean;


    public GoodsAdapter(Context context, List<Goods> lists) {
        this.context = context;
        this.list = lists;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_shop_goods, null);

            viewHolder.name = (TextView) view.findViewById(R.id.tv_course_name);
            viewHolder.desc = (TextView) view.findViewById(R.id.tv_course_desc);
            viewHolder.add = (TextView) view.findViewById(R.id.tv_add);
            viewHolder.price = (TextView) view.findViewById(R.id.tv_phone_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.desc.setText(list.get(position).getDec());
        viewHolder.price.setText(list.get(position).getPicture());

        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Goods(list.get(position).getCommodNumber(), list.get(position).getId(), list.get(position).getName(), list.get(position).getPicture(), list.get(position).getDec(), list.get(position).getTotal()).save();
                Toast.makeText(context, list.get(position).getName() + "加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ViewHolder {
        private TextView name;
        private TextView desc;
        private TextView add;
        private TextView price;
    }


}
