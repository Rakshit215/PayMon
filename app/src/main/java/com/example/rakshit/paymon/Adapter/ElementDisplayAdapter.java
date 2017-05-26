package com.example.rakshit.paymon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.R;

/**
 * Created by Rakshit on 08-04-2017.
 */
public class ElementDisplayAdapter extends RecyclerView.Adapter<ElementDisplayAdapter.ViewHolder> implements View.OnClickListener
        {
public Context context;
private View view;
private ViewHolder viewHolder;
            public String list[]={"DTH","Recharge","Bus","Wallet Recharge","Metro Recharge"};
            public int image[]={R.drawable.dth,R.drawable.mobile,R.drawable.bus,R.drawable.wallet,R.drawable.metro};
            private OnFragmentCommunicationListener communicationListener;


public ElementDisplayAdapter(Context context)
        {
        this.context=context;

            communicationListener=(OnFragmentCommunicationListener)context;


        }

@Override
public ElementDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
        view = LayoutInflater.from(this.context).inflate(R.layout.element_grid_layout, null);
        viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
        }

@Override
public void onBindViewHolder(ElementDisplayAdapter.ViewHolder holder, int position)
        {

        viewHolder.elementDetail.setText(list[position]);
            viewHolder.elementImg.setImageResource(image[position]);
            viewHolder.elementid.setText(String.valueOf(position+20));
        }

@Override
public int getItemCount() {
        return list.length;
        }

@Override
public void onClick(View v)
        {
        String productID=String.valueOf(((TextView)(v.findViewById(R.id.element_id))).getText().toString());
     //   Toast.makeText(context, productID, Toast.LENGTH_LONG).show();
            String pp=String.valueOf(((TextView)(v.findViewById(R.id.tv_element_detail))).getText().toString());
            communicationListener.onFragmentCommunicated(Integer.parseInt(productID),pp);

        }


public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView elementImg;
    public TextView elementDetail;
    public TextView elementid;




    public ViewHolder(View itemView)
    {
        super(itemView);
        elementImg = (ImageView) itemView.findViewById(R.id.element_img);
        elementDetail= (TextView) itemView.findViewById(R.id.tv_element_detail);

        elementid= (TextView) itemView.findViewById(R.id.element_id);

    }
}
}
