package com.example.rakshit.paymon.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rakshit.paymon.NetworkController.Login;
import com.example.rakshit.paymon.NetworkController.WalletAmt;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.PrefUtils;


public class SplashActivity extends ActionBarActivity {
    public WalletAmt walletAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        PrefUtils.initPrefUtils(this);



        Thread t=new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(4000);

                    Intent i=null;
                    if(PrefUtils.getApplicationStatus())
                    {
                        walletAmt=new WalletAmt(SplashActivity.this);
                        walletAmt.execute();

                    }
                    else
                    { i= new Intent(SplashActivity.this,LoginActivity.class);


                    finish();
                    startActivity(i);}
                }
                catch(InterruptedException e)
                {

                }
            }
        };
        t.start();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
