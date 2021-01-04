package br.com.usinasantafe.pbi.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalagemBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.CabecReqProdBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;
import br.com.usinasantafe.pbi.model.dao.CabecReqProdDAO;
import br.com.usinasantafe.pbi.model.dao.ColabDAO;
import br.com.usinasantafe.pbi.model.dao.EmbalagemDAO;
import br.com.usinasantafe.pbi.model.dao.ItemOSDAO;
import br.com.usinasantafe.pbi.model.dao.ItemReqProdDAO;
import br.com.usinasantafe.pbi.model.dao.LocalDAO;
import br.com.usinasantafe.pbi.model.dao.OSDAO;
import br.com.usinasantafe.pbi.model.dao.ProdutoDAO;
import br.com.usinasantafe.pbi.util.AtualDadosServ;
import br.com.usinasantafe.pbi.util.Tempo;

public class ReqProdutoCTR {

    private CabecReqProdBean cabecReqProdBean;
    private ItemReqProdBean itemReqProdBean;

    public ReqProdutoCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean verProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.verProduto(codProduto);
    }

    public boolean verEnvioReqProduto(){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        List<CabecReqProdBean> cabecReqProdList = cabecReqProdDAO.cabecReqProdFechadoList();
        boolean ret = cabecReqProdList.size() > 0;
        cabecReqProdList.clear();
        return ret;
    }

    public boolean verQtdeEmbProd(){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        return embalagemDAO.verQtdeEmbProd(itemReqProdBean.getIdProdItemReqProd());
    }

    public boolean verQtdeLocalProd(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.verQtdeLocalProd(itemReqProdBean.getIdEmbItemReqProd());
    }

    public boolean verQtdeItem(){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        List<ItemReqProdBean> itemReqProdList = itemReqProdDAO.itemReqProdList(cabecReqProdDAO.getCabecReqProdAberto().getIdCabecReqProd());
        boolean ret = itemReqProdList.size() > 0;
        itemReqProdList.clear();
        return ret;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void salvarCabecReqProd(Long itemOS){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        cabecReqProdBean.setItemOSCabecReqProd(itemOS);
        cabecReqProdDAO.salvarCabecReqProd(cabecReqProdBean);
    }

    public void salvarItemReqProd(Double qtdeProd){
        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        itemReqProdBean.setQtdeItemReqProd(qtdeProd);
        itemReqProdDAO.salvarItemReqProd(itemReqProdBean);
    }

    public void fecharCabecReqProd(){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        cabecReqProdDAO.fecharCabecReqProd(cabecReqProdDAO.getCabecReqProdAberto());
    }

    public void delCabecAberto(){

        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();

        CabecReqProdBean cabecReqProdBean = cabecReqProdDAO.getCabecReqProdAberto();
        cabecReqProdDAO.delCabec(cabecReqProdBean);

        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        itemReqProdDAO.delItem(cabecReqProdBean.getIdCabecReqProd());
    }

    public void delCabec(String retorno) {

        try {

            int pos1 = retorno.indexOf("#") + 1;
            String dados = retorno.substring(pos1);

            JSONObject jObj = new JSONObject(dados);
            JSONArray jsonArray = jObj.getJSONArray("cabec");

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();

                    CabecReqProdBean cabecReqProdBean = gson.fromJson(objeto.toString(), CabecReqProdBean.class);

                    CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
                    cabecReqProdDAO.delCabec(cabecReqProdBean);

                    ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
                    itemReqProdDAO.delItem(cabecReqProdBean.getIdCabecReqProd());

                }

            }

        } catch (Exception e) {
            Log.i("PBI", "ERRO = " + e);
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delItemReqProd(ItemReqProdBean itemReqProdBean){
        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        itemReqProdDAO.delItemReqProd(itemReqProdBean);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// GET DADOS /////////////////////////////////////////////

    public CabecReqProdBean getCabecReqProdBean() {
        return cabecReqProdBean;
    }

    public ItemReqProdBean getItemReqProdBean() {
        return itemReqProdBean;
    }

    public ProdutoBean getProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getProduto(codProduto);
    }

    public ProdutoBean getProduto(Long idProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getProduto(idProduto);
    }

    public CabecReqProdBean getCabecReqProdAberto(){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        return cabecReqProdDAO.getCabecReqProdAberto();
    }

    public ColabBean getColab(Long idColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getIdColab(idColab);
    }

    public EmbalagemBean getEmbalagem(Long idEmbalagem){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        return embalagemDAO.getEmbalagem(idEmbalagem);
    }

    public LocalBean getLocal(Long idLocal){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.getLocal(idLocal);
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(cabecReqProdBean.getNroOSCabecReqProd());
    }

    public ColabBean getColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getIdColab(cabecReqProdBean.getIdFuncCabecReqProd());
    }

    public List<ItemReqProdBean> itemReqProdList(){
        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        return itemReqProdDAO.itemReqProdList(getCabecReqProdAberto().getIdCabecReqProd());
    }

    public List<EmbalProdBean> embalProdList(){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        return embalagemDAO.embalProdList(itemReqProdBean.getIdProdItemReqProd());
    }

    public List<LocalProdBean> localProdList(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.localProdList(itemReqProdBean.getIdEmbItemReqProd());
    }

    public ArrayList<ItemOSBean> itemOSList(){
        ArrayList<ItemOSBean> itemOSArrayList = new ArrayList<>();
        ItemOSDAO itemOSDAO = new ItemOSDAO();
        List<ItemOSBean> itemOSList = itemOSDAO.itemOSList(getOS().getIdOS());
        if(getOS().getTipoOS() == 2L){
            for(ItemOSBean itemOSBean : itemOSList){
                if(itemOSBean.getIdOficSecaoItemOS() == getColab().getIdOficSecaoColab()){
                    itemOSArrayList.add(itemOSBean);
                }
            }
        }
        else if(getOS().getTipoOS() == 22L){
            for(ItemOSBean itemOSBean : itemOSList){
                itemOSArrayList.add(itemOSBean);
            }
        }
        return itemOSArrayList;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// SET DADOS /////////////////////////////////////////////

    public void setCabecReqProduto() {
        ConfigCTR configCTR = new ConfigCTR();
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        ColabBean colabBean = mecanicoCTR.getColab(configCTR.getConfig().getMatricFuncConfig());
        this.cabecReqProdBean = new CabecReqProdBean();
        this.cabecReqProdBean.setIdFuncCabecReqProd(colabBean.getIdColab());
        this.cabecReqProdBean.setAparelhoCabecReqProd(configCTR.getConfig().getAparelhoConfig());
    }

    public void setItemReqProduto(Long idProd) {
        this.itemReqProdBean = new ItemReqProdBean();
        this.itemReqProdBean.setIdCabecItemReqProd(getCabecReqProdAberto().getIdCabecReqProd());
        this.itemReqProdBean.setIdProdItemReqProd(idProd);
    }

    public void setIdEmbProdReqProduto(){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        itemReqProdBean.setIdEmbItemReqProd(embalagemDAO.getEmbProd(itemReqProdBean.getIdProdItemReqProd()).getIdEmbalProd());
    }

    public void setIdLocalProdReqProduto(){
        LocalDAO localDAO = new LocalDAO();
        itemReqProdBean.setIdLocalItemReqProd(localDAO.getLocalProd(itemReqProdBean.getIdEmbItemReqProd()).getIdLocalProd());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// RECEBER DADOS SERVIDOR ///////////////////////////////////////

    public void atualDadosEmbalagem(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList embalagemArrayList = new ArrayList();
        embalagemArrayList.add("EmbalProdBean");
        embalagemArrayList.add("EmbalagemBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, embalagemArrayList);
    }

    public void atualDadosLocal(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList embalagemArrayList = new ArrayList();
        embalagemArrayList.add("LocalProdBean");
        embalagemArrayList.add("ProdutoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, embalagemArrayList);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////

    public String dadosEnvioCabec() {

        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        String dadosCabec = cabecReqProdDAO.dadosCabecFechado();

        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        String dadosItem = itemReqProdDAO.dadosEnvioItem(cabecReqProdDAO.idCabecFechadoList());

        return dadosCabec + "_" + dadosItem;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
