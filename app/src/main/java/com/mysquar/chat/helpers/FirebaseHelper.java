package com.mysquar.chat.helpers;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mysquar.chat.models.ChatItem;
import com.mysquar.chat.service.FirebaseMessageService;
import com.mysquar.chat.service.InstanceIdService;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FirebaseHelper {


    private final String CHAT_COLLECTION = "chat";


    private static FirebaseHelper instance;
    private DatabaseReference firebaseClient;

    public static FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    private FirebaseHelper() {
        Context context = getApplicationContext();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        firebaseClient = FirebaseDatabase.getInstance().getReference();
        Intent fbmIDService = new Intent(context, InstanceIdService.class);
        context.startService(fbmIDService);
        Intent fbmService = new Intent(context, FirebaseMessageService.class);
        context.startService(fbmService);
    }

    public String saveChatItem(ChatItem chatItem) {
        DatabaseReference chatCollectionRef = firebaseClient.child(CHAT_COLLECTION);
        DatabaseReference newChatItem = chatCollectionRef.push();
        newChatItem.setValue(chatItem);
        return newChatItem.getKey();
    }

    public DatabaseReference getChatFirebaseClient() {
        return firebaseClient.child(CHAT_COLLECTION);
    }

    public void updateChatItemStatus(ChatItem item) {
        DatabaseReference firebaseItem = getChatFirebaseClient().child(item.getId());
        Map<String, Object> status = new HashMap<>();
        status.put("status", item.getStatus());
        firebaseItem.updateChildren(status);
    }
}
