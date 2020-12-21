package br.com.usinasantafe.pbi.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pbi.util.Tempo;

public class ApontIndDAO {

    public ApontIndDAO() {
    }

    public void salvarApont(ApontIndBean apontIndBean, String horarioEnt, BoletimIndBean boletimIndBean){

        List<ApontIndBean> apontList = apontList(boletimIndBean.getIdBoletim());

        if(apontIndBean.getParadaApont() > 0) {

            if(apontList.size() > 0) {
                ApontIndBean apontIndBeanBD = apontList.get(0);
                apontIndBean.setDthrInicialApont(apontIndBeanBD.getDthrFinalApont());
            }
            else{
                apontIndBean.setDthrInicialApont(Tempo.getInstance().manipDHSemTZ(Tempo.getInstance().dataSHoraSemTZ() + " " + horarioEnt));
            }

            apontIndBean.setDthrFinalApont(Tempo.getInstance().dataHora());

        }
        else {

            if(apontList.size() > 0) {

                ApontIndBean apontIndBeanBD = apontList.get(0);
                apontIndBeanBD.setDthrFinalApont(Tempo.getInstance().dataHora());
                apontIndBeanBD.setStatusApont(0L);
                apontIndBeanBD.update();

                apontIndBean.setDthrInicialApont(Tempo.getInstance().dataHora());

            }
            else{

                apontIndBean.setDthrInicialApont(Tempo.getInstance().manipDHSemTZ(Tempo.getInstance().dataSHoraSemTZ() + " " + horarioEnt));

            }

            apontIndBean.setDthrFinalApont("");

        }

        apontList.clear();
        apontIndBean.setIdBolApont(boletimIndBean.getIdBoletim());
        apontIndBean.setIdExtBolApont(boletimIndBean.getIdExtBoletim());
        apontIndBean.setStatusApont(0L);
        apontIndBean.insert();

    }

    public void updApont(ApontIndBean apontIndBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdApont(apontIndBean.getIdApont()));

        List<ApontIndBean> apontList = apontIndBean.get(pesqArrayList);

        ApontIndBean apontIndBeanBD = apontList.get(0);
        apontIndBeanBD.setStatusApont(1L);
        apontIndBeanBD.update();

        apontList.clear();

    }

    public void updApontIdExtBoletim(Long idBol, Long idExtBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));

        ApontIndBean apontIndBean = new ApontIndBean();
        List<ApontIndBean> apontList = apontIndBean.get(pesqArrayList);

        for(ApontIndBean apontIndBeanBD : apontList){
            apontIndBeanBD.setIdExtBolApont(idExtBol);
            apontIndBeanBD.update();
        }

        apontList.clear();

    }

    public void updApontEnviado(Long idApont, Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdApont(idApont));
        pesqArrayList.add(getPesqIdBol(idBol));

        ApontIndBean apontIndBean = new ApontIndBean();
        List<ApontIndBean> apontList = apontIndBean.get(pesqArrayList);

        for(ApontIndBean apontIndBeanBD : apontList){
            apontIndBeanBD.setStatusApont(1L);
            apontIndBeanBD.update();
        }

        apontList.clear();

    }

    public void delApont(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));

        ApontIndBean apontIndBean = new ApontIndBean();
        List<ApontIndBean> apontList = apontIndBean.get(pesqArrayList);

        for(ApontIndBean apontIndBeanBD : apontList){
            apontIndBeanBD.delete();
        }

        apontList.clear();

    }

    public boolean verApontSemEnvio(){
        List<ApontIndBean> apontList = apontSemEnvioList();
        boolean ret = (apontList.size() > 0);
        apontList.clear();
        return ret;
    }

    public boolean verApont(Long idBol){
        List<ApontIndBean> apontList = apontList(idBol);
        boolean ret = (apontList.size() > 0);
        apontList.clear();
        return ret;
    }

    public List<ApontIndBean> apontList(Long idBol){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));
        ApontIndBean apontIndBean = new ApontIndBean();
        return apontIndBean.getAndOrderBy(pesqArrayList, "idApont", false);
    }

    public List<ApontIndBean> apontList(ArrayList<Long> idBolArrayList){
        ApontIndBean apontIndBean = new ApontIndBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqSemEnvio());
        return apontIndBean.inAndGet("idBolApont", idBolArrayList, pesqArrayList);
    }

    public List<ApontIndBean> apontSemEnvioList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqSemEnvio());
        ApontIndBean apontIndBean = new ApontIndBean();
        return apontIndBean.get(pesqArrayList);
    }

    public ArrayList<Long> idBolAbertoList(){

        List<ApontIndBean> apontSemEnvioList = apontSemEnvioList();
        ArrayList<Long> idBolAbertoSemEnvioList = new ArrayList<>();
        for (ApontIndBean apontIndBean : apontSemEnvioList) {
            idBolAbertoSemEnvioList.add(apontIndBean.getIdBolApont());
        }
        apontSemEnvioList.clear();
        return idBolAbertoSemEnvioList;

    }

    public void finalizarApont(ApontIndBean apontIndBean){
        apontIndBean.setDthrFinalApont(Tempo.getInstance().dataHora());
        apontIndBean.setRealizApont(1L);
        apontIndBean.setStatusApont(0L);
        apontIndBean.update();
    }

    public void interroperApont(ApontIndBean apontIndBean){
        apontIndBean.setDthrFinalApont(Tempo.getInstance().dataHora());
        apontIndBean.setStatusApont(0L);
        apontIndBean.update();
    }

    public boolean verOSApont(Long idBol, Long nroOS){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));
        pesqArrayList.add(getPesqOSApont(nroOS));

        ApontIndBean apontIndBean = new ApontIndBean();
        List<ApontIndBean> apontList = apontIndBean.get(pesqArrayList);
        boolean ret = (apontList.size() > 0);
        apontList.clear();

        return ret;

    }

    public void fecharApont(ApontIndBean apontIndBean){
        if(apontIndBean.getParadaApont() == 0L){
            apontIndBean.setDthrFinalApont(Tempo.getInstance().dataHora());
            apontIndBean.setStatusApont(0L);
            apontIndBean.update();
        }
    }

    public void fecharApont(ApontIndBean apontIndBean, String dthrFinal){
        if(apontIndBean.getParadaApont() == 0L){
            apontIndBean.setDthrFinalApont(dthrFinal);
            apontIndBean.setStatusApont(0L);
            apontIndBean.update();
        }
    }

    public String dadosEnvioApont(ArrayList<Long> idBolArrayList){

        List<ApontIndBean> apontList = apontList(idBolArrayList);
        JsonArray jsonArrayApont = new JsonArray();

        for (ApontIndBean apontIndBean : apontList) {

            Gson gson = new Gson();
            jsonArrayApont.add(gson.toJsonTree(apontIndBean, apontIndBean.getClass()));

        }

        apontList.clear();

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("aponta", jsonArrayApont);

        return jsonApont.toString();

    }

    private EspecificaPesquisa getPesqIdApont(Long idApont){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idApont");
        pesquisa.setValor(idApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolApont");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqOSApont(Long nroOS){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("osApont");
        pesquisa.setValor(nroOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSemEnvio(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApont");
        pesquisa.setValor(0L);
        pesquisa.setTipo(1);
        return pesquisa;
    }


}
