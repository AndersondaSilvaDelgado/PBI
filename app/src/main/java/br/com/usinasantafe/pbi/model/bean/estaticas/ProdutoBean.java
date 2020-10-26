package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbprodutoest")
public class ProdutoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idProduto;
    @DatabaseField
    private String codProduto;
    @DatabaseField
    private String descrProduto;

    public ProdutoBean() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescrProduto() {
        return descrProduto;
    }

    public void setDescrProduto(String descrProduto) {
        this.descrProduto = descrProduto;
    }
}
