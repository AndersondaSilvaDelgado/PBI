package br.com.usinasantafe.pbi.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pbi.util.Tempo;

public class BoletimIndDAO {

    public BoletimIndDAO() {
    }

    public void atualBoletimSApont(){
        BoletimIndBean boletimIndBean = new BoletimIndBean();
        List boletimList = boletimIndBean.all();
        for (int i = 0; i < boletimList.size(); i++) {
            boletimIndBean = (BoletimIndBean) boletimList.get(i);
            boletimIndBean.setApontBoletim(0L);
            boletimIndBean.update();
        }
        boletimList.clear();
    }

    public void atualSalvarBoletim(Long idEquip, Long idColab, String horarioEntr){

        List<BoletimIndBean> boletimList = boletimList(idColab);

        if(boletimList.size() == 0){
            BoletimIndBean boletimIndBean = new BoletimIndBean();
            boletimIndBean.setDthrInicialBoletim(Tempo.getInstance().manipDHSemTZ(Tempo.getInstance().dataSHoraComTZ() + " " + horarioEntr));
            boletimIndBean.setNroAparelhoBoletim(idEquip);
            boletimIndBean.setIdFuncBoletim(idColab);
            boletimIndBean.setIdExtBoletim(0L);
            boletimIndBean.setStatusBoletim(1L);
            boletimIndBean.setApontBoletim(1L);
            boletimIndBean.insert();
        }
        else{
            BoletimIndBean boletimIndBean = boletimList.get(0);
            boletimIndBean.setApontBoletim(1L);
            boletimIndBean.update();
        }

    }

    public void atualIdExtBol(BoletimIndBean boletimIndBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBoletim(boletimIndBean.getIdBoletim()));

        BoletimIndBean boletimIndBeanBD = new BoletimIndBean();
        List<BoletimIndBean> boletimList = boletimIndBeanBD.get(pesqArrayList);

        boletimIndBeanBD = boletimList.get(0);
        boletimIndBeanBD.setIdExtBoletim(boletimIndBean.getIdExtBoletim());
        boletimIndBeanBD.update();

    }

    public void delBoletim(BoletimIndBean boletimIndBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBoletim(boletimIndBean.getIdBoletim()));

        BoletimIndBean boletimIndBeanBD = new BoletimIndBean();
        List<BoletimIndBean> boletimList = boletimIndBeanBD.get(pesqArrayList);

        boletimIndBeanBD = boletimList.get(0);
        boletimIndBeanBD.delete();

    }

    public void fecharBoletim(BoletimIndBean boletimIndBean){
        boletimIndBean.setDthrFinalBoletim(Tempo.getInstance().dataHora());
        boletimIndBean.setStatusBoletim(2L);
        boletimIndBean.setStatusFechBoletim(1L);
        boletimIndBean.update();
    }

    public void fecharBoletim(BoletimIndBean boletimIndBean, String dthrFinal){
        boletimIndBean.setDthrFinalBoletim(dthrFinal);
        boletimIndBean.setStatusBoletim(2L);
        boletimIndBean.setStatusFechBoletim(0L);
        boletimIndBean.update();
    }

    public boolean verBoletimFechado(){
        List<BoletimIndBean> boletimList = boletimFechadoList();
        boolean ret = (boletimList.size() > 0);
        boletimList.clear();
        return ret;
    }

    public List<BoletimIndBean> boletimSemEnvioList(ArrayList<Long> idBolAbertoList){
        BoletimIndBean boletimIndBean = new BoletimIndBean();
        return boletimIndBean.in("idBoletim", idBolAbertoList);
    }

    public List<BoletimIndBean> boletimFechadoList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());

        BoletimIndBean boletimIndBean = new BoletimIndBean();
        return boletimIndBean.get(pesqArrayList);

    }

    public List<BoletimIndBean> boletimList(Long idFunc){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdFunc(idFunc));
        pesqArrayList.add(getPesqStatusAberto());

        BoletimIndBean boletimIndBean = new BoletimIndBean();
        return boletimIndBean.get(pesqArrayList);

    }

    public BoletimIndBean getBoletimApont(){
        List<BoletimIndBean> boletimList = boletimApontList();
        BoletimIndBean boletimIndBean = boletimList.get(0);
        boletimList.clear();
        return boletimIndBean;
    }

    public List<BoletimIndBean> boletimApontList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqApont());

        BoletimIndBean boletimIndBean = new BoletimIndBean();
        return boletimIndBean.get(pesqArrayList);

    }

    public ArrayList<Long> idBolFechadoList(){

        List<BoletimIndBean> bolFechadoList = boletimFechadoList();
        ArrayList<Long> idBolFechadoList = new ArrayList<>();
        for (BoletimIndBean boletimIndBean : bolFechadoList) {
            idBolFechadoList.add(boletimIndBean.getIdBoletim());
        }
        bolFechadoList.clear();
        return idBolFechadoList;

    }

    public String dadosBolFechado(){

        List<BoletimIndBean> bolFechadoList = boletimFechadoList();
        JsonArray jsonArrayBolFechado = new JsonArray();

        for (BoletimIndBean boletimIndBean : bolFechadoList) {

            Gson gson = new Gson();
            jsonArrayBolFechado.add(gson.toJsonTree(boletimIndBean, boletimIndBean.getClass()));

        }

        bolFechadoList.clear();

        JsonObject jsonBolFechado = new JsonObject();
        jsonBolFechado.add("boletim", jsonArrayBolFechado);

        return jsonBolFechado.toString();

    }

    public List<BoletimIndBean> boletimAllList(){

        BoletimIndBean boletimIndBean = new BoletimIndBean();
        return boletimIndBean.all();

    }

    public String dadosBolAbertoSemEnvio(ArrayList<Long> idBolAbertoList){

        List<BoletimIndBean> bolFechadoList = boletimSemEnvioList(idBolAbertoList);
        JsonArray jsonArrayBolFechado = new JsonArray();

        for (BoletimIndBean boletimIndBean : bolFechadoList) {

            Gson gson = new Gson();
            jsonArrayBolFechado.add(gson.toJsonTree(boletimIndBean, boletimIndBean.getClass()));

        }

        bolFechadoList.clear();

        JsonObject jsonBolFechado = new JsonObject();
        jsonBolFechado.add("boletim", jsonArrayBolFechado);

        return jsonBolFechado.toString();

    }

    private EspecificaPesquisa getPesqIdBoletim(Long idBoletim){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBoletim");
        pesquisa.setValor(idBoletim);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdFunc(Long idFunc){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idFuncBoletim");
        pesquisa.setValor(idFunc);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("apontBoletim");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
