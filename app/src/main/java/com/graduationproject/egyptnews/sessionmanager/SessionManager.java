package com.graduationproject.egyptnews.sessionmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private Context context;

    private final static String IS_LOGGED = "is_logged";
    public final static String KEY_NAME = "name";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_PASSWORD = "password";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("pref", 0);
        editor = pref.edit();
    }

    public String getEmail()
    {
        return pref.getString("KEY_EMAIL","null");
    }

    public void createUserSession(String email,String password, String name){
        editor.putBoolean(IS_LOGGED,true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public boolean checkUserSession(){
        return pref.getBoolean(IS_LOGGED,false);
    }

    public void clearSession(){
        editor.clear();
        editor.commit();
    }

    public String getUserEmail(){
        return pref.getString(KEY_EMAIL,null);
    }
    public String getUserName(){
        return pref.getString(KEY_NAME,"ahmed");
    }
    public void setUserName(String name){
        editor.putString(KEY_NAME,name);
        editor.commit();
    }

}
