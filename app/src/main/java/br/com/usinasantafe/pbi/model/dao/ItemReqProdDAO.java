package br.com.usinasantafe.pbi.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;
import br.com.usinasantafe.pbi.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pbi.util.Tempo;

public class ItemReqProdDAO {

    public ItemReqProdDAO() {
    }

    public void salvarItemReqProd(ItemReqProdBean itemReqProdBean){
        itemReqProdBean.setDthrItemReqProd(Tempo.getInstance().dataHora());
        itemReqProdBean.insert();
    }

    public void delItemReqProd(ItemReqProdBean itemReqProdBean){
        itemReqProdBean.delete();
    }

    public String dadosEnvioItem(ArrayList<Long> idCabecArrayList){

        List<ItemReqProdBean> itemReqProdList = itemReqProdList(idCabecArrayList);
        JsonArray jsonArrayApont = new JsonArray();

        for (ItemReqProdBean itemReqProdBean : itemReqProdList) {

            Gson gson = new Gson();
            jsonArrayApont.add(gson.toJsonTree(itemReqProdBean, itemReqProdBean.getClass()));

        }

        itemReqProdList.clear();

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("item", jsonArrayApont);

        return jsonApont.toString();

    }

    public void delItem(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        ItemReqProdBean itemReqProdBean = new ItemReqProdBean();
        List<ItemReqProdBean> itemReqProdList = itemReqProdBean.get(pesqArrayList);

        for(ItemReqProdBean itemReqProdBeanBD : itemReqProdList){
            itemReqProdBeanBD.delete();
        }

        itemReqProdList.clear();

    }

    public List<ItemReqProdBean> itemReqProdList(ArrayList<Long> idCabecArrayList){
        ItemReqProdBean itemReqProdBean = new ItemReqProdBean();
        return itemReqProdBean.in("idCabecItemReqProd", idCabecArrayList);
    }

    public List<ItemReqProdBean> itemReqProdList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        ItemReqProdBean itemReqProdBean = new ItemReqProdBean();
        return itemReqProdBean.getAndOrderBy(pesqArrayList, "idItemReqProd", true);
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecItemReqProd");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
