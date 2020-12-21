package br.com.usinasantafe.pbi.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pbi.model.dao.ColabDAO;
import br.com.usinasantafe.pbi.model.dao.ConfigDAO;
import br.com.usinasantafe.pbi.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public boolean verConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verConfig(senha);
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public ColabBean getColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getMatricColab(getConfig().getMatricFuncConfig());
    }

    public void insertConfig(Long idEquip, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.insert(idEquip, senha);
    }

    public void matricFuncConfig(Long matricFunc){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.matricFuncConfig(matricFunc);
    }

    public void atualTodasTabelas(Context context, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabelas(progressDialog, context);
    }

}
