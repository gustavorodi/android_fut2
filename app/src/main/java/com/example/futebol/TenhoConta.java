package com.example.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class TenhoConta extends AppCompatActivity {

    private EditText nmTreinador;
    private EditText nmTime;
    private EditText cpfTreinador;
    private EditText emailTre;
    private EditText novaSenha;
    private Button   validar;
    private Integer  escolha;
    private RadioGroup groups;
    public static String UsuStrig;

    SqlConnect connect = new SqlConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenho_conta);

        nmTreinador     = findViewById(R.id.textNMTreinador);
        nmTime          = findViewById(R.id.textNMTime);
        cpfTreinador    = findViewById(R.id.textCPFTeinador);
        novaSenha       = findViewById(R.id.senhaNova);
        validar         = findViewById(R.id.validarNovoCadastro);

        groups = findViewById(R.id.group);


        groups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton3:
                        escolha = 3;
                        break;
                    case R.id.radioButton2:
                        escolha = 2;
                        break;
                    case R.id.radioButton:
                        escolha = 1;
                        break;
                }
            }

        });

        /*
        *
        * VARIAVEIS PARA SQL :
        * ESCOLHA, NMTREINADO, NMTIME, CPFTREINADOR
        * */

    }

    public void novoUsuarioPronto(View view){

        connect.addTreinador(nmTreinador.getText().toString(), cpfTreinador.getText().toString(), novaSenha.getText().toString(), nmTime.getText().toString(), escolha);
        UsuStrig = cpfTreinador.getText().toString();
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

    public String getUsuStrig() {
        return UsuStrig;
    }

}
