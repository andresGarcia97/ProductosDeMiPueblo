package com.co.movil.productosdemipueblo.util;

import com.co.movil.productosdemipueblo.clases.Negocio;

public class GlobalAction {

    public static void reiniciarValores(){
        GlobalInfo.NEGOCIOSELECCIONADO = false;
        GlobalInfo.PRODUCTOS.clear();
        GlobalInfo.NEGOCIOS.clear();
        GlobalInfo.NEGOCIO = new Negocio();
        GlobalInfo.PEDIDOENVIADO = false;
    }
}
