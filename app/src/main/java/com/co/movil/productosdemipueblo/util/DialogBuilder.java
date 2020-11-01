package com.co.movil.productosdemipueblo.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.co.movil.productosdemipueblo.R;

public class DialogBuilder {

    private static final DialogBuilder INSTANCIA = new DialogBuilder();

    public static final DialogBuilder obtener() {
        return INSTANCIA;
    }

    public Dialog createNewAlertDialog(Context clase, int titulo) {
        AlertDialog dlg = new AlertDialog.Builder(clase)
                .setTitle(titulo)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                })
                .create();
        return dlg;
    }


}
