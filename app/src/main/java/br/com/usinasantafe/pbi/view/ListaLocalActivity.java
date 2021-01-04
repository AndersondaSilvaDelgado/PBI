package br.com.usinasantafe.pbi.view;

import androidx.appcompat.app.AppCompatActivity;

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
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalProdBean;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class ListaLocalActivity extends ActivityGeneric {

    private ListView localListView;
    private List<LocalProdBean> localList;
    private PBIContext pbiContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local);

        pbiContext = (PBIContext) getApplication();

        Button buttonRetLocal = (Button) findViewById(R.id.buttonRetLocal);
        Button buttonAtualLocal = (Button) findViewById(R.id.buttonAtualLocal);

        localList = pbiContext.getReqProdutoCTR().localProdList();
        ArrayList<String> itens = new ArrayList<String>();

        for(LocalProdBean localProdBean : localList){
            itens.add(pbiContext.getReqProdutoCTR().getLocal(localProdBean.getIdLocal()).getDescrLocal());
        }

        buttonAtualLocal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaLocalActivity.this)) {

                    progressBar = new ProgressDialog(ListaLocalActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pbiContext.getReqProdutoCTR().atualDadosLocal(ListaLocalActivity.this, ListaLocalActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaLocalActivity.this);
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
        localListView = (ListView) findViewById(R.id.listaLocal);
        localListView.setAdapter(adapterList);

        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LocalProdBean localProdBean = localList.get(position);
                pbiContext.getReqProdutoCTR().getItemReqProdBean().setIdLocalItemReqProd(localProdBean.getIdLocalProd());

                Intent it = new Intent(ListaLocalActivity.this, QtdeProdActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pbiContext.getReqProdutoCTR().verQtdeEmbProd()) {

                    Intent it = new Intent(ListaLocalActivity.this, LeitorProdActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    Intent it = new Intent(ListaLocalActivity.this, ListaEmbalagemActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

    }

}