package com.mysquar.mychat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mysquar.mychat.ChatHelper;
import com.mysquar.mychat.R;
import com.mysquar.mychat.models.ChatItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.himanshusoni.chatmessageview.ChatMessageView;

/**
 * Created by kimhieu on 1/22/16.
 */
public class ChatViewAdapter extends ArrayAdapter<ChatItem> {

    private Context context;
    List<ChatItem> chatItemList;
    LayoutInflater inflater;

    public ChatViewAdapter(Context context, int resource, List<ChatItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.chatItemList = objects;
        this.inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ChatItem item = chatItemList.get(position);
        View messageView;
        TextView textViewMessage;
        TextView textViewUsername;
        TextView textViewStatus;
        if(ChatHelper.getInstance(context).isFromThisUser(item)){
            messageView = inflater.inflate(R.layout.cell_chat_item_mine, parent, false);
        } else {
            messageView = inflater.inflate(R.layout.cell_chat_item_other, parent, false);
        }
        textViewMessage = (TextView) messageView.findViewById(R.id.text_view_message);
        textViewUsername = (TextView) messageView.findViewById(R.id.text_view_username);
        textViewStatus = (TextView) messageView.findViewById(R.id.text_view_status);
        textViewMessage.setText(item.getMessage());
        textViewUsername.setText(item.getUsername());
        textViewStatus.setText(item.getStatus());
        return messageView;
    }
}
