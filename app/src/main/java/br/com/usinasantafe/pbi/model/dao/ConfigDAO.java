package br.com.usinasantafe.pbi.model.dao;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public void insert(Long idEquip, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.setAparelhoConfig(idEquip);
        configBean.setSenhaConfig(senha);
        configBean.setMatricFuncConfig(0L);
        configBean.insert();
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean verConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.get("senhaConfig", senha);
        boolean ret = (configList.size() > 0);
        configList.clear();
        return ret;
    }

    public void matricFuncConfig(Long matricFunc){
        ConfigBean configBean = getConfig();
        configBean.setMatricFuncConfig(matricFunc);
        configBean.update();
    }

}
