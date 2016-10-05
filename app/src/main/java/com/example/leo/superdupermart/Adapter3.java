package com.example.leo.superdupermart;

import android.speech.tts.TextToSpeech;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Leo on 13/09/2016.
 */
public class Adapter3 extends BaseAdapter {
    private final List<Carrinho> carrinhos;
    private final Activity act;

    public Adapter3(List<Carrinho> carrinhos, Activity act) {
        this.carrinhos = carrinhos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return carrinhos.size();
    }

    @Override
    public Object getItem(int position) {
        return carrinhos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_meu_historico, parent, false);


        Carrinho carrinho = carrinhos.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.textViewNome);
        TextView qtd = (TextView)
                view.findViewById(R.id.textnumQtd);
        TextView data = (TextView)
                view.findViewById(R.id.textData);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.imgProduto);
        nome.setText(carrinho.getNome());
        data.setText(carrinho.getData());
        qtd.setText(carrinho.getQuantidade());

        imagem.setImageResource(R.drawable.ic_exit_to_app_black_24dp);

        /*
        if(produto.getId()==1){
            imagem.setImageResource(R.drawable.img1);
        }
        else if(produto.getId()==2){
            imagem.setImageResource(R.drawable.img2);
        }
        else if(produto.getId()==3){
            imagem.setImageResource(R.drawable.img3);
        }
        else if(produto.getId()==4){
            imagem.setImageResource(R.drawable.img4);
        }
        else if(produto.getId()==5){
            imagem.setImageResource(R.drawable.img5);
        }
        else if(produto.getId()==6){
            imagem.setImageResource(R.drawable.img6);
        }
        else if(produto.getId()==7){
            imagem.setImageResource(R.drawable.img7);
        }
        else if(produto.getId()==8){
            imagem.setImageResource(R.drawable.img8);
        }
        else if(produto.getId()==9){
            imagem.setImageResource(R.drawable.img9);
        }
        else if(produto.getId()==10){
            imagem.setImageResource(R.drawable.img10);
        }
        else if(produto.getId()==11){
            imagem.setImageResource(R.drawable.img11);
        }
        else if(produto.getId()==12){
            imagem.setImageResource(R.drawable.img12);
        }
        else if(produto.getId()==13){
            imagem.setImageResource(R.drawable.img13);
        }
        else if(produto.getId()==14){
            imagem.setImageResource(R.drawable.img14);
        }
        else if(produto.getId()==15){
            imagem.setImageResource(R.drawable.img15);
        }
        else if(produto.getId()==16){
            imagem.setImageResource(R.drawable.img16);
        }
        else if(produto.getId()==17){
            imagem.setImageResource(R.drawable.img17);
        }
        else if(produto.getId()==18){
            imagem.setImageResource(R.drawable.img18);
        }
        else if(produto.getId()==19){
            imagem.setImageResource(R.drawable.img19);
        }
        else if(produto.getId()==20){
            imagem.setImageResource(R.drawable.img20);
        }
        else if(produto.getId()==21){
            imagem.setImageResource(R.drawable.img21);
        }
        else if(produto.getId()==22){
            imagem.setImageResource(R.drawable.img22);
        }
        else if(produto.getId()==23){
            imagem.setImageResource(R.drawable.img23);
        }
        else if(produto.getId()==24){
            imagem.setImageResource(R.drawable.img24);
        }
        else if(produto.getId()==25){
            imagem.setImageResource(R.drawable.img25);
        }
        else if(produto.getId()==26){
            imagem.setImageResource(R.drawable.img26);
        }
        else if(produto.getId()==27){
            imagem.setImageResource(R.drawable.img27);
        }
        else if(produto.getId()==28){
            imagem.setImageResource(R.drawable.img28);
        }
        else if(produto.getId()==29){
            imagem.setImageResource(R.drawable.img29);
        }
        else if(produto.getId()==30){
            imagem.setImageResource(R.drawable.img30);
        }

        */

        return view;
    }


}
