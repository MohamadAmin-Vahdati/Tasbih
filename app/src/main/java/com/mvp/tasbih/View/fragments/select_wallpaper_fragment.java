package com.mvp.tasbih.View.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import com.mvp.tasbih.R;

public class select_wallpaper_fragment extends Fragment  {

    private View view ;
    private AppCompatButton btn_ConfirmWallpaper ;
    private RadioGroup radioGroup ;
    private AppCompatRadioButton rb_Wall1 , rb_Wall2 , rb_Wall3 , rb_Wall4 , rb_Wall5 ;
    private RelativeLayout layout_SelectWallpaperPage ;
    private SharedPreferences sharedPreferences ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_select_wallpaper,container,false);

        sharedPreferences = getActivity().getSharedPreferences("Wallpaper",getContext().MODE_PRIVATE);
        FindViews();
        initRBandWallpaper();
        SetChangeListener();
        SetClickListener();

        return view ;
    }

    private void initRBandWallpaper(){
        switch (sharedPreferences.getInt("WallpaperID",R.drawable.bg_1)){
            case R.drawable.bg_1 :
                rb_Wall1.setChecked(true);
                layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_1);
                break;
            case R.drawable.bg_2 :
                rb_Wall2.setChecked(true);
                layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_2);
                break;
            case R.drawable.bg_3 :
                rb_Wall3.setChecked(true);
                layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_3);
                break;
            case R.drawable.bg_4 :
                rb_Wall4.setChecked(true);
                layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_4);
                break;
            case R.drawable.bg_5 :
                rb_Wall5.setChecked(true);
                layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_5);
                break;
        }
    }

    private void FindViews(){
        radioGroup = view.findViewById(R.id.radio_group);
        rb_Wall1 = view.findViewById(R.id.RB_Wall1);
        rb_Wall2 = view.findViewById(R.id.RB_Wall2);
        rb_Wall3 = view.findViewById(R.id.RB_Wall3);
        rb_Wall4 = view.findViewById(R.id.RB_Wall4);
        rb_Wall5 = view.findViewById(R.id.RB_Wall5);
        btn_ConfirmWallpaper = view.findViewById(R.id.btn_ConfirmWallpaper);
        layout_SelectWallpaperPage = getActivity().findViewById(R.id.layout_container);
    }

    private void SetChangeListener (){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.RB_Wall1 :
                        layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_1);
                        break;
                    case R.id.RB_Wall2 :
                        layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_2);
                        break;
                    case R.id.RB_Wall3 :
                        layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_3);
                        break;
                    case R.id.RB_Wall4 :
                        layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_4);
                        break;
                    case R.id.RB_Wall5 :
                        layout_SelectWallpaperPage.setBackgroundResource(R.drawable.bg_5);
                        break;
                }
            }
        });
    }

    private void SetClickListener(){
        btn_ConfirmWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.RB_Wall1 :
                        SetSharedPreferences(R.drawable.bg_1);
                        break;
                    case R.id.RB_Wall2 :
                        SetSharedPreferences(R.drawable.bg_2);
                        break;
                    case R.id.RB_Wall3 :
                        SetSharedPreferences(R.drawable.bg_3);
                        break;
                    case R.id.RB_Wall4 :
                        SetSharedPreferences(R.drawable.bg_4);
                        break;
                    case R.id.RB_Wall5 :
                        SetSharedPreferences(R.drawable.bg_5);
                        break;
                }
                getActivity().onBackPressed();

            }
        });

    }

    private void SetSharedPreferences(int ID){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("WallpaperID",ID) ;
        editor.apply();
    }

}
