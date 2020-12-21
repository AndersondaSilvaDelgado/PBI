package br.com.usinasantafe.pbi.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalagemBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;

public class EmbalagemDAO {

    public EmbalagemDAO() {
    }

    public boolean verQtdeEmbProd(Long idProd){
        List<EmbalProdBean> embalProdList = embalProdList(idProd);
        boolean ret = true;
        if(embalProdList.size() > 0){
           ret = false;
        }
        embalProdList.clear();
        return ret;
    }

    public EmbalProdBean getEmbProd(Long idProd){
        List<EmbalProdBean> embalProdList = embalProdList(idProd);
        EmbalProdBean embalProdBean = embalProdList.get(0);
        embalProdList.clear();
        return embalProdBean;
    }

    public List<EmbalProdBean> embalProdList(Long idProd){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdProd(idProd));
        EmbalProdBean embalProdBean = new EmbalProdBean();
        return embalProdBean.get(pesqArrayList);
    }

    public EmbalagemBean getEmbalagem(Long idEmbalagem){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEmbalagem(idEmbalagem));
        EmbalagemBean embalagemBean = new EmbalagemBean();
        List<EmbalagemBean> embalagemList = embalagemBean.get(pesqArrayList);
        embalagemBean = embalagemList.get(0);
        embalagemList.clear();
        return embalagemBean;
    }

    private EspecificaPesquisa getPesqIdProd(Long idProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idProd");
        pesquisa.setValor(idProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdEmbalagem(Long idEmbalagem){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEmbalagem");
        pesquisa.setValor(idEmbalagem);
        pesquisa.setTipo(1);
        return pesquisa;
    }


}
