package com.example.futebol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class NaoTenhoConta extends AppCompatActivity {
    private TextView voltarParaCriarTela;
    public  EditText inputNomeUsuario;
    private EditText inputSenhaUsuario;
    private Button   validarEntrada;
    private static String cpfStrig = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_tenho_conta);

        voltarParaCriarTela = findViewById(R.id.voltarParaCriarConta);
        inputNomeUsuario    = findViewById(R.id.NomeUsuarioEntrada);
        inputSenhaUsuario   = findViewById(R.id.SenhaUsuarioEntrada);
        validarEntrada      = findViewById(R.id.btValidarEntrada);


    }

    public void voltarParaCriarConta(View view){

        Intent intent = new Intent(this, TenhoConta.class);
        startActivity(intent);
    }

    public void irParaMenuPrincipal(View view){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Treinador").whereEqualTo("CPF", inputNomeUsuario.getText().toString())
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("oeeee", document.getId() + " => " + document.getData().get("CPF"));
                            String cpf = (String) document.getData().get("CPF");
                            if (cpf != null && !cpf.isEmpty()) {
                                cpfStrig = inputNomeUsuario.getText().toString();
                                Intent intent = new Intent(getBaseContext(), TelaPrincipal.class);
                                startActivity(intent);
                            }
                        }
                    } else {
                        Log.w("tei", "Error getting documents.", task.getException());
                    }
                }
            });
    }

    public EditText getInputNomeUsuario() {
        return inputNomeUsuario;
    }

    public String getCpfStrig() {
        return cpfStrig;
    }
}
