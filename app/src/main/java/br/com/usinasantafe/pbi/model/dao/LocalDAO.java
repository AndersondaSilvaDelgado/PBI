package br.com.usinasantafe.pbi.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalagemBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalProdBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;

public class LocalDAO {

    public LocalDAO() {
    }

    public boolean verQtdeLocalProd(Long idEmbProd){
        List<LocalProdBean> localProdList = localProdList(idEmbProd);
        boolean ret = true;
        if(localProdList.size() > 1){
            ret = false;
        }
        localProdList.clear();
        return ret;
    }

    public LocalProdBean getLocalProd(Long idEmbProd){
        List<LocalProdBean> localProdList = localProdList(idEmbProd);
        LocalProdBean localProdBean = localProdList.get(0);
        localProdList.clear();
        return localProdBean;
    }

    public List<LocalProdBean> localProdList(Long idEmbProd){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEmbProd(idEmbProd));
        LocalProdBean localProdBean = new LocalProdBean();
        return localProdBean.get(pesqArrayList);
    }

    public LocalBean getLocal(Long idLocal){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));
        LocalBean localBean = new LocalBean();
        List<LocalBean> localList = localBean.get(pesqArrayList);
        localBean = localList.get(0);
        localList.clear();
        return localBean;
    }

    private EspecificaPesquisa getPesqIdEmbProd(Long idEmbProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEmbProd");
        pesquisa.setValor(idEmbProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdLocal(Long idLocal){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idLocal");
        pesquisa.setValor(idLocal);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
