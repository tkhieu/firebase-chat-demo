package com.mysquar.chat.helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mysquar.chat.models.ChatItem;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {


    private final String CHAT_COLLECTION = "chat";


    private static FirebaseHelper instance;
    private DatabaseReference firebaseClient;

    public static FirebaseHelper getInstance(){
        if(instance == null){
            instance = new FirebaseHelper();
        }
        return instance;
    }

    private FirebaseHelper(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        firebaseClient = FirebaseDatabase.getInstance().getReference();

    }

    public String saveChatItem(ChatItem chatItem){
        DatabaseReference chatCollectionRef = firebaseClient.child(CHAT_COLLECTION);
        DatabaseReference newChatItem = chatCollectionRef.push();
        newChatItem.setValue(chatItem);
        return newChatItem.getKey();
    }

    public DatabaseReference getChatFirebaseClient(){
        return firebaseClient.child(CHAT_COLLECTION);
    }

    public void updateChatItemStatus(ChatItem item) {
        DatabaseReference firebaseItem = getChatFirebaseClient().child(item.getId());
        Map<String, Object> status = new HashMap<>();
        status.put("status", item.getStatus());
        firebaseItem.updateChildren(status);
    }
}
