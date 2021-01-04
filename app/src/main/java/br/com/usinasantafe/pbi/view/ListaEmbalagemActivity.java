package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class ListaEmbalagemActivity extends ActivityGeneric {

    private ListView embalagemListView;
    private List<EmbalProdBean> embalProdList;
    private PBIContext pbiContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_embalagem);

        pbiContext = (PBIContext) getApplication();

        Button buttonRetEmbalagem = (Button) findViewById(R.id.buttonRetEmbalagem);
        Button buttonAtualEmbalagem = (Button) findViewById(R.id.buttonAtualEmbalagem);

        embalProdList = pbiContext.getReqProdutoCTR().embalProdList();
        ArrayList<String> itens = new ArrayList<String>();

        for(EmbalProdBean embalProdBean : embalProdList){
            itens.add(pbiContext.getReqProdutoCTR().getEmbalagem(embalProdBean.getIdEmbal()).getSgEmbalagem());
        }

        buttonAtualEmbalagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaEmbalagemActivity.this)) {

                    progressBar = new ProgressDialog(ListaEmbalagemActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pbiContext.getReqProdutoCTR().atualDadosEmbalagem(ListaEmbalagemActivity.this, ListaEmbalagemActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEmbalagemActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }
            }
        });

        AdapterList adapterList = new AdapterList(this, itens);
        embalagemListView = (ListView) findViewById(R.id.listaEmbalagem);
        embalagemListView.setAdapter(adapterList);

        embalagemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                EmbalProdBean embalProdBean = embalProdList.get(position);
                pbiContext.getReqProdutoCTR().getItemReqProdBean().setIdEmbItemReqProd(embalProdBean.getIdEmbalProd());

                if(pbiContext.getReqProdutoCTR().verQtdeLocalProd()){

                    pbiContext.getReqProdutoCTR().setIdLocalProdReqProduto();

                    Intent it = new Intent(ListaEmbalagemActivity.this, QtdeProdActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    Intent it = new Intent(ListaEmbalagemActivity.this, ListaLocalActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonRetEmbalagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaEmbalagemActivity.this, LeitorProdActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}