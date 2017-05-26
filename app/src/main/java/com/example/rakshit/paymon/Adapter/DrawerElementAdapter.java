package com.example.rakshit.paymon.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Activity.LoginActivity;
import com.example.rakshit.paymon.Activity.TransactionActivity;
import com.example.rakshit.paymon.NetworkController.Transaction;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.Constants;
import com.example.rakshit.paymon.Utilities.PrefUtils;

/**
 * Created by Rakshit on 07-04-2017.
 */
public class DrawerElementAdapter extends RecyclerView.Adapter<DrawerElementAdapter.ViewHolder>
        {
            public Activity context;
            private View view;
            private ViewHolder viewHolder;
            private String drawerTitle[];
            private int drawerElementIcon[]={R.drawable.home,R.drawable.note,R.drawable.logout};




            public DrawerElementAdapter(Activity context)
            {
                this.context=context;
                drawerTitle=(context.getResources()).getStringArray(R.array.drawer_title);
            //    drawerElementIcon=(context.getResources()).getIntArray(R.array.drawer_element_icon);
            }

            @Override
            public DrawerElementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
            {
                if (viewType == Constants.DRAWER_FOOTER) {
                    view= LayoutInflater.from(context).inflate(R.layout.drawer_row,parent,false);
                    viewHolder= new ViewHolder(context,view,viewType);

                } else if (viewType == Constants.DRAWER_HEADER)
                {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header,parent,false);
                    viewHolder= new ViewHolder(context,view,viewType);

                }

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(DrawerElementAdapter.ViewHolder holder, int position)
            {
                if(holder.holderId==Constants.DRAWER_HEADER)
                {

                    holder.profile_pic.setImageDrawable(context.getResources().getDrawable(R.drawable.app_icon));
                    holder.userName.setText(PrefUtils.getUserName());
                    holder.userEmail.setText(PrefUtils.getUserEmail());

                }
                else
                {

                    holder.rowTitle.setText(drawerTitle[position - 1]);
                    holder.rowIcon.setImageResource(drawerElementIcon[position - 1]);


                }

            }

            @Override
            public int getItemCount()
            {
                return drawerTitle.length+1;
            }

            @Override
            public int getItemViewType(int position)
            {

                if(isPositionHeader(position))
                    return Constants.DRAWER_HEADER;

                return Constants.DRAWER_FOOTER;
            }

            public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            {
                private ImageView profile_pic;
                private TextView userName;
                private TextView userEmail;
                private TextView rowTitle;
                private ImageView rowIcon;
                private int holderId;
                private Activity mContext;
                public Transaction transaction;


                public ViewHolder(Activity mcontext,View itemView,int ViewType)
                {
                    super(itemView);
                    this.mContext=mcontext;

                    itemView.setClickable(true);
                    itemView.setSelected(true);
                    itemView.setOnClickListener(this);

                    if(ViewType == Constants.DRAWER_FOOTER) {
                        rowTitle = (TextView) itemView.findViewById(R.id.rowText);
                        rowIcon = (ImageView) itemView.findViewById(R.id.rowIcon);
                        holderId = Constants.DRAWER_FOOTER;
                    }
                    else{


                        userName = (TextView) itemView.findViewById(R.id.tv_user_name);
                        userEmail = (TextView) itemView.findViewById(R.id.tv_user_email);
                        profile_pic = (ImageView) itemView.findViewById(R.id.cv_iv_user_img);
                        holderId = Constants.DRAWER_HEADER;
                    }

                }

                @Override
                public void onClick(View v)
                {


              TextView tv=(TextView)v.findViewById(R.id.rowText);
                   String str=tv.getText().toString();

                    if(str.equalsIgnoreCase("LogOut"))
                    {
                        Intent i=new Intent(mContext, LoginActivity.class);
                        PrefUtils.removeUserEmail();
                        PrefUtils.removeUserId();
                        PrefUtils.removeUserName();
                        PrefUtils.removeUserNumber();
                        PrefUtils.removeWalletAmt();
                        PrefUtils.setApplicationStatus(false);

                        mContext.finish();
                        mContext.startActivity(i);



                    }
                    else
                        if(str.equalsIgnoreCase("Transaction History"))
                        {
                            transaction=new Transaction(mContext);
                            transaction.execute();


                        }

                }
            }

            private boolean isPositionHeader(int position)
            {
                return position==0;
            }

        }
