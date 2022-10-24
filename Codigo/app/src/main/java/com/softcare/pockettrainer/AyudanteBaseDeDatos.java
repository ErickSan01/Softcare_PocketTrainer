package com.softcare.pockettrainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Material;
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenes;
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.MaterialesPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;
import com.softcare.pockettrainer.nivel.ExperienciaActual;
import com.softcare.pockettrainer.utilerias.DateToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "pocket_trainer_db",
            NOMBRE_TABLA_EJERCICIO = "Ejercicio",
            NOMBRE_TABLA_MATERIAL = "Material",
            NOMBRE_TABLA_EJERCICIO_IMAGENES = "EjercicioImagenes",
            NOMBRE_TABLA_MATERIAL_IMAGENES = "MaterialImagenes",
            NOMBRE_TABLA_RUTINA = "Rutina",
            NOMBRE_TABLA_USUARIO = "Usuario",
            NOMBRE_TABLA_HORARIO = "Horario",
            NOMBRE_TABLA_RUTINAS_PROGRAMADAS = "RutinasProgramadas";



    public static final int VERSISON_BASE_DE_DATOS =  1;

    public Context context;

    public AyudanteBaseDeDatos(Context context){
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSISON_BASE_DE_DATOS);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_material int primary key,"+
                "nombre text, "+
                "descripcion text )", NOMBRE_TABLA_MATERIAL));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_ejercicio int primary key, "+
                "nombre text, "+
                "terminado boolean, "+
                "precio intm, "+
                "mivel text,"+
                "descripcion text, "+
                "parteCuerpo text, "+
                "meta text, "+
                "puntosEXP int, " +
                "id_rutina int, " +
                "id_material, "+
                "FOREIGN KEY (id_material) REFERENCES Material(id_material), "+
                "FOREIGN KEY (id_rutina) REFERENCES Rutina(id_rutina))", NOMBRE_TABLA_EJERCICIO));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_imagen int, "+
                "id_ejercicio int, "+
                "nombre_imagen text, "+
                "FOREIGN KEY (id_ejercicio) REFERENCES Ejercicio(id_ejercicio))", NOMBRE_TABLA_EJERCICIO_IMAGENES));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_imagen int, "+
                "id_material int, "+
                "nombre_imagen text, "+
                "FOREIGN KEY (id_material) REFERENCES Material(id_material))", NOMBRE_TABLA_MATERIAL_IMAGENES));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_rutina int, "+
                "parte_cuerpo text, "+
                "completada boolean, "+
                "puntosEXP int)", NOMBRE_TABLA_RUTINA));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                        "id_usuario int primary key, " +
                        "meta text, "+
                        "tipo_cuerpo text, "+
                        "id_rutinas_programadas int, "+
                        "id_horario int, "+
                        "exp int, "+
                        "nivel int, "+
                        "ejercicios_completados int, "+
                "FOREIGN KEY (id_rutinas_programadas) REFERENCES Rutinas_Programadas(id_rutinas_programadas), " +
                "FOREIGN KEY (id_horario) REFERENCES Horario(id_horario))", NOMBRE_TABLA_USUARIO));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_horario int, "+
                "lunes Date, "+
                "martes Date, "+
                "miercoles Date, "+
                "jueves Date, "+
                "viernes Date, "+
                "sabado Date, "+
                "domingo Date)", NOMBRE_TABLA_HORARIO));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s("+
                "id_rutina_programada int, "+
                "id_rutina_lunes int, "+
                "id_rutina_martes int, "+
                "id_rutina_miercoles int, "+
                "id_rutina_jueves int, "+
                "id_rutina_viernes int, "+
                "id_rutina_sabado int, "+
                "id_rutina_domingo int, "+
                "FOREIGN KEY (id_rutina_lunes) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_martes) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_miercoles) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_jueves) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_viernes) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_sabado) REFERENCES Rutina(id_rutina), "+
                "FOREIGN KEY (id_rutina_domingo) REFERENCES Rutina(id_rutina))"
                , NOMBRE_TABLA_RUTINAS_PROGRAMADAS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarMateriales(){
        ArrayList<String> materiales = leerMateriales();

        MaterialesPresentador materialesPresentador = new MaterialesPresentador(context);

        for(String material : materiales){
            String[] campos = material.split("-");

            int id_material;
            String nombre;
            String descripcion;
            id_material = Integer.parseInt(campos[0]);
            nombre = campos[1];
            descripcion = campos[2];

            Material nuevoMaterial = new Material(id_material, nombre, descripcion);
            materialesPresentador.nuevoMaterial(nuevoMaterial);
        }
    }

    public void agregarEjercicios(){
        ArrayList<String> ejerciciosAmbos = leerEjerciciosAmbos();
        ArrayList<String> ejerciciosAumentar = leerEjerciciosAumento();
        ArrayList<String> ejerciciosBajar = leerEjerciciosBajar();

        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(context);

        for (String ejercicio : ejerciciosAmbos){
            String[] campos = ejercicio.split("-");

            int idEjercicio;
            String nombre;
            boolean terminado;
            int precio;
            String nivel;
            String descripcion;
            String parteCuerpo;
            String meta;
            int puntosEXP;
            int idRutina;
            int idMaterial;

            idEjercicio = Integer.parseInt(campos[0]);
            nombre = campos[1];
            terminado = Boolean.parseBoolean(campos[2]);
            precio = Integer.parseInt(campos[3]);
            nivel = campos[4];
            descripcion = campos[5];
            parteCuerpo = campos[6];
            meta = campos[7];
            puntosEXP = Integer.parseInt(campos[8]);
            idRutina = Integer.parseInt(campos[9]);
            idMaterial = Integer.parseInt(campos[10]);

            Ejercicio ejercicioE = null;

            if(idMaterial == 0){
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, 0);
            } else {
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, idMaterial);
            }

            ejercicioPresentador.nuevoEjercicio(ejercicioE);

        }

        for (String ejercicio : ejerciciosAumentar){
            String[] campos = ejercicio.split("-");

            int idEjercicio;
            String nombre;
            boolean terminado;
            int precio;
            String nivel;
            String descripcion;
            String parteCuerpo;
            String meta;
            int puntosEXP;
            int idRutina;
            int idMaterial;

            idEjercicio = Integer.parseInt(campos[0]);
            nombre = campos[1];
            terminado = Boolean.parseBoolean(campos[2]);
            precio = Integer.parseInt(campos[3]);
            nivel = campos[4];
            descripcion = campos[5];
            parteCuerpo = campos[6];
            meta = campos[7];
            puntosEXP = Integer.parseInt(campos[8]);
            idRutina = Integer.parseInt(campos[9]);
            idMaterial = Integer.parseInt(campos[10]);

            Ejercicio ejercicioE = null;

            if(idMaterial == 0){
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, 0);
            } else {
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, idMaterial);
            }

            ejercicioPresentador.nuevoEjercicio(ejercicioE);
        }

        for (String ejercicio : ejerciciosBajar){
            String[] campos = ejercicio.split("-");

            int idEjercicio;
            String nombre;
            boolean terminado;
            int precio;
            String nivel;
            String descripcion;
            String parteCuerpo;
            String meta;
            int puntosEXP;
            int idRutina;
            int idMaterial;

            idEjercicio = Integer.parseInt(campos[0]);
            nombre = campos[1];
            terminado = Boolean.parseBoolean(campos[2]);
            precio = Integer.parseInt(campos[3]);
            nivel = campos[4];
            descripcion = campos[5];
            parteCuerpo = campos[6];
            meta = campos[7];
            puntosEXP = Integer.parseInt(campos[8]);
            idRutina = Integer.parseInt(campos[9]);
            idMaterial = Integer.parseInt(campos[10]);

            Ejercicio ejercicioE = null;

            if(idMaterial == 0){
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, 0);
            } else {
                ejercicioE = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosEXP, idRutina, idMaterial);
            }

            ejercicioPresentador.nuevoEjercicio(ejercicioE);
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

    public void agregarHorario(){
        SharedPreferences horario = context.getSharedPreferences("tiempo", Context.MODE_PRIVATE);

        String horarioLunes = horario.getString("lunes", "");
        String horarioMartes = horario.getString("martes", "");
        String horarioMiercoles = horario.getString("miercoles", "");
        String horarioJueves = horario.getString("jueves", "");
        String horarioViernes = horario.getString("viernes", "");
        String horarioSabado = horario.getString("sabado", "");
        String horarioDomingo = horario.getString("domingo", "");

        Date fechaLunes = DateToString.cadenaAFecha(horarioLunes);
        Date fechaMartes = DateToString.cadenaAFecha(horarioMartes);
        Date fechaMiercoles = DateToString.cadenaAFecha(horarioMiercoles);
        Date fechaJueves = DateToString.cadenaAFecha(horarioJueves);
        Date fechaViernes = DateToString.cadenaAFecha(horarioViernes);
        Date fechaSabado = DateToString.cadenaAFecha(horarioSabado);
        Date fechaDomingo = DateToString.cadenaAFecha(horarioDomingo);

        Horario horarioUsuario = new Horario(1, fechaLunes, fechaMartes, fechaMiercoles, fechaJueves, fechaViernes, fechaSabado, fechaDomingo);

        HorarioPresentador horarioPresentador = new HorarioPresentador(context);

        horarioPresentador.nuevoHorario(horarioUsuario);
    }

    public void agregarEjercicioImagenes(){
        ArrayList<String> imagenesEjercicio = leerImagenesEjercicio();

        EjercicioImagenesPresentador presentador = new EjercicioImagenesPresentador(context);

        for (String imagenes : imagenesEjercicio){
            String[] imagen = imagenes.split("-");
            int idImagen = Integer.parseInt(imagen[0]);
            int idEjercicio= Integer.parseInt(imagen[1]);
            String nombreImagen = imagen[2];

            EjercicioImagenes imagenE = new EjercicioImagenes(idImagen, idEjercicio, nombreImagen);
            presentador.nuevaImagen(imagenE);
        }
    }

    public void agregarMaterialImagenes(){
        ArrayList<String> imagenesMaterial = leerImagenesMaterial();

        MaterialImagenesPresentador presentador = new MaterialImagenesPresentador(context);

        for (String imagenes : imagenesMaterial) {
            String[] imagen = imagenes.split("-");
            int idImagen = Integer.parseInt(imagen[0]);
            int idMaterial = Integer.parseInt(imagen[1]);
            String nombreImagen = imagen[2];

            MaterialImagenes imagenM = new MaterialImagenes(idImagen, idMaterial, nombreImagen);
            presentador.nuevaImagen(imagenM);
        }
    }

    public ArrayList<String> leerImagenesMaterial(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> imagenesMaterial = new ArrayList<String>();

        try{
            String archivo = "materiales/imagenes.txt";
            InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
            reader = new BufferedReader(is);

            String line = "";

            while((line = reader.readLine()) != null){
                imagenesMaterial.add(line);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        return imagenesMaterial;
    }
    public ArrayList<String> leerImagenesEjercicio(){
        BufferedReader reader = null;
        String result = "";
        ArrayList<String> imagenesMaterial = new ArrayList<String>();

        try{
            String archivo = "materiales/imagenes.txt";
            InputStreamReader is = new InputStreamReader(context.getAssets().open(archivo));
            reader = new BufferedReader(is);

            String line = "";

            while((line = reader.readLine()) != null){
                imagenesMaterial.add(line);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        return imagenesMaterial;
    }

    public void agregarRutinas(){
        ArrayList<String> partesCuerpo = new ArrayList<String>();
        partesCuerpo.add("Abdomen");
        partesCuerpo.add("Brazo");
        partesCuerpo.add("Hombros");
        partesCuerpo.add("Pecho");
        partesCuerpo.add("Pierna");

        int idRutinaI = 0;
        int diasSeleccionados = contarDias();
        double exp = ExperienciaActual.getLevel();
        double expRutina = exp / diasSeleccionados;

        RutinaPresentador presentador = new RutinaPresentador(context);

        for (String parteCuerpo : partesCuerpo){
            Rutina rutina = new Rutina(idRutinaI, parteCuerpo, false, (int) Math.floor(expRutina));

            presentador.nuevaRutina(rutina);
        }
    }

    public int contarDias(){
        SharedPreferences diasLibres = context.getSharedPreferences("dias", Context.MODE_PRIVATE);
        int cantidadDias = 0;

        cantidadDias += boolToInt(diasLibres.getBoolean("lunes", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("martes", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("miercoles", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("jueves", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("viernes", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("sabado", false));
        cantidadDias += boolToInt(diasLibres.getBoolean("domingo", false));

        System.out.println(cantidadDias);

        return cantidadDias;
    }

    public int boolToInt(Boolean value){
        if(value){
            return 1;
        }
        return 0;
    }

    public void agregarUsuario() {
        int idUsuario = 1;
        String meta = getMeta();
        String tipoCuerpo = getTipoCuerpo();
        int idRutinasProgramadas = 1;
        int idHorario = 1;
        int exp = 0;
        int nivel = 0;
        int ejerciciosCompletados = 0;

        Usuario user = new Usuario(idUsuario, meta, tipoCuerpo, idRutinasProgramadas, idHorario, exp, nivel, ejerciciosCompletados);

        UsuarioPresentador presentador = new UsuarioPresentador(context);
        presentador.nuevoUsuario(user);
    }

    public String getMeta(){
        SharedPreferences meta = context.getSharedPreferences("meta", Context.MODE_PRIVATE);

        boolean musculos = meta.getBoolean("musculo", false);
        boolean ambos = meta.getBoolean("ambos", false);
        boolean peso = meta.getBoolean("peso", false);

        String metaS = "";
        if(musculos){
            metaS = "musculos";
        }
        if(ambos){
            metaS = "ambos";
        }
        if(peso){
            metaS = "peso";
        }

        return metaS;
    }

    public String getTipoCuerpo(){
        SharedPreferences tipoCuerpo = context.getSharedPreferences("cuerpo", Context.MODE_PRIVATE);

        boolean fornido = tipoCuerpo.getBoolean("fornido", false);
        boolean robusto = tipoCuerpo.getBoolean("robusto", false);
        boolean delgado = tipoCuerpo.getBoolean("delgado", false);

        String tipoCuerpoS = "";

        if(fornido){
            tipoCuerpoS = "fornido";
        }
        if(robusto){
            tipoCuerpoS = "robusto";
        }
        if(delgado){
            tipoCuerpoS = "delgado";
        }

        return tipoCuerpoS;
    }
}
