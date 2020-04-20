package com.mvp.tasbih.View.fragments;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.DataBase.MyDataBase;
import com.mvp.tasbih.View.Dialogs.ConfirmDialog;
import com.mvp.tasbih.View.Dialogs.FinishedDialog;
import com.mvp.tasbih.Presenter.Interface.ConfirmtClickListener;
import com.mvp.tasbih.Presenter.Interface.FinishedClickListener;
import com.mvp.tasbih.Presenter.Utility.CircleProgress;
import com.mvp.tasbih.R;

public class counter_fragment extends Fragment implements ConfirmtClickListener, View.OnClickListener, FinishedClickListener {

    private View view ;

    private AppCompatButton btn_counter ;
    private AppCompatImageView btn_play , btn_repeat ;

    private AppCompatTextView tv_max  , tv_zekr , tv_translate ;


    private int counter , max  ,ID ;



    private CircleProgress circleProgress ;



    private MyDataBase dataBase ;

    private MediaPlayer audioplayer ;

    private Zekr zekr ;

    private FinishedDialog finishedDialog ;


    counter_fragment(Zekr zekr) {
        this.zekr = zekr;
        ID = zekr.getID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_counter_page,container,false);

        dataBase  = Room.databaseBuilder(getContext() , MyDataBase.class,"AzkarDB").allowMainThreadQueries().build();

        FindViews();
        SetListeners();
        initFirst();

        return view ;
    }

    private void FindViews(){
        circleProgress = view.findViewById(R.id.circleProgress);
        tv_max = view.findViewById(R.id.tv_max);
        tv_translate = view.findViewById(R.id.TV_Translate);
        tv_zekr = view.findViewById(R.id.TV_Zekr);
        btn_counter = view.findViewById(R.id.btn_counter);
        btn_play = view.findViewById(R.id.btn_play);
        btn_repeat = view.findViewById(R.id.btn_repeat);


    }

    private void SetListeners(){
        btn_counter.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_repeat.setOnClickListener(this);
        view.findViewById(R.id.btn_Sefr).setOnClickListener(this);
        view.findViewById(R.id.btn_minus).setOnClickListener(this);
    }


    private void initFirst(){

        counter = zekr.getLastCount() ;
        max = zekr.getFullCount() ;
        btn_counter.setText(String.valueOf(counter));
        circleProgress.setMax(max);
        if (max==-1){
            circleProgress.setProgress(0);
        }else {
            circleProgress.setProgress(counter);
            tv_max.setText(String.valueOf(max));
        }
        tv_zekr.setText(zekr.getZekr());

        if (!zekr.getTranslate().isEmpty()){
            tv_translate.setText(zekr.getTranslate());
        }else {
            tv_translate.setVisibility(View.GONE);
        }

        initAudioPlayer();

    }

    private void initAudioPlayer(){

        if (zekr.getSound()!=0){
            audioplayer = MediaPlayer.create(getContext(),zekr.getSound()) ;
            audioplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (!audioplayer.isLooping()){
                        btn_play.setImageResource(R.drawable.ic_play);
                    }
                }
            });
            audioplayer.setLooping(false);

        }else {
            btn_play.setVisibility(View.GONE);
            btn_repeat.setVisibility(View.GONE);
        }
    }


    private void EtmamZekr(){
        Handler handler = new Handler();

        if (ID == 9 || ID == 8 ){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    circleProgress.setColor(Color.WHITE);
                    btn_counter.setTextColor(Color.WHITE);
                    ID = ID + 1 ;
                    zekr = dataBase.azkarDAO().getZekr(ID);
                    initFirst();
                }
            }, 700);



        }else {


            finishedDialog = new FinishedDialog(getActivity() , this);


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finishedDialog.ShowFinishedDialog();
                }
            }, 700);


        }

    }

    private void reset(){
        if (zekr.getSound()!=0){
            if (audioplayer.isPlaying()) {
                audioplayer.pause();
            }
            audioplayer.seekTo(0);
            btn_play.setImageResource(R.drawable.ic_play);
        }

        if (ID == 10 || ID == 9 || ID == 8 ){
            ID = 8 ;
            zekr = dataBase.azkarDAO().getZekr(ID);
            initFirst();
        } else {
            counter = 0;
            circleProgress.setProgress(counter);
            btn_counter.setText(String.valueOf(counter));
        }



    }





    @Override
    public void onStop() {
        super.onStop();
        if (zekr.getSound()!=0){
            if (audioplayer.isPlaying()) {
                audioplayer.pause();
                btn_play.setImageResource(R.drawable.ic_play);
            }
            audioplayer.seekTo(0);
        }

        if (!(ID==8 || ID==9 || ID==10)){
            zekr.setLastCount(counter);
            dataBase.azkarDAO().Update(zekr);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_counter :
            {
                if (max==-1){
                    counter = counter + 1;
                    btn_counter.setText(String.valueOf(counter));
                }else {
                    if (counter < max) {
                        counter = counter + 1;
                        btn_counter.setText(String.valueOf(counter));
                        circleProgress.setProgress(counter);
                        if (counter == max) {
                            btn_counter.setTextColor(getResources().getColor(R.color.GreenFinished));
                            circleProgress.setColor(getResources().getColor(R.color.GreenFinished));
                            EtmamZekr();
                            if (zekr.getSound()!=0){
                                if (audioplayer.isPlaying()) {
                                    audioplayer.pause();
                                    btn_play.setImageResource(R.drawable.ic_play);
                                }
                                audioplayer.seekTo(0);
                            }
                        }
                    }
                }
                break;
            }

            case R.id.btn_play :
            {
                if (audioplayer.isPlaying()){
                    btn_play.setImageResource(R.drawable.ic_play);
                    audioplayer.pause();
                }else {
                    btn_play.setImageResource(R.drawable.ic_pause);
                    audioplayer.start();
                }
                break;

            }

            case R.id.btn_repeat :
            {
                if (audioplayer.isLooping()){
                    btn_repeat.setImageResource(R.drawable.ic_repeat_player);
                    audioplayer.setLooping(false);
                }else {
                    btn_repeat.setImageResource(R.drawable.ic_repeat_player_on);
                    audioplayer.setLooping(true);
                }
                break;

            }

            case R.id.btn_Sefr :
            {
                ConfirmDialog confirmDialog = new ConfirmDialog(getActivity(),this);
                confirmDialog.ShowConfirmDialog();
                break;
            }

            case R.id.btn_minus :
            {
                if (counter>0){
                    if (max == -1){
                        counter = counter - 1;
                        btn_counter.setText(String.valueOf(counter));
                    } else if (max>counter){
                        counter = counter - 1;
                        btn_counter.setText(String.valueOf(counter));
                        circleProgress.setProgress(counter);

                    }
                }

            }



        }
    }

    @Override
    public void Confirmed() {
        reset();
    }

    @Override
    public void Reset() {
        circleProgress.setColor(Color.WHITE);
        btn_counter.setTextColor(Color.WHITE);
        reset();

    }

    @Override
    public void Return() {
        getActivity().onBackPressed();
    }


}
