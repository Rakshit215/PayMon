package com.example.rakshit.paymon.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rakshit.paymon.Fragment.CardPaymentFragment;
import com.example.rakshit.paymon.Fragment.DebitCreditCardFragment;
import com.example.rakshit.paymon.Fragment.WalletPayFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rakshit on 23-03-2017.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter implements Serializable
{
    private String[] tabLists;
   private Fragment fragment;
    private Bundle bundle;


    public TabPagerAdapter(FragmentManager fm,String[] payment_type,Bundle b) {

        super(fm);
  //      responseModel=(TabResponseModel) JsonUtils.objectify(response,TabResponseModel.class);
        tabLists=payment_type;
        bundle=b;

    }

    @Override
    public Fragment getItem(int position)
    {


        if(isFeaturedPosition(position)) {
            fragment = new WalletPayFragment().newInstance();
            fragment.setArguments(bundle);
        }
        else
        {
            fragment=new DebitCreditCardFragment().newInstance();
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount()
    {

        return tabLists.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return super.getPageTitle(position);
    }

    private boolean isFeaturedPosition(int position)
    {
        return position==0;
    }
}
