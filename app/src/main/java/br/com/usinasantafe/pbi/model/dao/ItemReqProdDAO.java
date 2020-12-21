package br.com.usinasantafe.pbi.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;

public class ItemReqProdDAO {

    public ItemReqProdDAO() {
    }

    public List<ItemReqProdBean> itemReqProdList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idCabec));
        ItemReqProdBean itemReqProdBean = new ItemReqProdBean();
        return itemReqProdBean.getAndOrderBy(pesqArrayList, "idItemReqProd", true);
    }

    private EspecificaPesquisa getPesqIdBol(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecItemReqProd");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
