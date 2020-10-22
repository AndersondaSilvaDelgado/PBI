package br.com.usinasantafe.pbi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;

public class ListaHistoricoActivity extends ActivityGeneric {

    private PBIContext pbiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historico);

        Button buttonRetHistorico = (Button) findViewById(R.id.buttonRetHistorico);

        pbiContext = (PBIContext) getApplication();

        ListView listaHistorico = (ListView) findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pbiContext.getMecanicoCTR().apontList());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaHistoricoActivity.this, MenuFuncaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}