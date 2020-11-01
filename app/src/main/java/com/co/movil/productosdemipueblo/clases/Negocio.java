package com.co.movil.productosdemipueblo.clases;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Negocio {

    private int imagen;
    private String nombre;
    private String direccion;
    private String descripcion;
    private List<Producto> productosDisponibles;

}
