package br.com.usinasantafe.pbi;

import android.app.Application;

import br.com.usinasantafe.pbi.control.ConfigCTR;
import br.com.usinasantafe.pbi.control.MecanicoCTR;

public class PBIContext extends Application {

    public static String versaoAplic = "1.00";
    private ConfigCTR configCTR;
    private MecanicoCTR mecanicoCTR;
    private int verTela; //1 - Parada Normal; 2 - Parada para Fechar Boletim; 3 - Calibragem de Pneu; 4 - Troca de Pneu;

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

    public int getVerTela() {
        return verTela;
    }

    public void setVerTela(int verTela) {
        this.verTela = verTela;
    }
}
