package com.mysquar.chat;

/**
 * Created by kimhieu on 1/22/16.
 */

public class AppConfig{


    // Singleton
    private static AppConfig instance = null;
    /**
     * REMEMBER CHANGE IT BEFORE DEPLOY
     */
    private static Mode mode = Mode.PROD;


    public AppConfig(Mode mode) {
        String FIREBASE_URL;
        if (mode == Mode.DEV) {
            FIREBASE_URL = "https://mysquar-test.firebaseio.com";

        } else if (mode == Mode.PROD) {
            FIREBASE_URL = "https://mysquar-test.firebaseio.com";
        }
    }

    /**
     * Get instance about this AppConfig (Singleton)
     * @return Current instance
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig(mode);
        }
        return instance;
    }

    // Define run mode
    public enum Mode {
        DEV(1), PROD(2);
        private int value;

        Mode(int value) {
            this.value = value;
        }
    }

}