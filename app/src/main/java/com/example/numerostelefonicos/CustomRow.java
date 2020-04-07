package com.example.numerostelefonicos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomRow extends BaseAdapter {

    Activity activity;
    ArrayList<Contact> contactArrayList;

    public CustomRow(Activity activity, ArrayList<Contact> contactArrayList){

        this.activity = activity;
        this.contactArrayList = contactArrayList;

    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customView = convertView;

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = layoutInflater.inflate(R.layout.custom_row, null);

            Contact contact = contactArrayList.get(position);

            TextView tv_name = customView.findViewById(R.id.custom_tv_name);
            tv_name.setText(contact.getName());

            TextView tv_number = customView.findViewById(R.id.custom_tv_number);
            tv_number.setText(contact.getNumber());

        }

        return customView;
    }
}
