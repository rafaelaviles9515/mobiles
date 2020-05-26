package com.example.basedatosac13002;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaConsultarActivity extends Activity {
    ControlBDAc13002 helper;
    EditText editCodmateria;
    EditText editNommateria;
    EditText editUnidadesval;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);
        helper = new ControlBDAc13002(this);
        editCodmateria = (EditText) findViewById(R.id.editCodmateria);
        editNommateria = (EditText) findViewById(R.id.editNommateria);
        editUnidadesval = (EditText) findViewById(R.id.editUnidadesval);
    }
    public void consultarMateria(View v) {
        helper.abrir();
        Materia materia = helper.consultarMateria(editCodmateria.getText().toString());
        helper.cerrar();
        if(materia == null)
            Toast.makeText(this, "Materia con codigo de materia " +
                    editCodmateria.getText().toString() +" no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCodmateria.setText(materia.getCodmateria());
            editNommateria.setText(materia.getNommateria());
            editUnidadesval.setText(materia.getUnidadesval());
        }
    }
    public void limpiarTexto(View v){
        editCodmateria.setText("");
        editNommateria.setText("");
        editUnidadesval.setText("");
    }
}
