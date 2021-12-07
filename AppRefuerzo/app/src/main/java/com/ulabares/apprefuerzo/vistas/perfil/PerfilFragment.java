package com.ulabares.apprefuerzo.vistas.perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ulabares.apprefuerzo.R;


public class PerfilFragment extends Fragment {

    private View view;

    private ImageView iv_foto;
    private TextInputEditText tie_nombre, tie_correo;
    private Button b_editar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_perfil, container, false);

        iv_foto= view.findViewById(R.id.iv_foto);
        tie_nombre= view.findViewById(R.id.tie_nombre);
        tie_correo= view.findViewById(R.id.tie_correo);
        b_editar= view.findViewById(R.id.b_editar);

        b_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (habilitar()){
                    PerfilControlador.actualizarDatos(getActivity(), "nombre", getNombre());
                }else {
                    Toast.makeText(getActivity(), "Ingreso de datos no validos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        obtenerDatos();

        return view;
    }

    private void obtenerDatos() {
    }

    private boolean habilitar(){

        String nombre= getNombre().trim();

        if(nombre.length()>2){
            return true;
        }else {
            return false;
        }
    }

    public String getNombre() {
        return tie_nombre.getText().toString();
    }

    public String getCorreo() {
        return tie_correo.getText().toString();
    }


}