package com.mvp.tasbih.View.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.Interface.ConfirmtClickListener;
import com.mvp.tasbih.Presenter.Interface.SelectClickListener2;
import com.mvp.tasbih.R;

public class ZekrDialog implements View.OnClickListener, ConfirmtClickListener {

    private Dialog dialogSelect, dialogSetCounter;
    private Activity activity;
    private Zekr zekr;

    private AppCompatTextView TV_FullCount;

    private AppCompatCheckBox CB_Ultimate;
    private AppCompatEditText ET_LastCount, ET_FullCount;

    private SelectClickListener2 selectClickListener2 ;


    public ZekrDialog(Zekr zekr, Activity activity , SelectClickListener2 selectClickListener2) {
        this.activity = activity;
        this.zekr = zekr;
        this.selectClickListener2 = selectClickListener2 ;
    }

    public void ShowSelectDialog(){

        dialogSelect = new Dialog(activity);
        dialogSelect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSelect.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if (zekr.getLastCount() == zekr.getFullCount()) {
            dialogSelect.setContentView(R.layout.selectwithoutcountinuslayout);
        } else {
            dialogSelect.setContentView(R.layout.selectcountinuslayout);
            dialogSelect.findViewById(R.id.layout_continue).setOnClickListener(this);
        }


        dialogSelect.findViewById(R.id.layout_reset).setOnClickListener(this);
        dialogSelect.findViewById(R.id.layout_settings).setOnClickListener(this);

        changeDialogSize(dialogSelect, 0.62);

        dialogSelect.show();
    }

    private void changeDialogSize(Dialog dialog, double per) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout((int) (per * size.x), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void ShowSetCounterDialog() {
        AppCompatTextView TV_inputSuggested, TV_Suggested;

        dialogSetCounter = new Dialog(activity);
        dialogSetCounter.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetCounter.setContentView(R.layout.setcounter_page);
        changeDialogSize(dialogSetCounter, .80);
        dialogSetCounter.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        CB_Ultimate = dialogSetCounter.findViewById(R.id.CB_Ultimate);
        ET_LastCount = dialogSetCounter.findViewById(R.id.ET_LastCount);
        ET_FullCount = dialogSetCounter.findViewById(R.id.ET_FullCount);
        TV_FullCount = dialogSetCounter.findViewById(R.id.TV_FullCount);
        TV_inputSuggested = dialogSetCounter.findViewById(R.id.TV_inputSuggested);
        TV_Suggested = dialogSetCounter.findViewById(R.id.TV_Suggested);

        CB_Ultimate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    ET_FullCount.setEnabled(false);
                    ET_FullCount.setTextColor(Color.GRAY);
                    TV_FullCount.setTextColor(Color.GRAY);
                } else {
                    ET_FullCount.setEnabled(true);
                    ET_FullCount.setTextColor(activity.getResources().getColor(R.color.Grey));
                    TV_FullCount.setTextColor(activity.getResources().getColor(R.color.Grey));
                }
            }
        });

        ET_LastCount.setText(String.valueOf(zekr.getLastCount()));

        if (!(zekr.getFullCount() == -1)) {
            ET_FullCount.setText(String.valueOf(zekr.getFullCount()));
        }

        if (zekr.getSuggested() == -1) {
            TV_inputSuggested.setVisibility(View.GONE);
            TV_Suggested.setVisibility(View.GONE);
        } else {
            TV_inputSuggested.setText(String.valueOf(zekr.getSuggested()));
        }

        dialogSetCounter.findViewById(R.id.btn_Confirm).setOnClickListener(this);


        dialogSetCounter.show();
    }

    private void SetDataBase (){
        int lastcount , fullcount ;
        String et_lastcount , et_fullcount ;

        et_lastcount = ET_LastCount.getText().toString().trim();
        et_fullcount = ET_FullCount.getText().toString().trim();

        if (et_lastcount.isEmpty() ){
            Toast.makeText(activity, "مقداری برای شروع ذکر وارد کنید", Toast.LENGTH_SHORT).show();
        }else if (et_fullcount.isEmpty() && !CB_Ultimate.isChecked()){
            Toast.makeText(activity, "مقداری برای تعداد دوره وارد کنید", Toast.LENGTH_SHORT).show();
        } else {
            lastcount = Integer.valueOf(et_lastcount);
            zekr.setLastCount(lastcount);

            if (!CB_Ultimate.isChecked()){
                fullcount = Integer.valueOf(et_fullcount);
                if (fullcount<=lastcount){
                    Toast.makeText(activity, "مقدار شروع ذکر باید کمتر از مقدار دوره باشد", Toast.LENGTH_SHORT).show();
                } else {
                    zekr.setFullCount(fullcount);

                    selectClickListener2.ChangeDataBase(zekr);
                    dialogSetCounter.dismiss();
                    selectClickListener2.GotoFragment(zekr);
                }
            }else {
                zekr.setFullCount(-1);

                selectClickListener2.ChangeDataBase(zekr);
                dialogSetCounter.dismiss();
                selectClickListener2.GotoFragment(zekr);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_continue:
                dialogSelect.dismiss();
                selectClickListener2.GotoFragment(zekr);
                break;

            case R.id.layout_reset:
                dialogSelect.dismiss();
                if (zekr.getLastCount()!=zekr.getFullCount()){
                    ConfirmDialog confirmDialog = new ConfirmDialog(activity , this) ;
                    confirmDialog.ShowConfirmDialog();
                }else {
                    zekr.setLastCount(0);
                    selectClickListener2.ChangeDataBase(zekr);
                    selectClickListener2.GotoFragment(zekr);
                }
                break;


            case R.id.layout_settings:
                dialogSelect.dismiss();
                ShowSetCounterDialog();
                break;

            case R.id.btn_Confirm:
                SetDataBase();
                break;

            default:
                break;
        }
    }

    @Override
    public void Confirmed() {
        zekr.setLastCount(0);
        selectClickListener2.ChangeDataBase(zekr);
        selectClickListener2.GotoFragment(zekr);
    }
}
