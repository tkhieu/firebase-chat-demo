package com.mysquar.mychat.helpers;

import android.content.ClipData;

import com.mysquar.mychat.models.ChatItem;

import java.util.List;

/**
 * Created by kimhieu on 1/22/16.
 */
public class ChatListHelper {

    public static ChatItem findItem(List<ChatItem> list, ChatItem item){
        for (ChatItem i : list) {
            if(i.getId().equals(item.getId())){
                return  i;
            }
        }
        return null;
    }

}
