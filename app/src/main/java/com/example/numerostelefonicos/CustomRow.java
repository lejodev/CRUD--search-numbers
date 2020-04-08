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

    private class ViewHolder {
        TextView tv_name;
        TextView tv_number;
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        View customView = convertView;

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = layoutInflater.inflate(R.layout.custom_row, null);// "Inflate the row with the customized design"

            // Holder class to store each of the displayed views while scrolling
            holder = new ViewHolder();

            holder.tv_name = customView.findViewById(R.id.custom_tv_name);
            holder.tv_number = customView.findViewById(R.id.custom_tv_number);

            customView.setTag(holder); // the setTag method gets a single key to each customView (row) with the object reference

        }else{
            holder = (ViewHolder)customView.getTag(); // holder obtains the reference to its Holder objects
        }

        Contact contact = contactArrayList.get(position); // Obtaining each contact from a Contact list

        //TextView tv_name = customView.findViewById(R.id.custom_tv_name);
        holder.tv_name.setText(contact.getName());

        //TextView tv_number = customView.findViewById(R.id.custom_tv_number);
        holder.tv_number.setText(contact.getNumber());

        return customView;
    }
}
