package com.co.movil.productosdemipueblo.util;

import com.co.movil.productosdemipueblo.clases.Negocio;
import com.co.movil.productosdemipueblo.clases.Producto;

import java.util.ArrayList;
import java.util.List;

public class GlobalInfo {

    public static List<Producto> PRODUCTOS = new ArrayList<>();
    public static Producto PRODUCTO = new Producto();
    public static Negocio NEGOCIO = new Negocio();
    public static List<Negocio> NEGOCIOS = new ArrayList<>();
    public static boolean NEGOCIOSELECCIONADO = false;
}
