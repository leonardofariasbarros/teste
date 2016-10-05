package com.example.leo.superdupermart;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leo on 13/09/2016.
 */
public class AdapterProdutos extends BaseAdapter {
    private final List<Produto> produtos;
    private final Activity act;

    public AdapterProdutos(List<Produto> produtos, Activity act) {
        this.produtos = produtos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos   .get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.listaprodutoslayout, parent, false);


        Produto produto = produtos.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.textViewNome);
        TextView descricao = (TextView)
                view.findViewById(R.id.textViewDescricao);
        TextView preco = (TextView)
                view.findViewById(R.id.textViewPreco);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.imgProduto);

        nome.setText(produto.getNome());
        descricao.setText(produto.getDescricao());
        preco.setText(produto.getPreco());

        imagem.setImageResource(R.drawable.carrinho_logo);

        /*
        if(produto.getId().equals("1")){
            imagem.setImageResource(R.drawable.img1);
        }
        else if(produto.getId().equals("2")){
            imagem.setImageResource(R.drawable.img2);
        }
        else if(produto.getId().equals("3")){
            imagem.setImageResource(R.drawable.img3);
        }
        else if(produto.getId().equals("4")){
            imagem.setImageResource(R.drawable.img4);
        }
        else if(produto.getId().equals("5")){
            imagem.setImageResource(R.drawable.img5);
        }
        else if(produto.getId().equals("6")){
            imagem.setImageResource(R.drawable.img6);
        }
        else if(produto.getId().equals("7")){
            imagem.setImageResource(R.drawable.img7);
        }
        else if(produto.getId().equals("8")){
            imagem.setImageResource(R.drawable.img8);
        }
        else if(produto.getId().equals("9")){
            imagem.setImageResource(R.drawable.img9);
        }
        else if(produto.getId().equals("10")){
            imagem.setImageResource(R.drawable.img10);
        }
        else if(produto.getId().equals("11")){
            imagem.setImageResource(R.drawable.img11);
        }
        else if(produto.getId().equals("12")){
            imagem.setImageResource(R.drawable.img12);
        }
        else if(produto.getId().equals("13")){
            imagem.setImageResource(R.drawable.img13);
        }
        else if(produto.getId().equals("14")){
            imagem.setImageResource(R.drawable.img14);
        }
        else if(produto.getId().equals("15")){
            imagem.setImageResource(R.drawable.img15);
        }
        else if(produto.getId().equals("16")){
            imagem.setImageResource(R.drawable.img16);
        }
        else if(produto.getId().equals("17")){
            imagem.setImageResource(R.drawable.img17);
        }
        else if(produto.getId().equals("18")){
            imagem.setImageResource(R.drawable.img18);
        }
        else if(produto.getId().equals("19")){
            imagem.setImageResource(R.drawable.img19);
        }
        else if(produto.getId().equals("20")){
            imagem.setImageResource(R.drawable.img20);
        }
        else if(produto.getId().equals("21")){
            imagem.setImageResource(R.drawable.img21);
        }
        else if(produto.getId().equals("22")){
            imagem.setImageResource(R.drawable.img22);
        }
        else if(produto.getId().equals("23")){
            imagem.setImageResource(R.drawable.img23);
        }
        else if(produto.getId().equals("24")){
            imagem.setImageResource(R.drawable.img24);
        }
        else if(produto.getId().equals("25")){
            imagem.setImageResource(R.drawable.img25);
        }
        else if(produto.getId().equals("26")){
            imagem.setImageResource(R.drawable.img26);
        }
        else if(produto.getId().equals("27")){
            imagem.setImageResource(R.drawable.img27);
        }
        else if(produto.getId().equals("28")){
            imagem.setImageResource(R.drawable.img28);
        }
        else if(produto.getId().equals("29")){
            imagem.setImageResource(R.drawable.img29);
        }
        else if(produto.getId().equals("30")){
            imagem.setImageResource(R.drawable.img30);
        }
        else{
            imagem.setImageResource(R.drawable.ic_local_mall_black_24dp);
        }
       */


        return view;
    }


}
