package com.ulabares.apprefuerzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.ulabares.apprefuerzo.vistas.LoginActivity;
import com.ulabares.apprefuerzo.vistas.perfil.PerfilActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout= findViewById(R.id.tabLayout);
        viewPager= findViewById(R.id.viewPager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.perfil:
                Toast.makeText(this, "Ingresando al Perfil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, PerfilActivity.class));

                break;
            case R.id.notificacion:
                Toast.makeText(this, "Notificación", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
                break;
            case R.id.salir:
                cerrarSesion();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cerrarSesion() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}