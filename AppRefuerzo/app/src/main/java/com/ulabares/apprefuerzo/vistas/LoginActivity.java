package com.ulabares.apprefuerzo.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ulabares.apprefuerzo.MainActivity;
import com.ulabares.apprefuerzo.R;
import com.ulabares.apprefuerzo.controladores.LoginControlador;
import com.ulabares.apprefuerzo.utiles.ValidarCorreo;

public class LoginActivity extends AppCompatActivity {

    private String correo="", contraseña="";
    private ImageView iv_logo;
    private EditText et_correo, et_contraseña;
    private Button b_login;
    private TextView tv_olvidocontra, tv_registrarse, tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioConectado();

        iv_logo= findViewById(R.id.iv_logo);
        et_correo= findViewById(R.id.et_correo);
        et_contraseña= findViewById(R.id.et_contraseña);
        b_login= findViewById(R.id.b_login);
        tv_olvidocontra= findViewById(R.id.tv_olvidocontra);
        tv_registrarse= findViewById(R.id.tv_registrarse);
        tv_info= findViewById(R.id.tv_info);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposVacios()){
                    if (habilitar()){
                        LoginControlador.login(LoginActivity.this, getCorreo(), getContraseña());
                    }else {
                        Toast.makeText(LoginActivity.this, "Los datos ingresados no son validos", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        tv_olvidocontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, RecuperarContraActivity.class);
                startActivity(intent);
            }
        });

        tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }

    private boolean habilitar(){
        String correo= getCorreo().trim();
        String contraseña= getContraseña().trim();


        if(ValidarCorreo.validar(correo) && contraseña.length()>5){
            return true;
        }else {
            return false;
        }
    }

    private boolean validarCamposVacios(){
        boolean retorna= true;
        correo=getCorreo().trim();
        contraseña=getContraseña().trim();
        if(correo.isEmpty()){
            et_correo.setError("Debe completar el campo Correo");
            retorna=false;
        }
        if(contraseña.isEmpty()){
            et_contraseña.setError("Debe completar el campo Contraseña");
            retorna=false;
        }

        return retorna;

    }

    public String getCorreo() {
        return et_correo.getText().toString();
    }

    public String getContraseña() {
        return et_contraseña.getText().toString();
    }


    private void usuarioConectado() {

        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();

        if(usuario!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}