package br.com.usinasantafe.pbi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;

public class OpcaoInterFinalActivity extends ActivityGeneric {

    private ListView opcaoListView;
    private PBIContext pbiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_inter_final);

        ArrayList<String> itens = new ArrayList<String>();

        pbiContext = (PBIContext) getApplication();

        itens.add("FINALIZAR");
        itens.add("INTERROPER");
        itens.add("CANCELAR");

        AdapterList adapterList = new AdapterList(this, itens);
        opcaoListView = (ListView) findViewById(R.id.listViewMenuOpcao);
        opcaoListView.setAdapter(adapterList);

        opcaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FINALIZAR")) {

                    pbiContext.getMecanicoCTR().finalizarApont();

                    Intent it = new Intent(OpcaoInterFinalActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("INTERROPER")) {

                    pbiContext.getMecanicoCTR().interroperApont();

                    Intent it = new Intent(OpcaoInterFinalActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("CANCELAR")) {

                    Intent it = new Intent(OpcaoInterFinalActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

    }
}