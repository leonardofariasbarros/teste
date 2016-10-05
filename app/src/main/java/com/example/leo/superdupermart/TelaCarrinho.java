package com.example.leo.superdupermart;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class TelaCarrinho extends AppCompatActivity {
    String login;
    float total = 0;
    DecimalFormat df = new DecimalFormat("#0.00");
    List<Carrinho> carrinhos;
    TextView txtNome;
    ListView lista;
    TextView valortotal;
    TextView txtAviso;
    Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);
        Intent intent = getIntent();
        login =intent.getStringExtra("login");
        lista = (ListView)findViewById(R.id.listMeuCarrinho);
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO(getBaseContext());
        carrinhos = carrinhoDAO.listaCarrinho(login);
        Adapter2 adapter = new Adapter2(carrinhos, this);
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                txtNome = (TextView)view.findViewById(R.id.textViewNome);
                return false;
            }
        });
        registerForContextMenu(lista);

        for(int i=0;i<carrinhos.size();i++){
            float qtd = Float.parseFloat(carrinhos.get(i).getQuantidade());
            float pr = Float.parseFloat(carrinhos.get(i).getPreco());
            float subt = qtd * pr;
            total = total + subt;
        }

        df.format(00.00);
        valortotal = (TextView) findViewById(R.id.textValorTotal);
        valortotal.setText(df.format(total));

        finalizar = (Button) findViewById(R.id.buttonFinalizar);

        if(carrinhos.size()==0){
            TextView aviso = (TextView)findViewById(R.id.txtAviso);
            aviso.setText("Não há produtos no seu carrinho!");
        }else{
            finalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pagamento();
                }
            });
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemRemover:{
                CarrinhoDAO carrinhoDAO = new CarrinhoDAO(this);
                if(carrinhoDAO.removerCarrinho(login.toString(),txtNome.getText().toString())){
                   mostratoast("Removido com sucesso!");
                    carrinhos = carrinhoDAO.listaCarrinho(login);
                    Adapter2 adapter = new Adapter2(carrinhos, this);
                    lista.setAdapter(adapter);
                    total = 0;
                    for(int i=0;i<carrinhos.size();i++){
                        float qtd = Float.parseFloat(carrinhos.get(i).getQuantidade());
                        float pr = Float.parseFloat(carrinhos.get(i).getPreco());
                        float subt = qtd * pr;
                        total = total + subt;
                    }

                    df.format(00.00);
                    valortotal = (TextView) findViewById(R.id.textValorTotal);
                    valortotal.setText(df.format(total));

                    if(total==0){
                        txtAviso = (TextView)findViewById(R.id.txtAviso);
                        txtAviso.setText("Não há produtos no seu carrinho!");
                        finalizar.setClickable(false);
                    }
                }

            }
            case R.id.itemCancelar:{
                return true;
            }
            default:return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contexto_carrinho, menu);
    }

    public void pagamento(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Forma de Pagamento");
        alert.setMessage("Valor da Compra: "+df.format(total));
        alert.setPositiveButton("Crédito",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
                chamapagamento("Crédito");
            }
        })
                .setNegativeButton("Débito",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                        chamapagamento("Débito");
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    public void chamapagamento(String forma){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layoutpagamento);
        dialog.setTitle("Efetuar Pagamento");

        final Button confirmar = (Button) dialog.findViewById(R.id.btnConfirmar);
        final Button cancelar = (Button) dialog.findViewById(R.id.btnCancelar);
        final TextView txtforma = (TextView)dialog.findViewById(R.id.txtForma);
        final EditText cartao = (EditText)dialog.findViewById(R.id.edtNumeroCartao);
        final EditText senha = (EditText)dialog.findViewById(R.id.edtSenha);
        txtforma.setText(forma);

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cartao.getText().toString().equals("") || senha.getText().toString().equals("")){
                    mostratoast("Preencha os campos!");
                }else{
                for(int i=0;i<carrinhos.size();i++){
                    CarrinhoDAO carrinhoDAO = new CarrinhoDAO(getBaseContext());
                    carrinhoDAO.alteraEstadoComprado(carrinhos.get(i));
                }
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"Compra efetuada com sucesso",Toast.LENGTH_LONG).show();
                TelaCarrinho.this.finish();}
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Cancelado",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        //exibe na tela o dialog
        dialog.show();
    }

    public  void mostratoast(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }

}
