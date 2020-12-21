package br.com.usinasantafe.pbi.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pbi.PBIContext;
import br.com.usinasantafe.pbi.R;

public class ItemOSDigActivity extends ActivityGeneric {

    private PBIContext pbiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_os_dig);

        Button buttonOkItemOSDig = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancItemOSDig = (Button) findViewById(R.id.buttonCancPadrao);

        pbiContext = (PBIContext) getApplication();

        buttonOkItemOSDig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (Long.parseLong(editTextPadrao.getText().toString()) < 1000) {

                        if(pbiContext.getVerTela() == 3) {

                            pbiContext.getMecanicoCTR().getApontIndBean().setItemOSApont(Long.parseLong(editTextPadrao.getText().toString()));
                            pbiContext.getMecanicoCTR().salvarApont();

                            Intent it = new Intent(ItemOSDigActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if(pbiContext.getVerTela() == 4) {

                            pbiContext.setVerTela(6);
                            pbiContext.getReqProdutoCTR().salvarCabecReqProd(Long.parseLong(editTextPadrao.getText().toString()));

                            Intent it = new Intent(ItemOSDigActivity.this, ListaProdutoActivity.class);
                            startActivity(it);
                            finish();

                        }

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ItemOSDigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR ACIMA DO QUE O PERMITIDO. POR FAVOR, VERIFIQUE O VALOR!");
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

        buttonCancItemOSDig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(ItemOSDigActivity.this, OSActivity.class);
        startActivity(it);
        finish();
    }

}