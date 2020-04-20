package com.mvp.tasbih.View.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mvp.tasbih.Presenter.Interface.ConfirmtClickListener;
import com.mvp.tasbih.R;

public class ConfirmDialog implements View.OnClickListener {

    private Activity activity ;
    private Dialog dialogConfirm ;
    private ConfirmtClickListener confirmtClickListener ;

    public ConfirmDialog(Activity activity , ConfirmtClickListener confirmtClickListener) {
        this.activity = activity ;
        this.confirmtClickListener = confirmtClickListener ;
    }

    public void ShowConfirmDialog(){
        dialogConfirm = new Dialog(activity);
        dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogConfirm.setContentView(R.layout.confirm_dialog);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogConfirm.findViewById(R.id.btn_Confirm_dialog).setOnClickListener(this);
        dialogConfirm.findViewById(R.id.btn_Cancel_dialog).setOnClickListener(this);


        changeDialogSize();
        dialogConfirm.show();
    }

    private void changeDialogSize() {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        if (dialogConfirm.getWindow() != null) {
            dialogConfirm.getWindow().setLayout((int) (0.70 * size.x), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_Confirm_dialog ){
            confirmtClickListener.Confirmed();
        }

        dialogConfirm.dismiss();

    }
}
