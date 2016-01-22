package com.mysquar.mychat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
