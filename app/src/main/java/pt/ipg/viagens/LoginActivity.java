package pt.ipg.viagens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //variavel para componentes de layout
    private Button btnLogin;
    private EditText editEmail;
    private EditText editPassowrd;

    //variavel para auth firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        compDeLayout();

        //Codigo para aut firebase
        mAuth = FirebaseAuth.getInstance();

        //acção dos componentes de layout
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString();
                String pass = editPassowrd.getText().toString();

                //Verificação do campo
                if (!email.isEmpty()){
                    if (!pass.isEmpty()){

                        loginUser(email,pass);

                    } else {

                        editPassowrd.setError("Introduza a password");
                        editPassowrd.requestFocus();
                        return;
                    }

                } else {

                    editEmail.setError("Introduza o email");
                    editEmail.requestFocus();
                    return;
                }
            }
        });


    }

    //Função login
    private void loginUser (String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Bem - Vindo", Toast.LENGTH_SHORT).show();
                            abrirPrincipal();
                        } else {

                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "A conta não existe !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Verifica se utilizador tem sessão iniciada
    private boolean userConnect(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onStart() {

        super.onStart();

        if (userConnect()){
            abrirPrincipal();
        }
    }

    //Os metodos
    private void abrirPrincipal(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Registar(View view){

        Intent intent = new Intent(getApplicationContext(), RegistoActivity.class);
        startActivity(intent);
        finish();
    }

    private  void compDeLayout (){

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        editEmail = (EditText) findViewById(R.id.email);
        editPassowrd = (EditText) findViewById(R.id.password);

    }

}