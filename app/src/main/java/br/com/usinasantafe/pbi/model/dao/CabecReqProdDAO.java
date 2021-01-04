package br.com.usinasantafe.pbi.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.CabecReqProdBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pbi.util.Tempo;

public class CabecReqProdDAO {

    public CabecReqProdDAO() {
    }

    public void salvarCabecReqProd(CabecReqProdBean cabecReqProdBean){
        cabecReqProdBean.setDthrInicialCabecReqProd(Tempo.getInstance().dataHora());
        cabecReqProdBean.setStatusCabecReqProd(1L);
        cabecReqProdBean.insert();
    }

    public void fecharCabecReqProd(CabecReqProdBean cabecReqProdBean){
        cabecReqProdBean.setDthrFinalCabecReqProd(Tempo.getInstance().dataHora());
        cabecReqProdBean.setStatusCabecReqProd(2L);
        cabecReqProdBean.update();
    }

    public void delCabec(CabecReqProdBean cabecReqProdBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(cabecReqProdBean.getIdCabecReqProd()));

        CabecReqProdBean cabecReqProdBeanBD = new CabecReqProdBean();
        List<CabecReqProdBean> cabecReqProdList = cabecReqProdBeanBD.get(pesqArrayList);

        cabecReqProdBeanBD = cabecReqProdList.get(0);
        cabecReqProdBeanBD.delete();

    }

    public CabecReqProdBean getCabecReqProdAberto(){
        return cabecReqProdAbertoList().get(0);
    }

    public List<CabecReqProdBean> cabecReqProdAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusAberto());
        CabecReqProdBean cabecReqProdBean = new CabecReqProdBean();
        return cabecReqProdBean.get(pesqArrayList);
    }

    public List<CabecReqProdBean> cabecReqProdFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());
        CabecReqProdBean cabecReqProdBean = new CabecReqProdBean();
        return cabecReqProdBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqStatusAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecReqProd");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecReqProd");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecReqProd");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public ArrayList<Long> idCabecFechadoList(){

        List<CabecReqProdBean> cabecReqProdFechadoList = cabecReqProdFechadoList();
        ArrayList<Long> idCabecFechadoList = new ArrayList<>();
        for (CabecReqProdBean cabecReqProdBean : cabecReqProdFechadoList) {
            idCabecFechadoList.add(cabecReqProdBean.getIdCabecReqProd());
        }
        cabecReqProdFechadoList.clear();
        return idCabecFechadoList;

    }

    public String dadosCabecFechado(){

        List<CabecReqProdBean> cabecReqProdList = cabecReqProdFechadoList();
        JsonArray jsonArrayCabecFechado = new JsonArray();

        for (CabecReqProdBean cabecReqProdBean : cabecReqProdList) {

            Gson gson = new Gson();
            jsonArrayCabecFechado.add(gson.toJsonTree(cabecReqProdBean, cabecReqProdBean.getClass()));

        }

        cabecReqProdList.clear();

        JsonObject jsonCabecFechado = new JsonObject();
        jsonCabecFechado.add("cabec", jsonArrayCabecFechado);

        return jsonCabecFechado.toString();

    }

}
