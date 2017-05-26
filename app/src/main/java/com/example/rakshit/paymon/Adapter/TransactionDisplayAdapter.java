package com.example.rakshit.paymon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.R;

/**
 * Created by Rakshit on 21-04-2017.
 */
public class TransactionDisplayAdapter extends RecyclerView.Adapter<TransactionDisplayAdapter.ViewHolder> implements View.OnClickListener
{
    public Context context;
    private View view;
    private ViewHolder viewHolder;
    public String [] msj;
    public String [] dat;





    public TransactionDisplayAdapter(Context context,String[] m,String[] d)
    {
        this.context=context;
this.msj=m;
        this.dat=d;

    }

    @Override
    public TransactionDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        view = LayoutInflater.from(this.context).inflate(R.layout.trans_grid_layout, null);
        viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionDisplayAdapter.ViewHolder holder, int position)
    {

        viewHolder.elementDetail.setText(Html.fromHtml(msj[position]));

        viewHolder.elementid.setText(dat[position]);
    }

    @Override
    public int getItemCount() {
        return msj.length;
    }

    @Override
    public void onClick(View v)
    {


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView elementDetail;
        public TextView elementid;




        public ViewHolder(View itemView)
        {
            super(itemView);

            elementDetail= (TextView) itemView.findViewById(R.id.tv_element_detail);

            elementid= (TextView) itemView.findViewById(R.id.element_id);

        }
    }
}
