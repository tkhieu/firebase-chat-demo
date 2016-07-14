package com.mysquar.chat.helpers;

import com.mysquar.chat.models.ChatItem;

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
