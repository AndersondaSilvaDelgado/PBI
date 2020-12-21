package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class DigProdActivity extends ActivityGeneric {

    private PBIContext pbiContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_prod);

        pbiContext = (PBIContext) getApplication();

        Button buttonOkFunc = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancFunc = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  DigProdActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(DigProdActivity.this)) {

                            progressBar = new ProgressDialog(DigProdActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Produto...");
                            progressBar.show();

                            pbiContext.getMecanicoCTR().atualDadosColab(DigProdActivity.this
                                    , LeitorFuncActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( DigProdActivity.this);
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

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonOkFunc.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (pbiContext.getReqProdutoCTR().verProduto(editTextPadrao.getText().toString())) {

                        pbiContext.getReqProdutoCTR().setItemReqProduto(pbiContext.getReqProdutoCTR().getProduto(editTextPadrao.getText().toString()).getIdProduto());

                        if(pbiContext.getReqProdutoCTR().verQtdeEmbProd()){

                            pbiContext.getReqProdutoCTR().setIdEmbProdReqProduto();

                            if(pbiContext.getReqProdutoCTR().verQtdeLocalProd()){

                                pbiContext.getReqProdutoCTR().setIdLocalProdReqProduto();

                                Intent it = new Intent(DigProdActivity.this, QtdeProdActivity.class);
                                startActivity(it);
                                finish();

                            }
                            else{

                                Intent it = new Intent(DigProdActivity.this, ListaLocalActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }
                        else{

                            Intent it = new Intent(DigProdActivity.this, ListaEmbalagemActivity.class);
                            startActivity(it);
                            finish();

                        }

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(DigProdActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("CÓDIGO PRODUTO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }

            }

        });

        buttonCancFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(DigProdActivity.this, LeitorProdActivity.class);
        startActivity(it);
        finish();
    }

}