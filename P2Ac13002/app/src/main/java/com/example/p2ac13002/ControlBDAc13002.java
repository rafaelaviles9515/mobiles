package com.example.p2ac13002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDAc13002 {
    private static final String[] camposEspecie = new String[]{"codespecie ", "nomespecie ", "peligroextincion ", "recuperados ", "muertos "};
    private static final String[] camposPais = new String[]{"codpais ", "nommpais ", "esdeAfrica "};
    private static final String[] camposAnimal = new String[]{"codespecie", "codpais", "codanimal", "edadanimal", "estaVivo"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDAc13002(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "baseparcial2.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE especie(codespecie VARCHAR(6) NOT NULL PRIMARY KEY,nomespecie VARCHAR(30),peligroextincion REAL,recuperados INTEGER,muertos INTEGER);");
                db.execSQL("CREATE TABLE pais(codpais VARCHAR(3) NOT NULL PRIMARY KEY,nommpais VARCHAR(30),esdeAfrica VARCHAR(1));");
                db.execSQL("CREATE TABLE animal(codespecie VARCHAR(6) NOT NULL ,codpais VARCHAR(3) NOT NULL ,codanimal VARCHAR(6),edadanimal FLOAT,estaVivo VARCHAR(1) ,PRIMARY KEY(codespecie,codpais,codanimal));");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }
    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    public String insertar(Especie especie) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues cont = new ContentValues();
        cont.put("codespecie ", especie.getCodespecie());
        cont.put("nomespecie ", especie.getNomespecie());
        cont.put("peligroextincion ", especie.getPeligroextincion());
        cont.put("recuperados ", especie.getRecuperados());
        cont.put("muertos ", especie.getMuertos());

        contador = db.insert("especie", null, cont);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Pais pais) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues cont = new ContentValues();
        cont.put("codpais  ", pais.getCodpais());
        cont.put("nommpais  ", pais.getNommpais());
        cont.put("esdeAfrica  ", pais.getEsdeAfrica());

        contador = db.insert("pais", null, cont);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Animal animal) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        if (verificarIntegridad(animal, 1)) {
            ContentValues pa = new ContentValues();
            pa.put("codespecie", animal.getCodespecie());
            pa.put("codpais", animal.getCodpais());
            pa.put("codanimal", animal.getCodanimal());
            pa.put("edadanimal", animal.getEdadanimal());
            pa.put("estaVivo", animal.getEstaVivo());

            contador = db.insert("animal", null, pa);
       }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            if (Integer.parseInt(animal.getEstaVivo())==2){
                String[] id = {animal.getCodespecie()};
                Cursor cursor1 = db.query("especie", null, "codespecie  = ?", id, null,
                        null, null);
                if(cursor1.moveToFirst()){

                    ContentValues cv = new ContentValues();
                    cv.put("codespecie", cursor1.getString(0));
                    cv.put("nomespecie", cursor1.getString(1));
                    cv.put("peligroextincion", cursor1.getFloat(2));
                    cv.put("recuperados", cursor1.getInt(3));
                    cv.put("muertos", cursor1.getInt(4)+1);
                    db.update("especie", cv, "codespecie = ?", id);

                }
                regInsertados = regInsertados +contador+"\n Se actualiza la tabla continente porque esta muerto";
            }
            else{
                regInsertados = regInsertados +contador+"\n No se actualiza la tabla porque esta vivo";
            }

        }
        return regInsertados;

    }
    public String eliminar(Especie especie){

        String regAfectados="filas afectadas= ";
        int contador=0;

        if (verificarIntegridad(especie,2)) {
            contador+=db.delete("especie", "codespecie='"+especie.getCodespecie()+"'", null);
            regAfectados+=contador;
        }
        else{
            regAfectados+="\n Especie que quiere eliminar , tiene especies en la tabla animal o tiene especies en peligro de extincion";

        }


        return regAfectados;

    }
    public Animal[] consultarAnimal(String codpais) {
        Pais existe= new Pais();
        existe.setCodpais(codpais);
        if(verificarIntegridad(existe,3)) {
            String[] id2 = {codpais};
            Cursor cursor1 = db.query("animal", camposAnimal, "codpais = ? ", id2, null, null, null);
            Animal[] arregloanimal = new Animal[10];
            int i = 0;

            for (cursor1.moveToFirst(); !cursor1.isAfterLast(); cursor1.moveToNext()) {
                String[] id1 = {cursor1.getString(0)};
                Cursor cursor2 = db.query("especie", camposEspecie, "codespecie = ? ", id1, null, null, null);
                if (cursor2.moveToFirst()) {
                    if (cursor2.getInt(2) > 0) {

                        Animal animal = new Animal();
                        animal.setCodespecie(cursor1.getString(0));
                        animal.setCodpais(cursor1.getString(1));
                        animal.setCodanimal(cursor1.getString(2));
                        animal.setEdadanimal(cursor1.getFloat(3));
                        animal.setEstaVivo(cursor1.getString(4));


                        arregloanimal[i] = animal;
                        i++;
                    }
                }


            }
            return arregloanimal;
        }
        return null;
    }


    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1:
            {
                Animal animal = (Animal) dato;
                String[] id1 = {animal.getCodespecie()};
                String[] id2 = {animal.getCodpais()};
                abrir();
                Cursor cursor1 = db.query("especie", null, "codespecie  = ?", id1, null,
                        null, null);
                Cursor cursor2 = db.query("pais", null, "codpais   = ?", id2, null,
                        null, null);

                if(cursor1.moveToFirst()){
                    if(cursor2.moveToFirst()){
                        return true;
                    }
                    else {
                        return false;
                    }

                }
                else{
                    return false;
                }


            }
            case 2: {
                    Especie especie = (Especie) dato;
                    String[] id1 = {especie.getCodespecie()};

                    abrir();
                    Cursor cursor1 = db.query("animal", null, "codespecie  = ?", id1, null,
                            null, null);
                    Cursor cursor2 = db.query("especie", null, "codespecie   = ?", id1, null,
                            null, null);

                    if(!cursor1.moveToFirst()){
                        if(cursor2.moveToFirst()){
                            if(cursor2.getDouble(2)==0){
                            return true;
                            }
                        }
                        else {
                            return false;
                        }

                    }
                    else{
                        return false;
                    }

            }
            case 3: {
                    Pais pais = (Pais) dato;
                    String[] id1 = {pais.getCodpais()};
                    abrir();
                    Cursor cursor2 = db.query("pais", null, "codpais   = ?", id1, null,
                        null, null);
                    if(cursor2.moveToFirst()){
                        return true;
                    }
                    else {
                        return false;
                    }

            }
            case 5: {

            }
            default:
                return false;
        }
    }
    public String llenarBDAc13002() {
        final String[] VAcodeespecie = {"111111", "111112", "111113", "111114"};
        final String[] VAespecie = {"Mamifero", "aves", "reptiles", "anfibios"};
        final int[] VApeligroextincion = {0, 0, 70, 90};
        final int[] VArecuperados = {1, 2, 3, 4};
        final int[] VAmuertos = {5, 6, 7, 8};

        final String[] VPcodpais = {"111", "112", "113", "114"};
        final String[] VPnombrepais = {"Mexico", "Nigeria", "El salvador", "Canada"};
        final String[] VPesdeafrica = {"0", "1", "0", "0"};

        final String[] VCcodeespecie = {"111113", "111113", "111112", "111114"};
        final String[] VCcodpais = {"111", "112", "112", "114"};
        final String[] VCcodanimal = {"111111", "111112", "111113", "111114"};
        final float [] VCedadanimal = {5, 6, 7, 8};
        final String[] VCestavivo= {"1", "1", "1", "1"};


        abrir();
        db.execSQL("DELETE FROM animal");
        db.execSQL("DELETE FROM especie");
        db.execSQL("DELETE FROM pais");

        Especie especie = new Especie();
        for (int i = 0; i < 4; i++) {
            especie.setCodespecie(VAcodeespecie[i]);
            especie.setNomespecie(VAespecie[i]);
            especie.setPeligroextincion(VApeligroextincion[i]);
            especie.setRecuperados(VArecuperados[i]);
            especie.setMuertos(VAmuertos[i]);

            insertar(especie);
        }
        Pais pais = new Pais();
        for (int i = 0; i < 4; i++) {
            pais.setCodpais(VPcodpais[i]);
            pais.setNommpais(VPnombrepais[i]);
            pais.setEsdeAfrica(VPesdeafrica[i]);

            insertar(pais);
        }

        Animal animal = new Animal();
        for (int i = 0; i < 4; i++) {
            animal.setCodespecie(VCcodeespecie[i]);
            animal.setCodpais(VCcodpais[i]);
            animal.setCodanimal(VCcodanimal[i]);
            animal.setEdadanimal(VCedadanimal[i]);
            animal.setEstaVivo(VCestavivo[i]);


            insertar(animal);
        }


        cerrar();
        return "Guardo Correctamente";


    }

    }
