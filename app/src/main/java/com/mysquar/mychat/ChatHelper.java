package com.mysquar.mychat;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.mysquar.mychat.models.ChatItem;

/**
 * Created by kimhieu on 1/22/16.
 */
public class ChatHelper {

    private static ChatHelper instance;
    private Context context;
    final String KEY_CHAT_USERNAME = "com.mysquar.mychat.ChatUsername";
    final String KEY_CHAT_SHAREREF = "com.mysquar.mychat.chat";

    public static ChatHelper getInstance(Context context){
        if(instance == null){
            instance = new ChatHelper(context);
        }
        return instance;
    }

    private ChatHelper(Context context){
        this.context = context;
    }

    private static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    
    public String getChatUsername(){
        String username = getCreatedUsername();
        if(username.equals("")){
            String deviceId = ChatHelper.getDeviceId(context);
            String substr = deviceId.substring(deviceId.length() - 4);
            username = "USER_"+substr;
            saveCreatedUsername(username);
        }
        return username;
    }

    private String getCreatedUsername(){
        Context context = this.context;
        SharedPreferences sharedPref = context.getSharedPreferences(
                KEY_CHAT_SHAREREF, Context.MODE_PRIVATE);
        String username = sharedPref.getString(KEY_CHAT_USERNAME,"");
        return username;
    }

    private void saveCreatedUsername(String username){
        Context context = this.context;
        SharedPreferences sharedPref = context.getSharedPreferences(
                KEY_CHAT_SHAREREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY_CHAT_USERNAME,username);
        editor.commit();
    }

}
