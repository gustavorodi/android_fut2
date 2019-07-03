package com.example.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends AppCompatActivity {

    private Button buscarParatida;
    private Button ultimosResultados;
    private Button listarJogadores;
    private Button addJogador;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

    }

    public void AddJogadorNoTime(View view){

        Intent intent = new Intent(this, AddJogadorNoTime.class);
        startActivity(intent);
    }

    public void ListJogadorNoTime(View view){

        Intent intent = new Intent(this, ListaJogador.class);
        startActivity(intent);
    }

    public void iniciarPartida(View view){

        Intent intent = new Intent(this, BuscarJogador.class);
        startActivity(intent);
    }





}
