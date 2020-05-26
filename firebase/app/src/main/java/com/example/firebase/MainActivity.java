package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebase.model.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private List<Persona> LisPerson= new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    private FirebaseAuth mAuth;

    EditText nomP, appP, correoP, passwordP;
    ListView listV_personas;
    FirebaseDatabase firebaseDatabase;
    //DatabaseReference VARIABLE;
    FirebaseFirestore VARIABLE;

    Persona personaselected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth

        setContentView(R.layout.activity_main);

        //enlace con la interfaz grafica
        nomP= findViewById(R.id.txt_nombrePersona);
        appP= findViewById(R.id.txt_appPersona);
        correoP= findViewById(R.id.txt_correoPersona);
        passwordP= findViewById(R.id.txt_passwordPersona);

        listV_personas= findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        mAuth = FirebaseAuth.getInstance();
        //mAuth.signInWithEmailAndPassword(correo,passwordP);
        //firebaseDatabase =FirebaseDatabase.getInstance();
        //databaseReference =firebaseDatabase.getReference();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaselected= (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaselected.getNombre());
                appP.setText(personaselected.getApellidos());
                correoP.setText(personaselected.getCorreo());
                passwordP.setText(personaselected.getPassword());
            }
        });
    }




    private void listarDatos() {
        VARIABLE.collection("Persona")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc: task.getResult()){
                            Persona p = new Persona(doc.getString("uid"),doc.getString("nombre"),doc.getString("apellidos"),
                                    doc.getString("correo"),doc.getString("password"));
                            LisPerson.add(p);
                            arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, LisPerson);
                            listV_personas.setAdapter(arrayAdapterPersona);

                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Fallo", Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void inicializarFirebase() {
        //VARIABLE = FirebaseDatabase.getInstance().getReference();
        VARIABLE = FirebaseFirestore.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombre=nomP.getText().toString();
        String app=appP.getText().toString();
        String correo=correoP.getText().toString();
        String password=passwordP.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (nombre.equals("")||app.equals("")||correo.equals("")||password.equals("")){
                    validacion();

                }
                else {
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellidos(app);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    //VARIABLE.child("Persona").child(p.getUid()).setValue(p);
                    VARIABLE.collection("Persona").document(p.getUid()).set(p)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //dato ingresado correctamente
                            Toast.makeText(MainActivity.this,"Agregar", Toast.LENGTH_LONG).show();
                            limpiarCajas();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //si falla ingresar datos
                            Toast.makeText(MainActivity.this,"Fallo", Toast.LENGTH_LONG).show();
                        }
                    })
                    ;


                }
                break;
            }
            case R.id.icon_save:{
                Persona p= new Persona();
                p.setUid(personaselected.getUid());
                p.setNombre(nomP.getText().toString().trim());
                p.setApellidos(appP.getText().toString().trim());
                p.setCorreo(correoP.getText().toString().trim());
                p.setPassword(passwordP.getText().toString().trim());

                VARIABLE.collection("Persona").document(p.getUid())
                        .update("uid",p.getUid(),"nombre",p.getNombre(),
                               "apellidos",p.getApellidos(),"correo",p.getCorreo(),"password",p.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //dato ingresado correctamente
                                Toast.makeText(MainActivity.this,"Actualizado", Toast.LENGTH_LONG).show();
                                limpiarCajas();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //si falla ingresar datos
                                Toast.makeText(MainActivity.this,"Fallo", Toast.LENGTH_LONG).show();
                            }
                        })
                ;
                break;
            }
            case R.id.icon_delete:{
                Persona p=new Persona();
                p.setUid(personaselected.getUid());

                VARIABLE.collection("Persona").document(p.getUid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //dato ingresado correctamente
                                Toast.makeText(MainActivity.this,"Eliminado", Toast.LENGTH_LONG).show();
                                limpiarCajas();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //si falla ingresar datos
                                Toast.makeText(MainActivity.this,"Fallo", Toast.LENGTH_LONG).show();
                            }
                        })
                ;
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomP.setText("");
        appP.setText("");
        correoP.setText("");
        passwordP.setText("");
    }

    private void validacion() {
        String nombre=nomP.getText().toString();
        String app=appP.getText().toString();
        String correo=correoP.getText().toString();
        String password=passwordP.getText().toString();

        if (nombre.equals("")){
            nomP.setError("Required");
        }
        else if (app.equals("")){
            appP.setError("Required");
        }
        else if (correo.equals("")){
            correoP.setError("Required");
        }
        else if (password.equals("")){
            passwordP.setError("Required");
        }
    }
}
