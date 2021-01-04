package br.com.usinasantafe.pbi.model.dao;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;

public class ProdutoDAO {

    public ProdutoDAO() {
    }

    public boolean verProduto(String codProduto){
        List<ProdutoBean> produtoList = produtoList(codProduto);
        boolean ret = (produtoList.size() > 0);
        produtoList.clear();
        return ret;
    }

    public ProdutoBean getProduto(String codProduto){
        List<ProdutoBean> produtoList = produtoList(codProduto);
        ProdutoBean produtoBean = produtoList.get(0);
        produtoList.clear();
        return produtoBean;
    }

    public ProdutoBean getProduto(Long idProduto){
        List<ProdutoBean> produtoList = produtoList(idProduto);
        ProdutoBean produtoBean = produtoList.get(0);
        produtoList.clear();
        return produtoBean;
    }

    public List<ProdutoBean> produtoList(String codProduto){
        ProdutoBean produtoBean = new ProdutoBean();
        return produtoBean.get("codProduto", codProduto);
    }

    public List<ProdutoBean> produtoList(Long idProduto){
        ProdutoBean produtoBean = new ProdutoBean();
        return produtoBean.get("idProduto", idProduto);
    }

}
