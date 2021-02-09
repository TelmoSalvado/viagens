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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegistoActivity extends AppCompatActivity {

    private Button btnRegist;
    private EditText editEmail;
    private EditText editPassowrd;

    //variavel para auth firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        compDeLayout ();

        mAuth = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString();
                String pass = editPassowrd.getText().toString();

                //Verificação do campo
                if (!email.isEmpty()){
                    if (!pass.isEmpty()){

                        regUser(email, pass);
                    } else{

                        editPassowrd.setError("Introduza a password");
                        editPassowrd.requestFocus();
                        return;
                    }
                }else{

                    editEmail.setError("Introduza o email");
                    editEmail.requestFocus();
                    return;
                }
            }
        });

    }

    //Função Registar
    private void regUser (String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegistoActivity.this, "Registo com sucesso", Toast.LENGTH_SHORT).show();
                            abrirPrincipal();
                        } else {

                            String erroExcecao = "";

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcecao = "A sua palavra-passe tem que ter 6 ou mais carateres!!!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcecao = "Digite um endereço de email válido!!!";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcecao = "Este conta já foi registada!!!";
                            } catch (Exception e) {
                                erroExcecao = "ao registar utilizador:" + e.getMessage();
                            }
                            Toast.makeText(RegistoActivity.this, "Erro:" + erroExcecao, Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    //Os metodos
    public void Login(View view){

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void abrirPrincipal(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private  void compDeLayout (){

        btnRegist = (Button) findViewById(R.id.buttonRegistarnovo);
        editEmail = (EditText) findViewById(R.id.emailnovo);
        editPassowrd = (EditText) findViewById(R.id.passwordnovo);
    }
}