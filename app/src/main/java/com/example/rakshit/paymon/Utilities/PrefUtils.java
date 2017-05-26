package com.example.rakshit.paymon.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rakshit on 20-04-2017.
 */
public class PrefUtils
{
    private static Context context;
    private static SharedPreferences appInfo;

    public static void initPrefUtils(Context con)
    {
        context = con;

        appInfo=context.getSharedPreferences(Constants.APP_INFO, Activity.MODE_PRIVATE);
    }


    public static void setApplicationStatus(boolean status)
    {
        appInfo.edit().putBoolean("Application_Status",status).commit();
    }

    public static boolean getApplicationStatus()
    {
        return appInfo.getBoolean("Application_Status",false);
    }


    public static String getUserId() {
        return appInfo.getString("user_id","");
    }



    public static void  removeUserId() {
         appInfo.edit().remove("user_id").commit();
    }



    public static void setUserId(String userId) {
        appInfo.edit().putString("user_id", userId).commit();
    }

    public static String getUserNumber() {
        return appInfo.getString("user_number","9760264265");

    }

    public static void removeUserNumber() {
        appInfo.edit().remove("user_number").commit();

    }

    public static void setUserNumber(String userNumber) {
        appInfo.edit().putString("user_number", userNumber).commit();
    }

    public static String getUserName() {
        return appInfo.getString("user_name","");
    }
    public static void removeUserName() {
        appInfo.edit().remove("user_name").commit();
    }


    public static void setUserName(String userName) {
        appInfo.edit().putString("user_name", userName).commit();
    }



    public static String getUserEmail() {
        return appInfo.getString("user_email","rakshitj21@gmail.com");
    }

    public static void removeUserEmail() {
        appInfo.edit().remove("user_email").commit();
    }

    public static void setUserEmail(String userEmail) {
        appInfo.edit().putString("user_email", userEmail).commit();
    }


    public static String getWalletAmt() {
        return appInfo.getString("wallet_amt","0.0");
    }


    public static void removeWalletAmt() {
         appInfo.edit().remove("wallet_amt").commit();
    }


    public static void setWalletAmt(String amt) {
        appInfo.edit().putString("wallet_amt", amt).commit();
    }


}
