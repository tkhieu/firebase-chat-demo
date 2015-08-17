package com.mysquar.mychat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayAdapter mChatListAdapter;
    private ArrayList<String> mMessages;
    private ListView mChatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);
        mChatListView = (ListView) findViewById(R.id.chatListView);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        EditText inputText = (EditText) findViewById(R.id.messageInput);

        inputText
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {

                    @Override
                    public boolean onEditorAction(TextView textView,
                                                  int actionId, KeyEvent keyEvent) {
                        if (actionId == EditorInfo.IME_NULL
                                && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                            sendMessage();
                        }
                        return true;
                    }
                });

        mMessages = new ArrayList<String>();
        mChatListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, mMessages);
        mChatListView.setAdapter(mChatListAdapter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void onChatSubmitClick(View view) {
        sendMessage();
    }

    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            mMessages.add(input);
            mChatListAdapter.notifyDataSetChanged();
            inputText.setText("");
        }
    }
}
