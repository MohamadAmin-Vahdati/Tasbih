package com.mvp.tasbih.View.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.DataBase.MyDataBase;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener2;
import com.mvp.tasbih.R;
import com.mvp.tasbih.View.Dialogs.ZekrDialog;

public class main_fragment extends Fragment implements View.OnClickListener, SelectClickListener2 {

    private View view ;
    private MyDataBase dataBase ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_main,container,false);

        dataBase  = Room.databaseBuilder(getContext() , MyDataBase.class,"AzkarDB").allowMainThreadQueries().build();
        SetOnClickListener();



        return view ;
    }

    private void SetOnClickListener() {
        view.findViewById(R.id.btn_ZekrRooz).setOnClickListener(this);
        view.findViewById(R.id.btn_Tasbihat).setOnClickListener(this);
        view.findViewById(R.id.btn_ZekrShomar).setOnClickListener(this);
        view.findViewById(R.id.btn_SalavatShomar).setOnClickListener(this);
        view.findViewById(R.id.btn_SelectWallpaperPage).setOnClickListener(this);
    }

    private void GoToFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left
                        ,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.layout_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ZekrRooz :
                GoToFragment(new zekr_rouz_fragment());
                break;

            case R.id.btn_Tasbihat:
                GoToFragment(new counter_fragment(dataBase.azkarDAO().getZekr(8)));
                break;

            case R.id.btn_ZekrShomar :
                GoToFragment(new azkar_page_fragment());
                break;

            case R.id.btn_SalavatShomar :
                ZekrDialog zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(11),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;

            case R.id.btn_SelectWallpaperPage :
                GoToFragment(new select_wallpaper_fragment());
                break;
        }
    }

    @Override
    public void ChangeDataBase(Zekr zekr) {
        dataBase.azkarDAO().Update(zekr);
    }

    @Override
    public void GotoFragment(Zekr zekr) {
        GoToFragment(new counter_fragment(zekr));

    }
}
