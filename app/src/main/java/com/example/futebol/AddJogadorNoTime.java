package com.example.futebol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AddJogadorNoTime extends AppCompatActivity {
    private EditText nmJogador;
    private EditText cpfJogador;
    private EditText numeroCamisa;
    private EditText senhaconf;
    SqlConnect connect = new SqlConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jogador_no_time);

        nmJogador       =   findViewById(R.id.editText);
        cpfJogador      =   findViewById(R.id.editText3);
        numeroCamisa    =   findViewById(R.id.editText4);
        senhaconf       =   findViewById(R.id.editText2);
    }

    public void AddJogadorNoTime(View view){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Treinador").whereEqualTo("NEWPASSWORD",senhaconf.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("oeeee", document.getId() + " => " + document.getData().get("CPF"));
                                    String info = (String) document.getData().get("CPF");
                                    if (info!= null) {
                                        connect.addJogador(nmJogador.getText().toString(), cpfJogador.getText().toString(), numeroCamisa.getText().toString(), info);
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
}
