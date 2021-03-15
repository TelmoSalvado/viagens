package pt.ipg.viagens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NovoPerfil extends AppCompatActivity {
    private EditText Textnome;
    private EditText Textlocalidade;
    private EditText Textdatadenascimento;
    private EditText Texttelemovel;
    DatabaseReference databaseReferencePerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReferencePerfil = FirebaseDatabase.getInstance().getReference("perfil");
        setContentView(R.layout.activity_novo_perfil);
        Textnome = (EditText) findViewById(R.id.nome);
        Textlocalidade = (EditText) findViewById(R.id.localidade);
        Textdatadenascimento = (EditText) findViewById(R.id.datadenascimento);
        Texttelemovel = (EditText) findViewById(R.id.telemovel);


    }
    public void GuardarPerfil(View view){

        String Nome = Textnome.getText().toString();


        String Localidade = Textlocalidade.getText().toString();
        if (Nome.trim().length() == 0) {
            Textnome.setError(getString(R.string.message_required));
            Textnome.requestFocus();
            return;
        }

        if (Localidade.trim().length() == 0) {
            Textlocalidade.setError(getString(R.string.message_required));
            Textlocalidade.requestFocus();
            return;
        }

        String data = Textdatadenascimento.getText().toString();
        if(data.trim().length() == 0){
            Textdatadenascimento.setError(getString(R.string.message_required));
            Textdatadenascimento.requestFocus();
            return;
        }else  if (data.length() != 10 || data.charAt(2) != '/' || data.charAt(5) != '/') {
            Textdatadenascimento.setError(getString(R.string.Validar_data));
            Textdatadenascimento.requestFocus();
            return;
        }
        String[] dataSeparada = data.split("/");

        int Dia = Integer.parseInt(dataSeparada[0]);
        int Mes = Integer.parseInt(dataSeparada[1]);
        int Ano = Integer.parseInt(dataSeparada[2]);
        if(Dia > 30 && (Mes == 6 || Mes == 4 || Mes == 9 || Mes == 11)  ) {

            Textdatadenascimento.setError("Error na Data");
            Textdatadenascimento.requestFocus();
            return;
        }else if (Dia > 29 && Mes == 2){
            Textdatadenascimento.setError("Erro na data");
            Textdatadenascimento.requestFocus();
            return;
        }else if(Dia <= 0 || Dia > 31 || Mes <= 0 || Mes > 12 || Ano > 2020){
            Textdatadenascimento.setError("Erro na data");
            Textdatadenascimento.requestFocus();
            return;
        }



        String Telemovel = Texttelemovel.getText().toString();



        if (Telemovel.trim().length() == 0) {
            Texttelemovel.setError(getString(R.string.message_required));
            Texttelemovel.requestFocus();
            return;
        }

        String id = databaseReferencePerfil.push().getKey();
        Perfil perfil = new Perfil(id, Nome, Localidade, data, Telemovel);
        databaseReferencePerfil.child(id).setValue(perfil);



        Toast.makeText(this, R.string.Guardar, Toast.LENGTH_SHORT).show();



    }

    public void Cancelar(View view) {
        Toast.makeText(this, R.string.Cancelar, Toast.LENGTH_SHORT).show();
        finish();
    }
}