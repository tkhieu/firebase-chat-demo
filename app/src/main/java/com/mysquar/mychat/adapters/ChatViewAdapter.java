package com.mysquar.mychat.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.mysquar.mychat.models.ChatItem;

import java.util.List;

/**
 * Created by kimhieu on 1/22/16.
 */
public class ChatViewAdapter extends ArrayAdapter<ChatItem> {

    public ChatViewAdapter(Context context, int resource, List<ChatItem> objects) {
        super(context, resource, objects);
    }
}
