package com.co.movil.productosdemipueblo.util;

import android.widget.EditText;

public final class UtilTexto {

    private static final UtilTexto INSTANCIA = new UtilTexto();

    private UtilTexto() {
        super();
    }

    public static final UtilTexto obtener() {
        return INSTANCIA;
    }

    public String obtenerTexto(EditText editText) {
        return editText.getText().toString();
    }
}
