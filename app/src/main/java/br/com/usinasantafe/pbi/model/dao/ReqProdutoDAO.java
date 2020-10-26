package br.com.usinasantafe.pbi.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ReqProdutoBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;

public class ReqProdutoDAO {

    public ReqProdutoDAO() {
    }

    public List<ReqProdutoBean> reqProdutoList(){
        ReqProdutoBean reqProdutoBean = new ReqProdutoBean();
        return reqProdutoBean.all();
    }

    public boolean verReqProduto(){
        ReqProdutoBean reqProdutoBean = new ReqProdutoBean();
        return reqProdutoBean.hasElements();
    }

    public void insertReqProduto(ReqProdutoBean reqProdutoBean){
        reqProdutoBean.insert();
    }

    public String dadosReqProduto(){

        List<ReqProdutoBean> reqProdutoList = reqProdutoList();
        JsonArray jsonArrayReqProduto = new JsonArray();

        for (ReqProdutoBean reqProdutoBean : reqProdutoList) {
            Gson gson = new Gson();
            jsonArrayReqProduto.add(gson.toJsonTree(reqProdutoBean, reqProdutoBean.getClass()));
        }

        reqProdutoList.clear();

        JsonObject jsonReqProduto = new JsonObject();
        jsonReqProduto.add("reqprod", jsonArrayReqProduto);

        return jsonReqProduto.toString();

    }

    public void delBoletim(BoletimBean boletimBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBoletim(boletimBean.getIdBoletim()));

        BoletimBean boletimBeanBD = new BoletimBean();
        List<BoletimBean> boletimList = boletimBeanBD.get(pesqArrayList);

        boletimBeanBD = boletimList.get(0);
        boletimBeanBD.delete();

    }

    private EspecificaPesquisa getPesqIdBoletim(Long idBoletim){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBoletim");
        pesquisa.setValor(idBoletim);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
