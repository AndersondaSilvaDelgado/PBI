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
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class ItemOSListaActivity extends ActivityGeneric {

    private ListView itemOSListView;
    private List<ItemOSBean> itemOSList;
    private PBIContext pbiContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_os_lista);

        pbiContext = (PBIContext) getApplication();

        Button buttonRetItemOS = (Button) findViewById(R.id.buttonRetItemOS);
        Button buttonAtualItemOS = (Button) findViewById(R.id.buttonAtualItemOS);

        itemOSList = pbiContext.getMecanicoCTR().itemOSList();
        ArrayList<String> itens = new ArrayList<String>();

        for(ItemOSBean itemOSBean : itemOSList){

            itens.add(itemOSBean.getSeqItemOS() + " - "
                    + pbiContext.getMecanicoCTR().getServico(itemOSBean.getIdServItemOS()).getDescrServico() + " - "
                    + pbiContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getCodComponente() + " - "
                    + pbiContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getDescrComponente());
        }

        buttonAtualItemOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ItemOSListaActivity.this)) {

                    progressBar = new ProgressDialog(ItemOSListaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pbiContext.getMecanicoCTR().atualDadosItemOS(ItemOSListaActivity.this, ItemOSListaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ItemOSListaActivity.this);
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
        itemOSListView = (ListView) findViewById(R.id.listItemOS);
        itemOSListView.setAdapter(adapterList);

        itemOSListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                ItemOSBean itemOSBean = itemOSList.get(position);

                if(pbiContext.getVerTela() == 3) {

                    pbiContext.getMecanicoCTR().getApontBean().setItemOSApont(itemOSBean.getSeqItemOS());
                    pbiContext.getMecanicoCTR().getApontBean().setParadaApont(0L);
                    pbiContext.getMecanicoCTR().getApontBean().setRealizApont(0L);
                    pbiContext.getMecanicoCTR().salvarApont();

                    Intent it = new Intent(ItemOSListaActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }
                else if(pbiContext.getVerTela() == 4) {

                    pbiContext.setVerTela(5);
                    pbiContext.getReqProdutoCTR().getReqProdutoBean().setItemOSReqProd(itemOSBean.getSeqItemOS());

                    Intent it = new Intent(ItemOSListaActivity.this, LeitorProdActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonRetItemOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ItemOSListaActivity.this, OSActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}