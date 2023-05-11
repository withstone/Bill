package com.example.bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bill.R;
import com.example.bill.entity.BillInfo;

import java.util.List;

public class BillListAdapter extends BaseAdapter {

    private final Context mcontext;
    private final List<BillInfo> mBillList;

    public BillListAdapter(Context context, List<BillInfo> billInfoList) {
        this.mcontext =context;
        this.mBillList = billInfoList;
    }

    @Override
    public int getCount() {
        return mBillList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBillList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mBillList.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder=new ViewHolder();
            view = LayoutInflater.from(mcontext).inflate(R.layout.item_bill,null);
            holder.tv_date=view.findViewById(R.id.tv_date);
            holder.tv_remark=view.findViewById(R.id.tv_remark);
            holder.tv_amount=view.findViewById(R.id.tv_amount);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        BillInfo bill =mBillList.get(i);
        holder.tv_date.setText(bill.date);
        holder.tv_remark.setText(bill.remark);
        holder.tv_amount.setText(String.format("%s%s元",bill.type==0 ? "+":"-",bill.amount));
        return view;
    }

    public final class ViewHolder{
        public TextView tv_date;
        public TextView tv_remark;
        public TextView tv_amount;

    }
}
