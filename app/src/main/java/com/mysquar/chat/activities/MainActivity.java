package com.mysquar.chat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mysquar.chat.helpers.ChatHelper;
import com.mysquar.chat.helpers.ChatListHelper;
import com.mysquar.chat.helpers.FirebaseHelper;
import com.mysquar.chat.R;
import com.mysquar.chat.adapters.ChatViewAdapter;
import com.mysquar.chat.models.ChatItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.editTextChatInput)
    EditText editTextChatInput;
    @Bind(R.id.listViewChatContent)
    ListView listViewChatContent;

    List<ChatItem> chatItemList;
    ChatViewAdapter adapter;
    String username;
    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            String content = v.getText().toString();

            if (content.equals("")) {
                return false;
            }


            ChatItem item = new ChatItem(username, content);
            item.setStatus(ChatHelper.CHAT_STATUS_SENDING);
            chatItemList.add(item);
            adapter.notifyDataSetChanged();


            listViewChatContent.smoothScrollToPosition(adapter.getCount() - 1);
            String id = FirebaseHelper.getInstance().saveChatItem(item);
            item.setId(id);
            editTextChatInput.setText("");
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        chatItemList = new ArrayList<>();
        username = ChatHelper.getInstance(MainActivity.this).getChatUsername();
        adapter = new ChatViewAdapter(MainActivity.this, 0, chatItemList);
        listViewChatContent.setAdapter(adapter);
        editTextChatInput.setImeActionLabel("Send", KeyEvent.KEYCODE_ENTER);
        editTextChatInput.setOnEditorActionListener(onEditorActionListener);

        // Send typing status
         editTextChatInput.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 Log.d("Before",charSequence.toString());
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 Log.d("On",charSequence.toString());
             }

             @Override
             public void afterTextChanged(Editable editable) {
                 Log.d("After",editable.toString());
             }
         });


        //TODO: Will move it to presenter to have best design and remove logic code inside activity
        FirebaseHelper.getInstance().getChatFirebaseClient().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatItem tempItem = new ChatItem(dataSnapshot);

                if (ChatHelper.getInstance(MainActivity.this).isFromThisUser(tempItem)) {
                    if (tempItem.getStatus().equals(ChatHelper.CHAT_STATUS_SENDING)) {
                        tempItem.setStatus(ChatHelper.CHAT_STATUS_DELIVERED);
                        FirebaseHelper.getInstance().updateChatItemStatus(tempItem);
                    }
                } else {
                    tempItem.setStatus(ChatHelper.CHAT_STATUS_RECEIVED);
                    FirebaseHelper.getInstance().updateChatItemStatus(tempItem);
                }

                ChatItem item = ChatListHelper.findItem(chatItemList, tempItem);
                if (item == null) {
                    chatItemList.add(tempItem);
                }
                adapter.notifyDataSetChanged();
                listViewChatContent.smoothScrollToPosition(adapter.getCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ChatItem tempItem = new ChatItem(dataSnapshot);
                ChatItem item = ChatListHelper.findItem(chatItemList, tempItem);
                if (item == null) {
                    chatItemList.add(tempItem);

                } else {
                    item.setStatus(tempItem.getStatus());
                    item.setUsername(tempItem.getUsername());
                    item.setMessage(tempItem.getMessage());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError DatabaseError) {

            }
        });

        Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
    }
}
