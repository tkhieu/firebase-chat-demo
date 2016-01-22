package com.mysquar.mychat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mysquar.mychat.R;
import com.mysquar.mychat.models.ChatItem;

import java.util.List;

/**
 * Created by kimhieu on 1/22/16.
 */
public class ChatViewAdapter extends ArrayAdapter<ChatItem> {

    private Context context;
    List<ChatItem> chatItemList;

    public ChatViewAdapter(Context context, int resource, List<ChatItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.chatItemList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cell_chat_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text_chat_content);
        ChatItem item = chatItemList.get(position);
        textView.setText(item.getMessage());

        return rowView;
    }
}
