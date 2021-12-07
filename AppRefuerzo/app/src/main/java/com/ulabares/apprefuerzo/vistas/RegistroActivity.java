package com.ulabares.apprefuerzo.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ulabares.apprefuerzo.R;
import com.ulabares.apprefuerzo.controladores.RegistroControlador;
import com.ulabares.apprefuerzo.utiles.ValidarCorreo;

public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText tie_nombre, tie_correo, tie_contraseña, tie_confirmarContra;
    private Button b_registrar;
    String nombre, correo,contraseña="", confirmarContra="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        tie_nombre= findViewById(R.id.tie_nombre);
        tie_correo= findViewById(R.id.tie_correo);
        tie_contraseña= findViewById(R.id.tie_contraseña);
        tie_confirmarContra= findViewById(R.id.tie_confirmarContra);
        b_registrar= findViewById(R.id.b_registrar);


        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposVacios()){

                    if (habilitar()){
                        RegistroControlador.registro(RegistroActivity.this, getNombre(), getCorreo(), getContraseña());
                    }else {
                        Toast.makeText(RegistroActivity.this, "Los datos ingresados no son validos", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private boolean habilitar(){

         nombre= getNombre().trim();
         correo= getCorreo().trim();
         contraseña= getContraseña().trim();
         confirmarContra= getConfirmarContra().trim();

        if(nombre.length()>2 && ValidarCorreo.validar(correo) && contraseña.length()>5 && confirmarContra.equals(contraseña)){
            return true;
        }else {
            return false;
        }
    }

    private boolean validarCamposVacios(){
        boolean retorna= true;
        nombre= getNombre().trim();
        correo= getCorreo().trim();
        contraseña= getContraseña().trim();
        confirmarContra= getConfirmarContra().trim();
        if(nombre.isEmpty()){
            tie_nombre.setError("Debe completar el campo el nombre");
            retorna=false;
        }
        if(correo.isEmpty()){
            tie_correo.setError("Debe completar el campo Correo");
            retorna=false;
        }
        if(contraseña.isEmpty()){
            tie_contraseña.setError("Debe completar el campo Contraseña");
            retorna=false;
        }
        if(confirmarContra.isEmpty()){
            tie_confirmarContra.setError("Debe completar el campo confirmar Contraseña");
            retorna=false;
        }

        return retorna;

    }

    public String getNombre() {
        return tie_nombre.getText().toString();
    }

    public String getCorreo() {
        return tie_correo.getText().toString();
    }

    public String getContraseña() {
        return tie_contraseña.getText().toString();
    }

    public String getConfirmarContra() {
        return tie_confirmarContra.getText().toString();
    }
}