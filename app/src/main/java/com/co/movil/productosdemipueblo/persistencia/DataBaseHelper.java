package com.co.movil.productosdemipueblo.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.co.movil.productosdemipueblo.entities.ClienteEntity;
import com.co.movil.productosdemipueblo.persistencia.dao.IClienteDAO;

// entidades

@Database(entities = {
        ClienteEntity.class}, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "productos_de_mi_pueblo";
    private static DataBaseHelper INSTANCIA;


    public static DataBaseHelper getSimpleDB(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return INSTANCIA;
    }

    public static DataBaseHelper getDBMainThread(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCIA;
    }

    // DAOs

    public abstract IClienteDAO transaccionesCliente();
}
