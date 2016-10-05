package com.example.leo.superdupermart;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TelaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProdutoDAO produtoDAO;
    private AlertDialog alerta;
    TextView txtNome,txtDescricao,txtPreco;
    String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        Intent intent = getIntent();
        login =intent.getStringExtra("login");

        TextView textoLogin = (TextView)header.findViewById(R.id.txtLoginUsuario);
        TextView textoNome = (TextView)header.findViewById(R.id.txtNomeUsuario);
        ImageView imagem = (ImageView)header.findViewById(R.id.imgUser);
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);

        textoLogin.setText(login);
        textoNome.setText(usuarioDAO.getEmail(login).getEmail());


        ListView lista = (ListView)findViewById(R.id.listadosprodutos);
        ProdutoDAO produtoDAO2 = new ProdutoDAO(getBaseContext());
        List<Produto> produtos = produtoDAO2.listaProdutos();
        AdapterProdutos adapter = new AdapterProdutos(produtos, this);
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                txtNome = (TextView)view.findViewById(R.id.textViewNome);
                txtDescricao = (TextView)view.findViewById(R.id.textViewDescricao);
                txtPreco = (TextView)view.findViewById(R.id.textViewPreco);
                return false;
            }
        });

        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contexto_produto, menu);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.itemAdd:{
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.add_carrinho);
                //define o título do Dialog
                dialog.setTitle("Adicionar ao Carrinho");

                //instancia os objetos que estão no layout customdialog.xml
                final Button botao = (Button) dialog.findViewById(R.id.btnOk);
                final EditText qtd = (EditText) dialog.findViewById(R.id.edtQtd);

                botao.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(qtd.getText().toString().equals("") || (Integer.parseInt(qtd.getText().toString()))<=0){
                            mostratoast("Quantidade inválida!");
                        }else {
                            Carrinho carrinho = new Carrinho(login.toString(), txtNome.getText().toString(),
                                    txtDescricao.getText().toString(), txtPreco.getText().toString(), qtd.getText().toString(), "13/09/16", "0");
                            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(getBaseContext());
                            carrinhoDAO.addProduto(carrinho);
                            dialog.dismiss();
                            mostratoast("Adicionado ao Carrinho");
                        }
                    }
                });
                //exibe na tela o dialog
                dialog.show();
            }
            case R.id.itemCancelar:{
                return true;
            }
            default:return super.onContextItemSelected(item);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meuCarrinho) {
            Intent intent = new Intent(this,TelaCarrinho.class);
            intent.putExtra("login",login);
            startActivity(intent);
        } else if (id == R.id.nav_historico) {
            Intent intent = new Intent(this, TelaHistorico.class);
            intent.putExtra("login", login);
            startActivity(intent);
        } else if (id == R.id.nav_buscar) {
            Intent intent = new Intent(this,TelaBusca.class);
            intent.putExtra("login",login);
            startActivity(intent);
        } else if (id == R.id.nav_sobre) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Aplicativo Super Duper Mart v1.0 criado por Thayza Ramos");
            alert.setNeutralButton("Ok",null).show();

        } else if (id == R.id.nav_sair) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Tem certeza que deseja sair?");
            alert.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    TelaPrincipal.this.finish();
                }
            })
                    .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void mostratoast(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }

}