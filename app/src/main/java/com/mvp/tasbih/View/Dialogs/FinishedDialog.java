package com.mvp.tasbih.View.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mvp.tasbih.Presenter.Interface.FinishedClickListener;
import com.mvp.tasbih.R;

public class FinishedDialog implements View.OnClickListener {

    private Activity activity ;
    private Dialog dialogFinished;
    private FinishedClickListener finishedClickListener  ;

    public FinishedDialog(Activity activity , FinishedClickListener finishedClickListener) {
        this.activity = activity ;
        this.finishedClickListener = finishedClickListener ;
    }

    public void ShowFinishedDialog(){
        dialogFinished = new Dialog(activity);
        dialogFinished.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFinished.setContentView(R.layout.finished_dialog);
        dialogFinished.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogFinished.findViewById(R.id.btn_return_dialog).setOnClickListener(this);
        dialogFinished.findViewById(R.id.btn_again_dialog).setOnClickListener(this);


        changeDialogSize();
        dialogFinished.show();
    }

    private void changeDialogSize() {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        if (dialogFinished.getWindow() != null) {
            dialogFinished.getWindow().setLayout((int) (0.70 * size.x), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_return_dialog ){
            finishedClickListener.Return();
        } else if (v.getId() == R.id.btn_again_dialog){
            finishedClickListener.Reset();
        }

        dialogFinished.dismiss();

    }
}
