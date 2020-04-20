package com.mvp.tasbih.View.fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.DataBase.MyDataBase;
import com.mvp.tasbih.Presenter.Interface.AddZekrClickListener;
import com.mvp.tasbih.Presenter.Interface.ConfirmtClickListener;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener2;
import com.mvp.tasbih.View.Dialogs.AddZekrDialog;
import com.mvp.tasbih.View.Dialogs.ConfirmDialog;
import com.mvp.tasbih.View.Dialogs.ZekrDialog;
import com.mvp.tasbih.Presenter.adapter.MyAdapter;
import com.mvp.tasbih.R;

import java.util.List;

public class azkar_page_fragment extends Fragment implements SelectClickListener, SelectClickListener2, View.OnClickListener, AddZekrClickListener, ConfirmtClickListener {

    private RecyclerView recyclerView ;
    private MyAdapter adapter ;
    private MyDataBase dataBase ;
    private int ID_delete ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_azkar,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        view.findViewById(R.id.btn_AddZekr).setOnClickListener(this);
        initRecycler();



        return view ;
    }


    private void initRecycler(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);

        initAdapter();

        recyclerView.setAdapter(adapter);

    }

    private void initAdapter(){
        dataBase  = Room.databaseBuilder(getContext() , MyDataBase.class,"AzkarDB").allowMainThreadQueries().build();
        List<Zekr> Azkar = dataBase.azkarDAO().getAzkarForAzkarPage() ;
        adapter = new MyAdapter(Azkar,this,FindDensity(),getResources().getBoolean(R.bool.isTablet));
    }

    private int FindDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    @Override
    public void ZekrClickListener(int ID) {
        ZekrDialog zekrDialog = new ZekrDialog(dataBase.azkarDAO().getZekr(ID),getActivity(),this);
        zekrDialog.ShowSelectDialog();
    }




    @Override
    public void DeleteClickListener(int ID) {
        ID_delete = ID ;

        ConfirmDialog confirmDialog = new ConfirmDialog(getActivity(),this);
        confirmDialog.ShowConfirmDialog();
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

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_AddZekr){
            AddZekrDialog addZekrDialog = new AddZekrDialog(getActivity(),FindDensity(),this);
            addZekrDialog.ShowDialog();
        }
    }

    @Override
    public void AddZekr(Zekr zekr) {
        dataBase.azkarDAO().insert(zekr);
        adapter.UpdateAzkar(dataBase.azkarDAO().getAzkarForAzkarPage());
    }

    @Override
    public void Confirmed() {
        dataBase.azkarDAO().Delete(ID_delete);
        List<Zekr> Azkar = dataBase.azkarDAO().getAzkarForAzkarPage() ;
        adapter = new MyAdapter(Azkar,this,FindDensity(),getResources().getBoolean(R.bool.isTablet));
        recyclerView.setAdapter(adapter);
        Toast.makeText(getActivity(), "ذکر با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
    }
}
