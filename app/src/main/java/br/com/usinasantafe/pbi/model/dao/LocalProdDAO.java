package br.com.usinasantafe.pbi.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.LocalProdBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;

public class LocalProdDAO {

    public LocalProdDAO() {
    }

    public boolean verQtdeLocalProd(Long idEmbProd){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEmbProd(idEmbProd));
        LocalProdBean localProdBean = new LocalProdBean();
        List<LocalProdBean> localProdList = localProdBean.get(pesqArrayList);
        boolean ret = true;
        if(localProdList.size() > 0){
            ret = false;
        }
        localProdList.clear();
        return ret;
    }

    public LocalProdBean getLocalProd(Long idEmbProd){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEmbProd(idEmbProd));
        LocalProdBean localProdBean = new LocalProdBean();
        List<LocalProdBean> localProdList = localProdBean.get(pesqArrayList);
        localProdBean = localProdList.get(0);
        localProdList.clear();
        return localProdBean;
    }

    private EspecificaPesquisa getPesqIdEmbProd(Long idEmbProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEmbProd");
        pesquisa.setValor(idEmbProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }
}
