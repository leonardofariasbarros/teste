package com.example.leo.superdupermart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {

    EditText cpf,rg,cep,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        cpf = (EditText)findViewById(R.id.editTextCPF);
        rg  = (EditText)findViewById(R.id.editTextRG);
        cep = (EditText)findViewById(R.id.editTextCEP);
        tel = (EditText)findViewById(R.id.editTextTelefone);

        cpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, cpf));
        rg.addTextChangedListener(Mask.insert(Mask.RG_MASK,rg));
        cep.addTextChangedListener(Mask.insert(Mask.CEP_MASK,cep));
        tel.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK,tel));

    }

    public void cancelarClicado(View v){
        Intent intent = new Intent(this,TelaInicial.class);
        startActivity(intent);
    }

    public void enviarClicado(View v){
        EditText nome = (EditText)findViewById(R.id.editTextNome);
        EditText endereco = (EditText)findViewById(R.id.editTextEndereco);
        EditText login = (EditText)findViewById(R.id.editTextLogin);
        EditText senha = (EditText)findViewById(R.id.editTextSenha);
        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText rg = (EditText)findViewById(R.id.editTextRG);
        EditText cpf = (EditText)findViewById(R.id.editTextCPF);
        EditText telefone = (EditText)findViewById(R.id.editTextTelefone);
        EditText cep = (EditText)findViewById(R.id.editTextCEP);

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);

        if(nome.getText().toString().trim().equals("")  || endereco.getText().toString().trim().equals("") ||
           login.getText().toString().trim().equals("") || senha.getText().toString().trim().equals("")    ||
           email.getText().toString().trim().equals("") || rg.getText().toString().length() != 9           ||
           cpf.getText().toString().length() !=14       || telefone.getText().toString().length()!= 15     ||
           cep.getText().toString().length()!=9){
            Toast.makeText(getBaseContext(),"Preencha todos os dados!",Toast.LENGTH_LONG).show();
        }else {
            if (usuarioDAO.getLogin(login.getText().toString()) != null) {
                Toast.makeText(getBaseContext(), "Usuário já existe!", Toast.LENGTH_LONG).show();
            } else {

                Usuario usuario = new Usuario(login.getText().toString(), nome.getText().toString(),
                        endereco.getText().toString(), senha.getText().toString(), email.getText().toString(),
                        rg.getText().toString(), cpf.getText().toString(), cep.getText().toString(),
                        telefone.getText().toString());

                UsuarioDAO usuarioDAO2 = new UsuarioDAO(this);

                if (usuarioDAO2.addUsuario(usuario)) {
                    Intent intent = new Intent(this, TelaInicial.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Usuário Adicionado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Ocorreu algum erro!", Toast.LENGTH_LONG).show();
                }

                finish();
            }
        }

    }
}
