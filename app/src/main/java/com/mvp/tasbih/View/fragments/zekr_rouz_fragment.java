package com.mvp.tasbih.View.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.DataBase.MyDataBase;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener2;
import com.mvp.tasbih.R;
import com.mvp.tasbih.View.Dialogs.ZekrDialog;

import java.util.Calendar;

public class zekr_rouz_fragment extends Fragment implements View.OnClickListener, SelectClickListener2 {

    private View view ;
    private AppCompatButton Shanbe , YekShanbe , DoShanbe , SeShanbe , ChaharShanbe , PanjShanbe , Jome ;
    private MyDataBase dataBase ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_zekr_rouz,container,false);

        dataBase  = Room.databaseBuilder(getContext() , MyDataBase.class,"AzkarDB").allowMainThreadQueries().build();
        FindViews();
        DayofWeek();
        SetClickListeners();

        return view ;
    }

    private void FindViews(){
        Shanbe = view.findViewById(R.id.btn_Shanbe);
        YekShanbe = view.findViewById(R.id.btn_YekShanbe);
        DoShanbe = view.findViewById(R.id.btn_DoShanbe);
        SeShanbe = view.findViewById(R.id.btn_SeShanbe);
        ChaharShanbe = view.findViewById(R.id.btn_ChaharShanbe);
        PanjShanbe = view.findViewById(R.id.btn_PanjShanbe);
        Jome = view.findViewById(R.id.btn_Jome);

    }

    private void SetClickListeners(){
        Shanbe.setOnClickListener(this);
        YekShanbe.setOnClickListener(this);
        DoShanbe.setOnClickListener(this);
        SeShanbe.setOnClickListener(this);
        ChaharShanbe.setOnClickListener(this);
        PanjShanbe.findViewById(R.id.btn_PanjShanbe).setOnClickListener(this);
        Jome.setOnClickListener(this);
    }

    private void DayofWeek(){
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SATURDAY :
                Shanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.SUNDAY:
                YekShanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.MONDAY :
                DoShanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.TUESDAY :
                SeShanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.WEDNESDAY :
                ChaharShanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.THURSDAY :
                PanjShanbe.setTextColor(getResources().getColor(R.color.Red));
                break;
            case Calendar.FRIDAY :
                Jome.setTextColor(getResources().getColor(R.color.Red));
                break;
        }
    }



    @Override
    public void onClick(View v) {
        ZekrDialog zekrDialog ;
        switch (v.getId()){

            case R.id.btn_Shanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(1),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_YekShanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(2),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_DoShanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(3),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_SeShanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(4),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_ChaharShanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(5),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_PanjShanbe :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(6),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
            case R.id.btn_Jome :
                zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(7),getActivity(),this);
                zekrDialog.ShowSelectDialog();
                break;
        }
    }

    @Override
    public void ChangeDataBase(Zekr zekr) {
        dataBase.azkarDAO().Update(zekr);
    }

    @Override
    public void GotoFragment(Zekr zekr) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left
                        ,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.layout_container, new counter_fragment(zekr))
                .addToBackStack(null)
                .commit();
    }
}
