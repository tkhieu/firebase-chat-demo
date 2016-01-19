# mysquar-android-test
You are expected to implement a small chat application. 

### 1. Add dummy chat view
* It has a chat message list
* It has an edit text view
* It will append the new text to the list upon submit
* ![Mock 1](https://raw.githubusercontent.com/khacanh/public-assets/master/mocks/chat_1.png)

### 2. Write to Firebase
* For each message submitted in step #1, it should also be saved to Firebase
* Example code:
```java
  Firebase ref = new Firebase(FIREBASE_URL);
  // Use push() to create a unique & chronologically ordered key  
  ref.push().set(message);
```
* Data structure should look like this: 
```json
{
  "chat": {
    $messageId1: "Message 1",
    $messageId2: "Message 2"
  }
}
```
* Save data to Firebase https://www.firebase.com/docs/web/guide/saving-data.html

### 3. Read from Firebase
* Example code:
```java
  Firebase ref = new Firebase(FIREBASE_URL);
  
  // ChildEventListener is best for retrieving items in a list
  ref.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
      // Do stuff
    }
    
    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
    
    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {}
    
    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
    
    @Override
    public void onCancelled(FirebaseError firebaseError) {}
  });
```
* Compile and run the app on 2 devices to test the real-time chat
* Read data from Firebase https://www.firebase.com/docs/android/guide/retrieving-data.html

### 4. Add user name to chat
* Generate a unique user name, `"USER_<UNIQUE_4_DIGITS_NUMBER>"`
* Change data structure to support user name
```json
{
  "chat": {
    $messageId1: {
      "username": "USER_1234",
      "message": "Message 1",
    },
    $messageId2: {
      "username": "USER_5678",
      "message": "Message 2",
    }
  }
}
```
* Change chat message item type to reflect sender and receiver. Sender's messages are aligned to the left, receiver's to the right
* ![Mock 2](https://raw.githubusercontent.com/khacanh/public-assets/master/mocks/chat_2.png)

### 5. Message status
* Support `sending`, `delivered`, `received`
* Show a simple status icon next to the chat message
* You probably want to look back at Firebase API to update data
* ![Mock 3](https://raw.githubusercontent.com/khacanh/public-assets/master/mocks/chat_3.png)
