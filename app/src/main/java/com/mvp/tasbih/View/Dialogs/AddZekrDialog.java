package com.mvp.tasbih.View.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.Interface.AddZekrClickListener;
import com.mvp.tasbih.R;

public class AddZekrDialog implements View.OnClickListener {

    private Activity activity ;
    private Dialog AddZekrDialog ;
    private AppCompatEditText   ET_inputFullCount ;
    private TextInputEditText ET_inputZekr , ET_inputTranslate;
    private AppCompatCheckBox CB_UltimateAddZekr ;
    private int displaydensity ;
    private AddZekrClickListener addZekrClickListener ;

    public AddZekrDialog(Activity activity , int displaydensity , AddZekrClickListener addZekrClickListener) {
        this.activity = activity;
        this.displaydensity = displaydensity ;
        this.addZekrClickListener = addZekrClickListener ;
    }

    public void ShowDialog (){
        final AppCompatTextView TV_inputFullCountAddZekr ;
        AppCompatButton btn_ConfirmAddZekr ;

        AddZekrDialog = new Dialog(activity) ;
        AddZekrDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddZekrDialog.setContentView(R.layout.set_new_zekr_dialog);
        AddZekrDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ET_inputZekr = AddZekrDialog.findViewById(R.id.ET_inputZekr);
        ET_inputTranslate = AddZekrDialog.findViewById(R.id.ET_inputTranslate);
        ET_inputFullCount = AddZekrDialog.findViewById(R.id.ET_inputFullCount);
        TV_inputFullCountAddZekr = AddZekrDialog.findViewById(R.id.TV_inputFullCountAddZekr);
        CB_UltimateAddZekr = AddZekrDialog.findViewById(R.id.CB_UltimateAddZekr);
        btn_ConfirmAddZekr = AddZekrDialog.findViewById(R.id.btn_ConfirmAddZekr);

        SetLengh();



        CB_UltimateAddZekr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    ET_inputFullCount.setEnabled(false);
                    ET_inputFullCount.setTextColor(Color.GRAY);
                    TV_inputFullCountAddZekr.setTextColor(Color.GRAY);
                }else {
                    ET_inputFullCount.setEnabled(true);
                    ET_inputFullCount.setTextColor(activity.getResources().getColor(R.color.Grey));
                    TV_inputFullCountAddZekr.setTextColor(activity.getResources().getColor(R.color.Grey));
                }
            }
        });

        btn_ConfirmAddZekr.setOnClickListener(this);

        changeDialogSize();
        AddZekrDialog.show();
    }

    private void SetLengh(){

        int maxlengh ;
        if (displaydensity<160){
            maxlengh = 85 ;
        }else if (displaydensity<240){
            maxlengh = 105 ;
        }else if (displaydensity<320){
            maxlengh = 125 ;
        }else if (displaydensity<420){
            maxlengh = 145 ;
        } else {
            maxlengh = 165 ;
        }
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxlengh);
        ET_inputZekr.setFilters(filters);
        ET_inputTranslate.setFilters(filters);


    }

    private void changeDialogSize() {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        if (AddZekrDialog.getWindow() != null) {
            AddZekrDialog.getWindow().setLayout((int) (0.88 * size.x), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void AddnewZekr(){
        String inputZekr , inputTranslate , et_inputFullCount ;

        inputZekr = ET_inputZekr.getText().toString().trim() ;
        inputTranslate = ET_inputTranslate.getText().toString().trim() ;
        et_inputFullCount = ET_inputFullCount.getText().toString().trim() ;

        if (inputZekr.isEmpty()){
            Toast.makeText(activity, "ذکر را وارد کنید", Toast.LENGTH_SHORT).show();
        }else if(!CB_UltimateAddZekr.isChecked() && et_inputFullCount.isEmpty()){
            Toast.makeText(activity, "مقداری برای تعداد دوره وارد کنید", Toast.LENGTH_SHORT).show();
        } else {

            int inputFullCount ;

            if (CB_UltimateAddZekr.isChecked()){
                inputFullCount = -1 ;
            }else {
                inputFullCount = Integer.valueOf(et_inputFullCount);
            }

            if (inputFullCount==0){
                Toast.makeText(activity, "مقداری غیر صفر برای تعداد دوره وارد کنید", Toast.LENGTH_SHORT).show();
            }else {

                Zekr new_azkar = new Zekr();
                new_azkar.setZekr(inputZekr);
                new_azkar.setTranslate(inputTranslate);
                new_azkar.setSound(0);
                new_azkar.setLastCount(0);
                new_azkar.setFullCount(inputFullCount);
                new_azkar.setSuggested(-1);

                addZekrClickListener.AddZekr(new_azkar);
                AddZekrDialog.dismiss();
                Toast.makeText(activity, "ذکر با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();

            }
        }




    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_ConfirmAddZekr){
            AddnewZekr();
        }
    }
}
