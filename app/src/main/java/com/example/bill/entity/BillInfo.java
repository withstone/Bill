package com.example.bill.entity;

public class BillInfo {

    public int id;
    public String date;
    public int type;
    public double amount;
    public String remark;

    //账单类型,0 收入,1支出
    public static final int BILL_TYPE_INPUT = 0;
    public static final int BILL_TYPE_OUTPUT = 1;

    @Override
    public String toString() {
        return "BillInfo{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
