package com.mysquar.chat.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by kimhieu on 7/21/16.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "FBMID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        String id = FirebaseInstanceId.getInstance().getId();
        Log.d(TAG, String.format("Token: %s",token));
        Log.d(TAG, String.format("Id: %s",id));

    }
}
