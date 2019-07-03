package com.example.futebol;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaJogador extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listview;
    private String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogador);

        listview    =   findViewById(R.id.list);


        NaoTenhoConta naoTenhoConta = new NaoTenhoConta();
        TenhoConta tenhoConta = new TenhoConta();
        cpf = naoTenhoConta.getCpfStrig();
        if (cpf.isEmpty()){
            cpf = tenhoConta.getUsuStrig();
        }

        if(cpf!=null) {
            db.collection("Jogador").whereEqualTo("CPFLEADER",cpf)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                List<String> list = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String cpf = (String) document.getData().get("NAMEPLAYER");
                                    list.add(cpf);
                                }

                                String[] values = new String[] {  list.get(0),
                                        list.get(1), list.get(2), list.get(3),
                                        list.get(4), list.get(5), list.get(6),
                                        list.get(7), list.get(8), list.get(9),
                                        list.get(10)
                                };

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                        getApplicationContext(),
                                        android.R.layout.simple_list_item_1,
                                        android.R.id.text1,
                                        values
                                );

                                listview.setAdapter(adapter);

                            } else {
                                Log.w("->>", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }
}
