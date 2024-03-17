package com.example.number_guess;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleConfirmDialog{
    private final AlertDialog.Builder alertDialogBuilder;

    public SimpleConfirmDialog(Context context, String title, String message) {
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
    }

    public void createAndShow(){
        alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Handle confirmation button click if needed
                dialog.dismiss();
            }
        });
        alertDialogBuilder.create().show();
    }
}
