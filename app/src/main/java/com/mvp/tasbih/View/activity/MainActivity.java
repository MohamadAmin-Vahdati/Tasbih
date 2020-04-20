package com.mvp.tasbih.View.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.mvp.tasbih.Presenter.Utility.CheckDateBase;
import com.mvp.tasbih.View.fragments.main_fragment;
import com.mvp.tasbih.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWallpaper();
        CheckDateBase.CheckDataBase(this);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container,new main_fragment()).commit();

    }

    private void initWallpaper(){
        SharedPreferences sharedPreferences = getSharedPreferences("Wallpaper",MODE_PRIVATE);
        findViewById(R.id.layout_container).setBackgroundResource(sharedPreferences.getInt("WallpaperID",R.drawable.bg_1));
    }


}
