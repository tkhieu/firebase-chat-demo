package com.mysquar.mychat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.mysquar.mychat.ChatHelper;
import com.mysquar.mychat.FirebaseHelper;
import com.mysquar.mychat.R;
import com.mysquar.mychat.adapters.ChatViewAdapter;
import com.mysquar.mychat.models.ChatItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.editTextChatInput)
    EditText editTextChatInput;
    @Bind(R.id.listViewChatContent)
    ListView listViewChatContent;

    List<ChatItem> chatItemList;
    ChatViewAdapter adapter;
    String username;

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
        editTextChatInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String content = v.getText().toString();

                if (content.equals("")) {
                    return false;
                }
                ChatItem item = new ChatItem(username, content);
                item.setStatus("sending");
                chatItemList.add(item);
                adapter.notifyDataSetChanged();
                listViewChatContent.smoothScrollToPosition(adapter.getCount()-1);
                String id = FirebaseHelper.getInstance().saveChatItem(item);
                item.setId(id);
                editTextChatInput.setText("");
                return true;
            }
        });


        //TODO: Will move it to presenter to have best design and remove logic code inside activity
        FirebaseHelper.getInstance().getChatFirebaseClient().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatItem item = dataSnapshot.getValue(ChatItem.class);
                item.setId(dataSnapshot.getKey());
                if(ChatHelper.getInstance(MainActivity.this).isFromThisUser(item)){
                    item.setStatus("delivered");
                } else {
                    item.setStatus("received");
                }
                FirebaseHelper.getInstance().updateChatItemStatus(item);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
