package com.example.rail_cookery;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

public class TableUserCart {
    private String cmp_col;
    private String seat_col;
    private String phn_col;
    private int item_col;
    private String  pay_col;

    public String getCmp_col() {
        return cmp_col;
    }

    public void setCmp_col(String cmp_col) {
        this.cmp_col = cmp_col;
    }

    public void setSeat_col(String seat_col) {
        this.seat_col = seat_col;
    }

    public void setPhn_col(String phn_col) {
        this.phn_col = phn_col;
    }

    public void setItem_col(int item_col) {
        this.item_col = item_col;
    }

    public void setPay_col(String pay_col) {
        this.pay_col = pay_col;
    }

    public String getSeat_col() {
        return seat_col;
    }

    public String getPhn_col() {
        return phn_col;
    }

    public int getItem_col() {
        return item_col;
    }

    public String getPay_col() {
        return pay_col;
    }





}