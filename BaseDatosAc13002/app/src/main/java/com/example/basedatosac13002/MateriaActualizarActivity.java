package com.example.basedatosac13002;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MateriaActualizarActivity extends Activity {
    ControlBDAc13002 helper;
    EditText editMateria;
    EditText editNombreMateria;
    EditText editUnidadValorativa;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar);
        helper = new ControlBDAc13002(this);
        editMateria = (EditText) findViewById(R.id.editMateria);
        editNombreMateria = (EditText) findViewById(R.id.editNombreMateria);
        editUnidadValorativa = (EditText) findViewById(R.id.editUnidadValorativa);
    }
    public void actualizarMateria(View v) {
        Materia materia = new Materia();
        materia.setCodmateria(editMateria.getText().toString());
        materia.setNommateria(editNombreMateria.getText().toString());
        materia.setUnidadesval(editUnidadValorativa.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(materia);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editMateria.setText("");
        editNombreMateria.setText("");
        editUnidadValorativa.setText("");
    }
}