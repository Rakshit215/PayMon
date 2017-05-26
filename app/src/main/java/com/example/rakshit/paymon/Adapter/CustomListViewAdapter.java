package com.example.rakshit.paymon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Fragment.BrowsePlanFragment;
import com.example.rakshit.paymon.Fragment.RechargeFragment;
import com.example.rakshit.paymon.R;

import java.util.ArrayList;

/**
 * Created by Rakshit on 13-04-2017.
 */
public class CustomListViewAdapter extends ArrayAdapter<DataModel>{

    private ArrayList<DataModel> dataSet;
            Context mContext;



            private static class ViewHolder {
                TextView talktime;
                TextView validity;
                TextView amt;

                LinearLayout ll;
            }

            public CustomListViewAdapter(ArrayList<DataModel> data, Context context) {
                super(context, R.layout.custom_listview_row, data);
                this.dataSet = data;

                this.mContext=context;

            }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_listview_row, parent, false);
            viewHolder.talktime = (TextView) convertView.findViewById(R.id.talktime);
            viewHolder.validity = (TextView) convertView.findViewById(R.id.validity);
            viewHolder.amt=(TextView)convertView.findViewById(R.id.amt);

            viewHolder.ll=(LinearLayout)convertView.findViewById(R.id.detail_id);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.talktime.setText(dataModel.getTalktime());
        viewHolder.validity.setText(dataModel.getValidity());
        viewHolder.amt.setText(dataModel.getAmt());

      //  viewHolder.ll.setOnClickListener(this);

        // Return the completed view to render on screen
        return convertView;
    }



}
