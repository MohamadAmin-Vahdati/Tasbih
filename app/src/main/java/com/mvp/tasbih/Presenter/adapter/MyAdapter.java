package com.mvp.tasbih.Presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener;
import com.mvp.tasbih.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Zekr> zekrList;
    private MyViewHolder viewHolder ;
    private SelectClickListener selectClickListener;
    private int displaydensity ;
    private boolean isTablet ;

    public MyAdapter(List<Zekr> zekrList , SelectClickListener selectClickListener, int displaydensity , boolean isTablet){
        this.zekrList = zekrList;
        this.selectClickListener = selectClickListener;
        this.displaydensity = displaydensity ;
        this.isTablet = isTablet ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_zekr,parent,false);
        viewHolder = new MyViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        viewHolder.bind(zekrList.get(position));
    }

    @Override
    public int getItemCount() {
        return zekrList.size();
    }

    public void UpdateAzkar(List<Zekr> new_Azkar) {
        if (new_Azkar ==null){
            new_Azkar = new ArrayList<>();
        }
        zekrList = new_Azkar ;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView ZekrforAdapter , tedad_gozarande , tedad_dore , btn_Delete , tedad_dore2;
        RelativeLayout zekr_bg ;

        MyViewHolder(@NonNull View view) {
            super(view);

            ZekrforAdapter = view.findViewById(R.id.ZekrforAdapter);
            tedad_dore = view.findViewById(R.id.tedad_dore);
            tedad_gozarande = view.findViewById(R.id.tedad_gozarande);
            tedad_dore2 = view.findViewById(R.id.tedad_dore2);
            btn_Delete = view.findViewById(R.id.btn_Delete);
            zekr_bg = view.findViewById(R.id.Zekr_bg);
        }

        void bind(Zekr zekr){

            ZekrforAdapter.setText(zekr.getZekr());
            tedad_gozarande.setText(String.valueOf(zekr.getLastCount()));


            if (zekr.getFullCount() == -1 ){
                tedad_dore.setText("-");
            }else {
                tedad_dore.setText(String.valueOf(zekr.getFullCount()));
            }


            zekr_bg.setOnClickListener(this);
            btn_Delete.setOnClickListener(this);

            if (displaydensity <= 480 && !isTablet){
                tedad_dore.setVisibility(View.INVISIBLE);
                tedad_dore2.setVisibility(View.INVISIBLE);
            }




        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Zekr_bg :
                    selectClickListener.ZekrClickListener(zekrList.get(getAdapterPosition()).getID());
                    break;

                case R.id.btn_Delete:
                    selectClickListener.DeleteClickListener(zekrList.get(getAdapterPosition()).getID());
                    break;

                default:
                    break;
            }
        }
    }
}
