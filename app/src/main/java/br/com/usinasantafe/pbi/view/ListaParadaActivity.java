package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;
import br.com.usinasantafe.pbi.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.util.VerifDadosServ;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView paradaListView;
    private ProgressDialog progressBar;
    private ArrayAdapter<String> stringArrayAdapter;
    private PBIContext pbiContext;
    private String textParada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = (EditText) findViewById(R.id.editPesqListParada);

        pbiContext = (PBIContext) getApplication();

        List<ParadaBean> paradaList = pbiContext.getMecanicoCTR().paradaList();

        String itens[] = new String[paradaList.size()];

        int i = 0;
        for (ParadaBean paradaBean : paradaList) {
            itens[i] = paradaBean.getCodParada() + " - " + paradaBean.getDescrParada();
            i++;
        }

        stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        paradaListView = (ListView) findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(stringArrayAdapter);

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                textParada = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + textParada + "' ?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pbiContext.getMecanicoCTR().setApontIndBean(new ApontIndBean());
                        pbiContext.getMecanicoCTR().getApontIndBean().setOsApont(0L);
                        pbiContext.getMecanicoCTR().getApontIndBean().setItemOSApont(0L);
                        pbiContext.getMecanicoCTR().getApontIndBean().setParadaApont(pbiContext.getMecanicoCTR().getParadaCod(Long.parseLong(textParada.substring(0, textParada.indexOf('-')).trim())).getIdParada());
                        pbiContext.getMecanicoCTR().getApontIndBean().setRealizApont(1L);
                        pbiContext.getMecanicoCTR().salvarApont();

                        if(pbiContext.getVerTela() == 2){
                            pbiContext.getMecanicoCTR().fecharBoletim();
                        }

                        Intent it = new Intent(  ListaParadaActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    }

                });


                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });

                alerta.show();

            }

        });

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                ListaParadaActivity.this.stringArrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Paradas...");
                    progressBar.show();

                    VerifDadosServ.getInstance().verDados("", "Parada"
                            , ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
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

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaParadaActivity.this, MenuFuncaoActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}