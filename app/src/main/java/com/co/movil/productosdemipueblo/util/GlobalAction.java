package com.co.movil.productosdemipueblo.util;

public class GlobalAction {

    public static void reiniciarValores(){
        GlobalInfo.NEGOCIOSELECCIONADO = false;
        GlobalInfo.PRODUCTOS.clear();
        GlobalInfo.NEGOCIOS.clear();
    }
}
