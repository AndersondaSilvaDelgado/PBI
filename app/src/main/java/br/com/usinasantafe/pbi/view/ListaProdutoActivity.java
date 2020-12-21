package br.com.usinasantafe.pbi.view;

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

    private List<ItemReqProdBean> prodList;
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
        Button buttonRetMenuProd = (Button) findViewById(R.id.buttonFinalizarReq);

        pbiContext = (PBIContext) getApplication();

        CabecReqProdBean cabecReqProdBean = pbiContext.getReqProdutoCTR().getCabecReqProdAberto();
        textViewFunc.setText(pbiContext.getReqProdutoCTR().getColab(cabecReqProdBean.getIdFuncCabecReqProd()).getMatricColab() + " - " + pbiContext.getReqProdutoCTR().getColab(cabecReqProdBean.getIdFuncCabecReqProd()).getNomeColab());
        textViewNroOS.setText("NRO OS: " +  cabecReqProdBean.getNroOSCabecReqProd());
        textViewItemOS.setText("ITEM OS:"  + cabecReqProdBean.getItemOSCabecReqProd());

        prodList = pbiContext.getReqProdutoCTR().itemReqProdList();
        ArrayList<String> itens = new ArrayList<String>();

        for(ItemReqProdBean itemReqProdBean : prodList){
            itens.add(String.valueOf(itemReqProdBean.getIdProdItemReqProd()));
        }

        AdapterList adapterList = new AdapterList(this, itens);
        prodListView = (ListView) findViewById(R.id.listViewProduto);
        prodListView.setAdapter(adapterList);

        prodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

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

//                Intent it = new Intent(ListaProdutoActivity.this, MenuFuncaoActivity.class);
//                startActivity(it);
//                finish();

            }
        });

        buttonRetMenuProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Intent it = new Intent(ListaProdutoActivity.this, MenuInicialActivity.class);
//                startActivity(it);
//                finish();

            }
        });

    }
}