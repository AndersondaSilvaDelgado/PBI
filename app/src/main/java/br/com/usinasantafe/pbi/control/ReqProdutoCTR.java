package br.com.usinasantafe.pbi.control;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ReqProdutoBean;
import br.com.usinasantafe.pbi.model.dao.ApontDAO;
import br.com.usinasantafe.pbi.model.dao.BoletimDAO;
import br.com.usinasantafe.pbi.model.dao.ProdutoDAO;
import br.com.usinasantafe.pbi.model.dao.ReqProdutoDAO;

public class ReqProdutoCTR {

    private ReqProdutoBean reqProdutoBean;

    public ReqProdutoCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean verProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.verProduto(codProduto);
    }

    public boolean verReqProduto(){
        ReqProdutoDAO reqProdutoDAO = new ReqProdutoDAO();
        return reqProdutoDAO.verReqProduto();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void insertReqProduto(){
        ReqProdutoDAO reqProdutoDAO = new ReqProdutoDAO();
        reqProdutoDAO.insertReqProduto(reqProdutoBean);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// GET DADOS /////////////////////////////////////////////

    public ReqProdutoBean getReqProdutoBean() {
        return reqProdutoBean;
    }

    public ProdutoBean getProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getProduto(codProduto);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// SET DADOS /////////////////////////////////////////////

    public void setReqProdutoBean() {
        ConfigCTR configCTR = new ConfigCTR();
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        ColabBean colabBean = mecanicoCTR.getColab(configCTR.getConfig().getMatricFuncConfig());
        this.reqProdutoBean = new ReqProdutoBean();
        this.reqProdutoBean.setIdFuncReqProd(colabBean.getIdColab());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////

    public String dadosEnvioReqProduto() {

        ReqProdutoDAO reqProdutoDAO = new ReqProdutoDAO();
        String dadosReqProduto = reqProdutoDAO.dadosReqProduto();

        return dadosReqProduto;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
