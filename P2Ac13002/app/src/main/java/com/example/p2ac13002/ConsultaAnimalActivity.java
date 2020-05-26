package com.example.p2ac13002;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultaAnimalActivity extends AppCompatActivity {
    private List<Animal> LisAnimal= new ArrayList<Animal>();
    ArrayAdapter<Animal> arrayAdapterAnimal;

    ControlBDAc13002 helper;
    EditText editcodigopais;
    EditText editnombrepais;
    ListView lv_datosAnimales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_animal);
        helper = new ControlBDAc13002(this);
        editcodigopais = (EditText) findViewById(R.id.editcodigopais);
        editnombrepais = (EditText) findViewById(R.id.editnombrepais);
        lv_datosAnimales = (ListView) findViewById(R.id.lv_datosAnimales);
    }
    @SuppressLint("SetTextI18n")
    public void consultarAnimal(View v) {
        helper.abrir();
        Animal[] animal = helper.consultarAnimal(editcodigopais.getText().toString());
        helper.cerrar();
        if(animal==null){
            Toast.makeText(this, "Ningun animal registrado con esos datos",
                    Toast.LENGTH_LONG).show();
        } else{
            for (int i = 0; i < 10; i++) {
                LisAnimal.add(animal[i]);
                arrayAdapterAnimal = new ArrayAdapter<Animal>(ConsultaAnimalActivity.this, android.R.layout.simple_list_item_1, LisAnimal);
                lv_datosAnimales.setAdapter(arrayAdapterAnimal);
            }


        }
        editnombrepais.setText("Nigeria");
    }
    public void limpiarTexto(View v) {
        editcodigopais.setText("");
        editnombrepais.setText("");
        lv_datosAnimales.setAdapter(null);

    }
}
