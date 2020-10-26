package br.com.usinasantafe.pbi;

import android.app.Application;

import br.com.usinasantafe.pbi.control.ConfigCTR;
import br.com.usinasantafe.pbi.control.MecanicoCTR;
import br.com.usinasantafe.pbi.control.ReqProdutoCTR;

public class PBIContext extends Application {

    public static String versaoAplic = "1.00";
    private ConfigCTR configCTR;
    private MecanicoCTR mecanicoCTR;
    private ReqProdutoCTR reqProdutoCTR;
    private int verTela;
    // 1 - Parada Normal;
    // 2 - Parada para Fechar Boletim;
    // 3 - Atividade Normal;
    // 4 - Requisição Produto;
    // 5 - ItemOSLista
    // 6 - ItemOSDig;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ConfigCTR getConfigCTR() {
        if(configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public MecanicoCTR getMecanicoCTR() {
        if(mecanicoCTR == null)
            mecanicoCTR = new MecanicoCTR();
        return mecanicoCTR;
    }

    public ReqProdutoCTR getReqProdutoCTR() {
        if(reqProdutoCTR == null)
            reqProdutoCTR = new ReqProdutoCTR();
        return reqProdutoCTR;
    }

    public int getVerTela() {
        return verTela;
    }

    public void setVerTela(int verTela) {
        this.verTela = verTela;
    }
}
