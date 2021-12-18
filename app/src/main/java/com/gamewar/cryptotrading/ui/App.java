package com.gamewar.cryptotrading.ui;

import androidx.annotation.NonNull;
import com.gamewar.cryptotrading.R;

import com.google.firebase.FirebaseApp;
import com.traffappscorelib.wsc.IntroItem;

import java.util.ArrayList;
import java.util.List;

public class App extends com.traffappscorelib.wsc.App {

@Override
public void onCreate() {
    super.onCreate();
    FirebaseApp.initializeApp(getApplicationContext());
}

@NonNull
@Override
public Class<?> getAppUiClassName() {
    return StartActivity.class;
}

@Override
public int getIntroBgColor() {
    return R.color.black;
}

@NonNull
@Override
public List<IntroItem> getIntroItems() {
    return null;
}

@Override
public boolean showIntro() {
    return false;
}
}
