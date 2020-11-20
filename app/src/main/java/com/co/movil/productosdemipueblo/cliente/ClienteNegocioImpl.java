package com.co.movil.productosdemipueblo.cliente;

import android.util.Log;

import com.co.movil.productosdemipueblo.clases.Negocio;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@NoArgsConstructor
public class ClienteNegocioImpl {

    private final OkHttpClient simpleClient = new OkHttpClient.Builder()
            .readTimeout(ParametrosConexion.CONEXION_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(ParametrosConexion.CONEXION_TIMEOUT, TimeUnit.SECONDS).build();

    protected Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(ParametrosConexion.ENDPOINT).addConverterFactory(GsonConverterFactory.create())
                .client(simpleClient).build();
    }

    public void getCantidadesOfNegocio() {
        Retrofit retrofit = getInstance();
        IClienteNegocio cliente = retrofit.create(IClienteNegocio.class);
        Call<List<Negocio>> respuesta = cliente.getNegocios();
        respuesta.enqueue(new Callback<List<Negocio>>() {
            @Override
            public void onResponse(Call<List<Negocio>> call, Response<List<Negocio>> response) {
                List<Negocio> negocios = response.body();
                for (int i = 0; i < negocios.size(); i++) {
                    for (int j = 0; j < negocios.get(i).getProductosDisponibles().size(); j++) {
                        GlobalInfo.NEGOCIOS.get(i).getProductosDisponibles().get(j).setCantidadDisponible(negocios.get(i).getProductosDisponibles().get(j).getCantidad());
                        GlobalInfo.NEGOCIOS.get(i).getProductosDisponibles().get(j).setPrecio(negocios.get(i).getProductosDisponibles().get(j).getPrecio());
                    }
                }
                GlobalInfo.SINCRONIZACION_EXITOSA = true;
            }

            @Override
            public void onFailure(Call<List<Negocio>> call, Throwable t) {
                GlobalInfo.SINCRONIZACION_EXITOSA = false;
                Log.e("Error conexion", t.getMessage());
            }
        });
    }
}
