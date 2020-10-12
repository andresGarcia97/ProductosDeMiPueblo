package com.co.movil.productosdemipueblo.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.co.movil.productosdemipueblo.entities.ClienteEntity;

import java.util.List;

@Dao
public interface IClienteDAO {

    @Insert
    void insert(ClienteEntity clienteEntity);

    @Update
    void update(ClienteEntity clienteEntity);

    @Query("SELECT * FROM clientes")
    List<ClienteEntity> listar();

}
