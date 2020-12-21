package br.com.usinasantafe.pbi.control;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalagemBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.CabecReqProdBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;
import br.com.usinasantafe.pbi.model.dao.CabecReqProdDAO;
import br.com.usinasantafe.pbi.model.dao.ColabDAO;
import br.com.usinasantafe.pbi.model.dao.EmbalagemDAO;
import br.com.usinasantafe.pbi.model.dao.ItemReqProdDAO;
import br.com.usinasantafe.pbi.model.dao.LocalProdDAO;
import br.com.usinasantafe.pbi.model.dao.ProdutoDAO;

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

    public boolean verQtdeEmbProd(){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        return embalagemDAO.verQtdeEmbProd(itemReqProdBean.getIdProdItemReqProd());
    }

    public boolean verQtdeLocalProd(){
        LocalProdDAO localProdDAO = new LocalProdDAO();
        return localProdDAO.verQtdeLocalProd(itemReqProdBean.getIdEmbItemReqProd());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void salvarCabecReqProd(Long itemOS){
        CabecReqProdDAO cabecReqProdDAO = new CabecReqProdDAO();
        cabecReqProdBean.setItemOSCabecReqProd(itemOS);
        cabecReqProdDAO.salvarCabecReqProd(cabecReqProdBean);
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

    public List<ItemReqProdBean> itemReqProdList(){
        ItemReqProdDAO itemReqProdDAO = new ItemReqProdDAO();
        return itemReqProdDAO.itemReqProdList(getCabecReqProdAberto().getIdCabecReqProd());
    }

    public List<EmbalProdBean> embalProdList(){
        EmbalagemDAO embalagemDAO = new EmbalagemDAO();
        return embalagemDAO.embalProdList(itemReqProdBean.getIdProdItemReqProd());
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
        LocalProdDAO localProdDAO = new LocalProdDAO();
        itemReqProdBean.setIdLocalItemReqProd(localProdDAO.getLocalProd(itemReqProdBean.getIdEmbItemReqProd()).getIdLocalProd());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// RECEBER DADOS SERVIDOR ///////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////////

}
