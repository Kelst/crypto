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
    return MainActivity.class;
}

@Override
public int getIntroBgColor() {
    return R.color.black;
}

@NonNull
@Override
public List<IntroItem> getIntroItems() {
    List<IntroItem> list = new ArrayList<>();
    list.add(new IntroItem("Gifts unboxing", "Click on the box and win free gifts.", R.drawable.ic_search));
    list.add(new IntroItem("Daily", "You have 5 attempts to open box everyday. Good luck!", R.drawable.ic_search));
    return list;
}

@Override
public boolean showIntro() {
    return true;
}
}
