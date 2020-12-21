package br.com.usinasantafe.pbi.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbitemreqprodvar")
public class ItemReqProdBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemReqProd;
    @DatabaseField
    private Long idCabecItemReqProd;
    @DatabaseField
    private Long idProdItemReqProd;
    @DatabaseField
    private Long idEmbItemReqProd;
    @DatabaseField
    private Long idLocalItemReqProd;
    @DatabaseField
    private Double qtdeItemReqProd;
    @DatabaseField
    private String dthrItemReqProd;

    public ItemReqProdBean() {
    }

    public Long getIdItemReqProd() {
        return idItemReqProd;
    }

    public void setIdItemReqProd(Long idItemReqProd) {
        this.idItemReqProd = idItemReqProd;
    }

    public Long getIdCabecItemReqProd() {
        return idCabecItemReqProd;
    }

    public void setIdCabecItemReqProd(Long idCabecItemReqProd) {
        this.idCabecItemReqProd = idCabecItemReqProd;
    }

    public Long getIdProdItemReqProd() {
        return idProdItemReqProd;
    }

    public void setIdProdItemReqProd(Long idProdItemReqProd) {
        this.idProdItemReqProd = idProdItemReqProd;
    }

    public Long getIdEmbItemReqProd() {
        return idEmbItemReqProd;
    }

    public void setIdEmbItemReqProd(Long idEmbItemReqProd) {
        this.idEmbItemReqProd = idEmbItemReqProd;
    }

    public Long getIdLocalItemReqProd() {
        return idLocalItemReqProd;
    }

    public void setIdLocalItemReqProd(Long idLocalItemReqProd) {
        this.idLocalItemReqProd = idLocalItemReqProd;
    }

    public Double getQtdeItemReqProd() {
        return qtdeItemReqProd;
    }

    public void setQtdeItemReqProd(Double qtdeItemReqProd) {
        this.qtdeItemReqProd = qtdeItemReqProd;
    }

    public String getDthrItemReqProd() {
        return dthrItemReqProd;
    }

    public void setDthrItemReqProd(String dthrItemReqProd) {
        this.dthrItemReqProd = dthrItemReqProd;
    }
}
