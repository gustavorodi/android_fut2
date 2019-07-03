package com.example.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btTenhoConta;
    private Button btNaoTenhoConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTenhoConta = findViewById(R.id.btnaoTenhoConta);
        btNaoTenhoConta = findViewById(R.id.btTenhoConta);

    }

    public void entrarNoTenhoConta(View view){

        Intent intent = new Intent(this, NaoTenhoConta.class);
        startActivity(intent);
    }

    public void entrarNoNaoTenhoConta(View view){

        Intent intent = new Intent(this, TenhoConta.class);
        startActivity(intent);
    }
}
