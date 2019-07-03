package com.example.futebol;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SqlConnect {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> treinador = new HashMap<>();
    Map<String, Object> jogador = new HashMap<>();

    public SqlConnect() {

    }

    protected void  addTreinador ( String nmTreinador, String cpfTreinador, String novaSenha,  String nmTime, Integer estado )
            {

            treinador.put("NAMELEADER", nmTreinador);
            treinador.put("CPF",cpfTreinador );
            treinador.put("TIMENAME", nmTime );
            treinador.put("NEWPASSWORD", novaSenha);
            treinador.put("LOCATION",estado);

// Add a new document with a generated ID
        db.collection("Treinador")
                .add(treinador)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("aquiii", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("aquiiiiiiii2", "Error adding document", e);
                    }
                });

    }

    protected void  addJogador ( String nmJogador, String cpfJogador, String numeroCamisa, String cpfLeader) {
        jogador.put("NAMEPLAYER", nmJogador);
        jogador.put("CPF", cpfJogador);
        jogador.put("SHIRTNUMBER", numeroCamisa);
        jogador.put("CPFLEADER",cpfLeader);

// Add a new document with a generated ID
        db.collection("Jogador")
                .add(jogador)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("aquiii", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("aquiiiiiiii2", "Error adding document", e);
                    }
                });
    }
}
