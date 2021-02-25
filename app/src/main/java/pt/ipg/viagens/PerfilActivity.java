package pt.ipg.viagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }
    public void verPerfil(View view) {

        Intent intent = new Intent(getApplicationContext(), VerPerfilActivity.class);
        startActivity(intent);
        finish();
    }
    public void novoPerfil(View view) {

        Intent intent = new Intent(getApplicationContext(), NovoPerfil.class);
        startActivity(intent);
        finish();
    }
    public void eliminarPerfil(View view) {

        Intent intent = new Intent(getApplicationContext(), EliminarPerfil.class);
        startActivity(intent);
        finish();
    }
}