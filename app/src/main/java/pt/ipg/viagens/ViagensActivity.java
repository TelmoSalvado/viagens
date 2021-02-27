package pt.ipg.viagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViagensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);
    }
    public void  NovaViagem(View view){
        Intent intent = new Intent(this, NovaViagem.class);
        startActivity(intent);
    }
    public void  VerViagem(View view){
        Intent intent = new Intent(this, VerViagem.class);
        startActivity(intent);
    }
    public void  ApagarViagem(View view){
        Intent intent = new Intent(this, EliminarViagem.class);
        startActivity(intent);
    }

}