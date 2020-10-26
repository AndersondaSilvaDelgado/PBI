package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;
import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pbi.util.ConexaoWeb;

public class LeitorProdActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PBIContext pbiContext;
    private TextView txtRetProd;
    private ProgressDialog progressBar;
    private ProdutoBean produtoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_prod);

        pbiContext = (PBIContext) getApplication();

        txtRetProd = (TextView) findViewById(R.id.txtRetProd);
        Button buttonOkProd = (Button) findViewById(R.id.buttonOkProd);
        Button buttonCancProd = (Button) findViewById(R.id.buttonCancProd);
        Button buttonDigProd = (Button) findViewById(R.id.buttonDigProd);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptProd = (Button) findViewById(R.id.buttonCaptProd);

        produtoBean = new ProdutoBean();

        produtoBean.setIdProduto(0L);
        produtoBean.setCodProduto("");
        produtoBean.setDescrProduto("");

        txtRetProd.setText("Por Favor, realize a leitura do Código de Barra de Produto.");

        buttonOkProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (produtoBean.getIdProduto() > 0) {

                    Intent it = new Intent(LeitorProdActivity.this, QtdeProdActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pbiContext.getVerTela() == 5){
                    pbiContext.setVerTela(4);
                    Intent it = new Intent(LeitorProdActivity.this, ItemOSListaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pbiContext.getVerTela() == 6){
                    pbiContext.setVerTela(4);
                    Intent it = new Intent(LeitorProdActivity.this, ItemOSDigActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

        buttonDigProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorProdActivity.this, DigProdActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorProdActivity.this, br.com.usinasantafe.pbi.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorProdActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(LeitorProdActivity.this)) {

                            progressBar = new ProgressDialog(LeitorProdActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Produto...");
                            progressBar.show();

                            pbiContext.getMecanicoCTR().atualDadosColab(LeitorProdActivity.this
                                    , LeitorProdActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorProdActivity.this);
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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            String produto = data.getStringExtra("SCAN_RESULT");
            if (produto.length() == 8) {
                produto = produto.substring(0, 7);
                if (pbiContext.getReqProdutoCTR().verProduto(produto)) {
                    produtoBean = pbiContext.getReqProdutoCTR().getProduto(produto);
                    txtRetProd.setText(produto + "\n" + produtoBean.getDescrProduto());
                } else {
                    txtRetProd.setText("Produto Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
    }

}