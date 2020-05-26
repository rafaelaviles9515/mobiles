package com.example.p2ac13002;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEspecieActivity extends Activity {
    EditText editCodespecie;
    ControlBDAc13002 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_especie);
        controlhelper=new ControlBDAc13002 (this);
        editCodespecie=(EditText)findViewById(R.id.editCodespecie);
    }
    public void eliminarEspecie(View v){
        String regEliminadas;
        Especie especie=new Especie();
        especie.setCodespecie(editCodespecie.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(especie);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
