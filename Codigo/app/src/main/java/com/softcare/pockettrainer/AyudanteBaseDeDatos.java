package com.softcare.pockettrainer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.softcare.pockettrainer.adminejercicios.EjerciciosControlador;
import com.softcare.pockettrainer.adminmateriales.Material;
import com.softcare.pockettrainer.adminmateriales.MaterialesControlador;
import com.softcare.pockettrainer.rutinas.Ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AyudanteBaseDeDatos extends SQLiteOpenHelper{
    private static final String NOMBRE_BASE_DE_DATOS = "pocket_trainer_db",
            NOMBRE_TABLA_EJERCICIOS = "Ejercicios",
            NOMBRE_TABLA_MATERIALES = "Materiales";
    public static final int VERSISON_BASE_DE_DATOS =  1;

    public Context context;

    public AyudanteBaseDeDatos(Context context){
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSISON_BASE_DE_DATOS);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Database");

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id_material int primary key, nombre text, descripcion text, imagen1 text, imagen2 text)", NOMBRE_TABLA_MATERIALES));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id_ejercicio int primary key, nombre text, categoria text, descripcion text, imagen1 text, imagen2 text, imagen3 , parteCuerpo text, id_material int," +
                "FOREIGN KEY (id_material) REFERENCES Materiales(id_material))", NOMBRE_TABLA_EJERCICIOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarMateriales(){
        ArrayList<String> materiales = leerMateriales();

        MaterialesControlador materialesControlador = new MaterialesControlador(context);

        for(String material : materiales){
            String[] campos = material.split("-");

            int id_material = Integer.parseInt(campos[0]);
            String nombre;
            String descripcion;
            String imagen1;
            String imagen2;

            switch(campos.length){
                case(4):
                    id_material = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    descripcion = campos[2];
                    imagen1 = campos[3];

                    Material nuevoMaterial = new Material(id_material, nombre, descripcion, imagen1);

                    materialesControlador.nuevoMaterial(nuevoMaterial);

                    break;
                case(5):
                    id_material = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    descripcion = campos[2];
                    imagen1 = campos[3];
                    imagen2 = campos[4];

                    Material nuevoMaterial2 = new Material(id_material, nombre, descripcion, imagen1, imagen2);

                    materialesControlador.nuevoMaterial(nuevoMaterial2);

                    break;
            }
        }
    }

    public void agregarEjercicios(){
        //SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<String> ejerciciosAmbos = leerEjerciciosAmbos();
        ArrayList<String> ejerciciosAumentar = leerEjerciciosAumento();
        ArrayList<String> ejerciciosBajar = leerEjerciciosBajar();

        EjerciciosControlador ejerciciosControlador = new EjerciciosControlador(context);

        for (String ejercicio : ejerciciosAmbos){
            String[] campos = ejercicio.split(",");
            int id_ejercicio;
            String nombre;
            String categoria;
            String imagen1, imagen2, imagen3;
            String parteCuerpo;
            String descripcion;
            int id_material;

            switch(campos.length){
                case(7):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    parteCuerpo = campos[3];
                    categoria = campos[4];
                    descripcion = campos[5];
                    id_material = Integer.parseInt(campos[6]);

                    Ejercicio ejercicio5 = null;

                    if(id_material == 0){
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion);
                    } else {
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio5);

                    break;
                case(8):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    parteCuerpo = campos[4];
                    categoria = campos[5];
                    descripcion = campos[6];
                    id_material = Integer.parseInt(campos[7]);

                    Ejercicio ejercicio6 = null;

                    if(id_material == 0){
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion);
                    } else {
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio6);

                    break;
                case(9):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    imagen3 = campos[4];
                    parteCuerpo = campos[5];
                    categoria = campos[6];
                    descripcion = campos[7];
                    id_material = Integer.parseInt(campos[8]);

                    Ejercicio ejercicio7 = null;

                    if(id_material == 0){
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion);
                    } else {
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion, id_material);
                    }
                    ejerciciosControlador.nuevoEjercicio(ejercicio7);

                    break;
            }

            //Ejercicio ejercicio = new Ejercicio();

        }

        for (String ejercicio : ejerciciosAumentar){
            String[] campos = ejercicio.split(",");
            int id_ejercicio;
            String nombre;
            String categoria;
            String imagen1, imagen2, imagen3;
            String parteCuerpo;
            String descripcion;
            int id_material;

            switch(campos.length){
                case(7):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    parteCuerpo = campos[3];
                    categoria = campos[4];
                    descripcion = campos[5];
                    id_material = Integer.parseInt(campos[6]);

                    Ejercicio ejercicio5 = null;

                    if(id_material == 0){
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion);
                    } else {
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio5);

                    break;
                case(8):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    parteCuerpo = campos[4];
                    categoria = campos[5];
                    descripcion = campos[6];
                    id_material = Integer.parseInt(campos[7]);

                    Ejercicio ejercicio6 = null;

                    if(id_material == 0){
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion);
                    } else {
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio6);

                    break;
                case(9):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    imagen3 = campos[4];
                    parteCuerpo = campos[5];
                    categoria = campos[6];
                    descripcion = campos[7];
                    id_material = Integer.parseInt(campos[8]);

                    Ejercicio ejercicio7 = null;

                    if(id_material == 0){
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion);
                    } else {
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion, id_material);
                    }
                    ejerciciosControlador.nuevoEjercicio(ejercicio7);

                    break;
            }

            //Ejercicio ejercicio = new Ejercicio();

        }

        for (String ejercicio : ejerciciosBajar){
            String[] campos = ejercicio.split(",");
            int id_ejercicio;
            String nombre;
            String categoria;
            String imagen1, imagen2, imagen3;
            String parteCuerpo;
            String descripcion;
            int id_material;

            switch(campos.length){
                case(7):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    parteCuerpo = campos[3];
                    categoria = campos[4];
                    descripcion = campos[5];
                    id_material = Integer.parseInt(campos[6]);

                    Ejercicio ejercicio5 = null;

                    if(id_material == 0){
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion);
                    } else {
                        ejercicio5 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio5);

                    break;
                case(8):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    parteCuerpo = campos[4];
                    categoria = campos[5];
                    descripcion = campos[6];
                    id_material = Integer.parseInt(campos[7]);

                    Ejercicio ejercicio6 = null;

                    if(id_material == 0){
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion);
                    } else {
                        ejercicio6 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, parteCuerpo, descripcion, id_material);
                    }

                    ejerciciosControlador.nuevoEjercicio(ejercicio6);

                    break;
                case(9):
                    id_ejercicio = Integer.parseInt(campos[0]);
                    nombre = campos[1];
                    imagen1 = campos[2];
                    imagen2 = campos[3];
                    imagen3 = campos[4];
                    parteCuerpo = campos[5];
                    categoria = campos[6];
                    descripcion = campos[7];
                    id_material = Integer.parseInt(campos[8]);

                    Ejercicio ejercicio7 = null;

                    if(id_material == 0){
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion);
                    } else {
                        ejercicio7 = new Ejercicio(id_ejercicio, nombre, categoria, imagen1, imagen2, imagen3, parteCuerpo, descripcion, id_material);
                    }
                    ejerciciosControlador.nuevoEjercicio(ejercicio7);

                    break;
            }

            //Ejercicio ejercicio = new Ejercicio();

        }


    }



    public ArrayList<String> leerEjerciciosAmbos(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> ejercicios = new ArrayList<String>();

        for (int ej = 0; ej < 5; ej++){
            String nombreParte = "";
            switch (ej){
                case(0):
                    nombreParte = "Pecho";
                    break;
                case(1):
                    nombreParte = "Abdomen";
                    break;
                case(2):
                    nombreParte = "Brazo";
                    break;
                case(3):
                    nombreParte = "Hombros";
                    break;
                case(4):
                    nombreParte = "Pierna";
                    break;
            }
            try {
                String archivo = String.format("ejercicios/ambos/ejercicios%s.txt", nombreParte);
                InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
                reader = new BufferedReader(is);
                String line = "";

                while((line = reader.readLine()) != null){
                    ejercicios.add(line);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        return ejercicios;
    }

    public ArrayList<String> leerEjerciciosAumento(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> ejercicios = new ArrayList<String>();

        for (int ej = 0; ej < 3; ej++){
            String nombreParte = "";
            switch (ej){
                case(0):
                    nombreParte = "Brazo";
                    break;
                case(1):
                    nombreParte = "Hombro";
                    break;
                case(2):
                    nombreParte = "Pierna";
                    break;
            }
            try {
                String archivo = String.format("ejercicios/aumentarMasa/ejercicios%s.txt", nombreParte);
                InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
                reader = new BufferedReader(is);
                String line = "";

                while((line = reader.readLine()) != null){
                    ejercicios.add(line);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        return ejercicios;
    }

    public ArrayList<String> leerEjerciciosBajar(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> ejercicios = new ArrayList<String>();

        for (int ej = 0; ej < 2; ej++){
            String nombreParte = "";
            switch (ej){
                case(0):
                    nombreParte = "Pecho";
                    break;
                case(1):
                    nombreParte = "Pie";
                    break;
            }
            try {
                String archivo = String.format("ejercicios/bajarPeso/ejercicios%s.txt", nombreParte);
                InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
                reader = new BufferedReader(is);
                String line = "";

                while((line = reader.readLine()) != null){
                    ejercicios.add(line);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        return ejercicios;
    }

    public ArrayList<String> leerMateriales(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> materiales = new ArrayList<String>();

        try{
            String archivo = "materiales/materiales.txt";
            InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
            reader = new BufferedReader(is);

            String line = "";

            while((line = reader.readLine()) != null){
                materiales.add(line);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        return materiales;
    }

}
