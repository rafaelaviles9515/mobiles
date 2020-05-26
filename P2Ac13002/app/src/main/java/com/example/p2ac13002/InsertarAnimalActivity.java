package com.example.p2ac13002;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarAnimalActivity extends Activity {
    ControlBDAc13002 helper;
    EditText editCodigoespecie;
    EditText editCodigopais;
    EditText editCodigoanimal;
    EditText editEdadanimal;
    EditText editEstavivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_animal);
        helper = new ControlBDAc13002(this);
        editCodigoespecie = (EditText) findViewById(R.id.editCodigoespecie);
        editCodigopais = (EditText) findViewById(R.id.editCodigopais);
        editCodigoanimal = (EditText) findViewById(R.id.editCodigoanimal);
        editEdadanimal = (EditText) findViewById(R.id.editEdadanimal);
        editEstavivo = (EditText) findViewById(R.id.editEstavivo);
    }
    public void insertarAnimal(View v) {
        String regInsertados;
        String codigoespecie=editCodigoespecie.getText().toString();
        String codigopais=editCodigopais.getText().toString();
        String codigoanimal=editCodigoanimal.getText().toString();
        float edadanimal= Float.parseFloat(editEdadanimal.getText().toString());
        String estavivo=editEstavivo.getText().toString();
        Animal animal= new Animal();
        animal.setCodespecie(codigoespecie);
        animal.setCodpais(codigopais);
        animal.setCodanimal(codigoanimal);
        animal.setEdadanimal(edadanimal);
        animal.setEstaVivo(estavivo);

        helper.abrir();
        regInsertados=helper.insertar(animal);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigoespecie.setText("");
        editCodigopais.setText("");
        editCodigoanimal.setText("");
        editEdadanimal.setText("");
        editEstavivo.setText("");
    }
}
