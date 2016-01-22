package com.mysquar.mychat;

import android.content.Context;

import com.firebase.client.Firebase;
import com.mysquar.mychat.models.ChatItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kimhieu on 1/22/16.
 */
public class FirebaseHelper {


    final String CHAT_COLLECTION = "chat";


    private static FirebaseHelper instance;
    private Firebase firebaseClient;

    public static FirebaseHelper getInstance(){
        if(instance == null){
            instance = new FirebaseHelper();
        }
        return instance;
    }

    private FirebaseHelper(){
        firebaseClient = new Firebase(AppConfig.FIREBASE_URL);
    }

    public String saveChatItem(ChatItem chatItem){
        Firebase chatCollectionRef = firebaseClient.child(CHAT_COLLECTION);
        Firebase newChatItem = chatCollectionRef.push();
        newChatItem.setValue(chatItem);
        return newChatItem.getKey();
    }

    public Firebase getChatFirebaseClient(){
        Firebase chatCollectionRef = firebaseClient.child(CHAT_COLLECTION);
        return chatCollectionRef;
    }

    public void updateChatItemStatus(ChatItem item) {
        Firebase firebaseItem = getChatFirebaseClient().child(item.getId());
        Map<String, Object> status = new HashMap<String, Object>();
        status.put("status", item.getStatus());
        firebaseItem.updateChildren(status);
    }
}
