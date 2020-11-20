package com.co.movil.productosdemipueblo.cliente;

import com.co.movil.productosdemipueblo.clases.Negocio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IClienteNegocio {

    @GET(ParametrosConexion.LISTA_NEGOCIOS)
    Call<List<Negocio>> getNegocios();
}
