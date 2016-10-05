package com.example.leo.superdupermart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        ImageView logo = (ImageView)findViewById(R.id.imagLogo);
        logo.setImageResource(R.drawable.carrinho_logo);

    }
    @Override
    protected void onResume() {
        super.onResume();
        EditText editLogin = (EditText) findViewById(R.id.editLogin);
        EditText editSenha = (EditText) findViewById(R.id.editSenha);

        editLogin.setText("");
        editSenha.setText("");
    }

    public void entrarClicado(View view){

        EditText login = (EditText)findViewById(R.id.editLogin);
        EditText senha = (EditText)findViewById(R.id.editSenha);

        Intent intent = new Intent(this,TelaPrincipal.class);

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Usuario usuario = usuarioDAO.getUsuario(login.getText().toString(), senha.getText().toString());

        if(usuario != null){
            intent.putExtra("login",usuario.getLogin());
            Toast.makeText(this,"Bem-Vindo "+usuario.getNome()+"!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            senha.setText("");
        }
    }

    public void cadastrarClicado(View view){
        Intent intent = new Intent(this,TelaCadastro.class);
        startActivity(intent);
    }

}
