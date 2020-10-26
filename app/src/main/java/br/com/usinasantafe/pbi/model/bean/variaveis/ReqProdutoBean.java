package br.com.usinasantafe.pbi.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbreqprodvar")
public class ReqProdutoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idReqProd;
    @DatabaseField
    private Long idFuncReqProd;
    @DatabaseField
    private Long aparelhoReqProd;
    @DatabaseField
    private Long osReqProd;
    @DatabaseField
    private Long itemOSReqProd;
    @DatabaseField
    private String codProdReqProd;
    @DatabaseField
    private Long qtdeReqProd;
    @DatabaseField
    private String dthrReqProd;

    public ReqProdutoBean() {
    }

    public Long getIdReqProd() {
        return idReqProd;
    }

    public void setIdReqProd(Long idReqProd) {
        this.idReqProd = idReqProd;
    }

    public Long getIdFuncReqProd() {
        return idFuncReqProd;
    }

    public void setIdFuncReqProd(Long idFuncReqProd) {
        this.idFuncReqProd = idFuncReqProd;
    }

    public Long getAparelhoReqProd() {
        return aparelhoReqProd;
    }

    public void setAparelhoReqProd(Long aparelhoReqProd) {
        this.aparelhoReqProd = aparelhoReqProd;
    }

    public Long getOsReqProd() {
        return osReqProd;
    }

    public void setOsReqProd(Long osReqProd) {
        this.osReqProd = osReqProd;
    }

    public Long getItemOSReqProd() {
        return itemOSReqProd;
    }

    public void setItemOSReqProd(Long itemOSReqProd) {
        this.itemOSReqProd = itemOSReqProd;
    }

    public Long getQtdeReqProd() {
        return qtdeReqProd;
    }

    public void setQtdeReqProd(Long qtdeReqProd) {
        this.qtdeReqProd = qtdeReqProd;
    }

    public String getDthrReqProd() {
        return dthrReqProd;
    }

    public void setDthrReqProd(String dthrReqProd) {
        this.dthrReqProd = dthrReqProd;
    }

    public String getCodProdReqProd() {
        return codProdReqProd;
    }

    public void setCodProdReqProd(String codProdReqProd) {
        this.codProdReqProd = codProdReqProd;
    }
}
