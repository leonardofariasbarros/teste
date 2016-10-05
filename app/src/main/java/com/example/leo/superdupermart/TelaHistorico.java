package com.example.leo.superdupermart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TelaHistorico extends AppCompatActivity {

    String login;
    List<Carrinho> carrinhos;
    ListView lista;
    Button limparHist;
    Adapter3 adapter;
    TextView txtAviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        Intent intent = getIntent();
        login =intent.getStringExtra("login");
        lista = (ListView)findViewById(R.id.listHist);
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO(getBaseContext());
        carrinhos = carrinhoDAO.listaHistorico(login);
        adapter = new Adapter3(carrinhos, this);
        lista.setAdapter(adapter);

        limparHist = (Button)findViewById(R.id.buttonLimparHist);

        if(carrinhos.size()==0){
            TextView aviso = (TextView)findViewById(R.id.txtAviso);
            aviso.setText("Histórico vazio!");
        }else{
            limparHist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarrinhoDAO carrinhoDAO1 = new CarrinhoDAO(getBaseContext());
                    carrinhoDAO1.limparHistorico(login);
                    TelaHistorico.this.finish();
                    Toast.makeText(getBaseContext(),"Histórico Vazio",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
