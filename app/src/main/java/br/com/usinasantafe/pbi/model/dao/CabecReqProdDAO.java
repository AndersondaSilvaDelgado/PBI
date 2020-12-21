package br.com.usinasantafe.pbi.model.dao;

import java.util.ArrayList;
import java.util.List;

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

    public CabecReqProdBean getCabecReqProdAberto(){
        return cabecReqProdAbertoList().get(0);
    }

    public List<CabecReqProdBean> cabecReqProdAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusAberto());
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

}
