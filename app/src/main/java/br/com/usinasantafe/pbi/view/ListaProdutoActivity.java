package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;
import br.com.usinasantafe.pbi.model.bean.variaveis.CabecReqProdBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;

public class ListaProdutoActivity extends ActivityGeneric {

    private List<ItemReqProdBean> itemReqProdList;
    private ListView prodListView;
    private PBIContext pbiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        TextView textViewFunc = (TextView) findViewById(R.id.textViewFunc);
        TextView textViewNroOS = (TextView) findViewById(R.id.textViewNroOS);
        TextView textViewItemOS = (TextView) findViewById(R.id.textViewItemOS);
        Button buttonInserirProduto = (Button) findViewById(R.id.buttonInserirProduto);
        Button buttonFinalizarReq = (Button) findViewById(R.id.buttonFinalizarReq);
        Button buttonRetMenuProd = (Button) findViewById(R.id.buttonRetMenuProd);

        pbiContext = (PBIContext) getApplication();

        CabecReqProdBean cabecReqProdBean = pbiContext.getReqProdutoCTR().getCabecReqProdAberto();
        textViewFunc.setText(pbiContext.getReqProdutoCTR().getColab(cabecReqProdBean.getIdFuncCabecReqProd()).getMatricColab() + " - " + pbiContext.getReqProdutoCTR().getColab(cabecReqProdBean.getIdFuncCabecReqProd()).getNomeColab());
        textViewNroOS.setText("NRO OS: " +  cabecReqProdBean.getNroOSCabecReqProd());
        textViewItemOS.setText("ITEM OS:"  + cabecReqProdBean.getItemOSCabecReqProd());

        itemReqProdList = pbiContext.getReqProdutoCTR().itemReqProdList();
        ArrayList<String> itens = new ArrayList<String>();

        for(ItemReqProdBean itemReqProdBean : itemReqProdList){
            String produto = "PRODUTO: " + pbiContext.getReqProdutoCTR().getProduto(itemReqProdBean.getIdProdItemReqProd()).getCodProduto() + " - " + pbiContext.getReqProdutoCTR().getProduto(itemReqProdBean.getIdProdItemReqProd()).getDescrProduto();
            String qtde = "QTDE: " + itemReqProdBean.getQtdeItemReqProd();
            itens.add(produto + "\n" + qtde);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        prodListView = (ListView) findViewById(R.id.listViewProduto);
        prodListView.setAdapter(adapterList);

        prodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, final int position,
                                    long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaProdutoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE EXCLUIR O PRODUTO DA REQUISIÇÃO?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pbiContext.getReqProdutoCTR().delItemReqProd(itemReqProdList.get(position));
                        itemReqProdList.clear();

                        Intent it = new Intent(ListaProdutoActivity.this, ListaProdutoActivity.class);
                        startActivity(it);
                        finish();

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

        buttonInserirProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaProdutoActivity.this, LeitorProdActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonFinalizarReq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pbiContext.getReqProdutoCTR().verQtdeItem()){

                    pbiContext.getReqProdutoCTR().fecharCabecReqProd();

                    Intent it = new Intent(ListaProdutoActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaProdutoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR. INSIRA PRODUTO(S) PARA PODER FINALIZAR A REQUISIÇÃO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alerta.show();
                }

            }
        });

        buttonRetMenuProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaProdutoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE SAIR DA REQUISIÇÃO? ISSO FARÁ QUE A INFORMAÇÃO DA MESMA SEJA APAGADAS.");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pbiContext.getReqProdutoCTR().delCabecAberto();

                        Intent it = new Intent(ListaProdutoActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

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
}