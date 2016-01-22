package com.mysquar.mychat;

import com.mysquar.mychat.models.ChatItem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kimhieu on 1/22/16.
 */
public class FirebaseHelperTest {

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testSaveChatItem() throws Exception {
        ChatItem chatItem = new ChatItem("USER_1", "Messeger 1");
        FirebaseHelper.getInstance().saveChatItem(chatItem);

    }
}