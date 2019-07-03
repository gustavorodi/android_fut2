package com.example.futebol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class BuscarJogador extends AppCompatActivity {
    private String cpf;
    private TextView quem;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_jogador);

        quem = findViewById(R.id.textView6);

    }

    public void buscar(View view){
        NaoTenhoConta naoTenhoConta = new NaoTenhoConta();
        TenhoConta tenhoConta = new TenhoConta();
        cpf = naoTenhoConta.getCpfStrig();
        if (cpf.isEmpty()){
            cpf = tenhoConta.getUsuStrig();
        }

        db.collection("Treinador").limit(1).orderBy("CPF")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String rival;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("aquiii", "DocumentSnapshot added with ID: " + document.getId());

                                rival = (String) document.getData().get("NAMEPLAYER");
                                quem.setText("Seu rival vai ser : \n ->" +rival);
                            }


                        } else {
                            Log.w("->2", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void Voltar(View view){

        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

}
