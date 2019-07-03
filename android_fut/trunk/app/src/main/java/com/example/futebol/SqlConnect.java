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
    static Random random = new Random();
    NaoTenhoConta naoTenhoConta = new NaoTenhoConta();

    public static Integer ids = random.nextInt(1500);

    public String treindadorVoltou;

    private String s;

    public SqlConnect() {

    }

    protected void mudar(String y){
        System.out.println("aqui é o y "+y);
        setS(y);
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


    protected void quemEntrou(String cpf)
    {
        db.collection("Treinador").whereEqualTo("CPF",cpf)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("oeeee", document.getId() + " => " + document.getData().get("ID"));
                                treindadorVoltou = (String) document.getData().get("ID");
                                Log.i("oq2 vem", String.valueOf(treindadorVoltou));
                            }
                        } else {
                            Log.w("tei", "Error getting documents.", task.getException());
                        }
                    }
                });
        Log.i("oq vem", String.valueOf(treindadorVoltou));
    }



    protected void addJdogador(String nmJogador, String cpfJogador, String numeroCamisa)
    {
        String cpfVelho = s;
        System.out.println("->->"+s);
        db.collection("Treinador").whereEqualTo("CPF",cpfVelho)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("oe3", document.getId() + " => " + document.getData().get("ID"));
                                treindadorVoltou = (String) document.getData().get("ID");
                                Log.i("oq3 vem", String.valueOf(treindadorVoltou));
                            }
                        } else {
                            Log.w("teie", "Error getting documents.", task.getException());
                        }
                    }
                });
        Log.i("oq vem", String.valueOf(treindadorVoltou));

    }

    public void setS(String s) {
        this.s = s;
    }
}