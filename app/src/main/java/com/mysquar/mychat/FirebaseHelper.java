package com.mysquar.mychat;

import android.content.Context;

import com.firebase.client.Firebase;
import com.mysquar.mychat.models.ChatItem;

/**
 * Created by kimhieu on 1/22/16.
 */
public class FirebaseHelper {


    private static String CHAT_COLLECTION = "chat";


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

    public void saveChatItem(ChatItem chatItem){
        Firebase chatCollectionRef = firebaseClient.child(CHAT_COLLECTION);
        Firebase newChatItem = chatCollectionRef.push();
        newChatItem.setValue(chatItem.getMessage());
    }

}
