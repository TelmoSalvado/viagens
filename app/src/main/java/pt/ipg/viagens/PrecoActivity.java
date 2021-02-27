package pt.ipg.viagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco);
    }
    public void NovoPrecoViagem(View view) {

        Intent intent = new Intent(getApplicationContext(), NovoPrecoActivity.class);
        startActivity(intent);
        finish();
    }
    public void VerPreco(View view) {

        Intent intent = new Intent(getApplicationContext(), VerPrecoActivity.class);
        startActivity(intent);
        finish();
    }
    public void EliminarPreco(View view) {

        Intent intent = new Intent(getApplicationContext(), EliminarPrecoActivity.class);
        startActivity(intent);
        finish();
    }
}