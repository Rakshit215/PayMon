package com.example.rakshit.paymon.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakshit.paymon.Activity.CardActivity;
import com.example.rakshit.paymon.Adapter.CardAdapter;
import com.example.rakshit.paymon.Adapter.TabPagerAdapter;
import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.R;
import com.viewpagerindicator.TabPageIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardPaymentFragment extends android.support.v4.app.Fragment implements ViewPager.OnPageChangeListener{
    private FragmentManager fragmentManager;
    private ActionBar.TabListener tabListener;
public         ViewPager viewPager;
public CardActivity cardActivity;
    public String response[]={"Wallet","Debit Card","Credit Card"};
    public Bundle bundle;


    public CardPaymentFragment()
    {

    }

    public static CardPaymentFragment newInstance()
    {
        CardPaymentFragment fragment = new CardPaymentFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.PagerIndicatorDefaultNewWithDivider);
        bundle=getArguments();

        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        fragmentManager = getActivity().getSupportFragmentManager();

        View rootView = localInflater.inflate(R.layout.fragment_card_payment, container, false);

        FragmentStatePagerAdapter adapter = new CardAdapter(getChildFragmentManager());
cardActivity=(CardActivity)getActivity();

        cardActivity.getSupportActionBar().setHomeButtonEnabled(true);
        cardActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        cardActivity.getSupportActionBar().setDisplayUseLogoEnabled(true);

        cardActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

viewPager= (ViewPager) rootView.findViewById(R.id.pager);

        viewPager.addOnPageChangeListener(this);


   PagerAdapter pagerAdapter;


        pagerAdapter=new TabPagerAdapter(fragmentManager,response,bundle);

        viewPager.setAdapter(pagerAdapter);
        tabListener=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };


        for (int i = 0; i <response.length; i++) {
            cardActivity.getSupportActionBar().addTab(
                    cardActivity.getSupportActionBar().newTab()
                            .setText(response[i])
                            .setTabListener(tabListener));
        }

        viewPager.setCurrentItem(0);

        return rootView;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

    @Override
    public void onPageSelected(int position) {
        cardActivity.getSupportActionBar().setSelectedNavigationItem(position);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
