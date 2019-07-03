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

import java.util.Random;

public class BuscarJogador extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Random gerador = new Random();
    private String cpf;
    private TextView quem;



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

        db.collection("Treinador").limit(1).orderBy(ordernar())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String rival;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("aquiii", "DocumentSnapshot added with ID: " + document.getData().get("CPF"));

                                rival = String.valueOf(document.getData().get("TIMENAME"));
                                quem.setText("Seu rival vai ser : \n ->" +rival);
                            }


                        } else {
                            Log.w("->2", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private String ordernar(){
       Integer x = gerador.nextInt(100);
       String  y;
        if (x >= 20 && x <= 50){
            y = "CPF";
        }else if(x > 50 && x <= 80 ){
            y = "ID";
        }else{
            y = "NEWPASSWORD";
        }
        return y;
    }

    public void Voltar(View view){

        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

}
