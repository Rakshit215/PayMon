package com.example.rakshit.paymon.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rakshit.paymon.Fragment.DebitCreditCardFragment;

/**
 * Created by Rakshit on 23-03-2017.
 */
public class CardAdapter extends FragmentStatePagerAdapter {
    public CardAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
     //   if (position == 0) {
      //      return SavedOptionsFragment.newInstance(paymentType, amount);
       // }
       // else if (position == 1)
       // {
            return DebitCreditCardFragment.newInstance();
        //}

    }




    @Override
    public CharSequence getPageTitle(int position) {
        return "hello";
    }

    @Override
    public int getCount() {
        return 4;
    }

}
