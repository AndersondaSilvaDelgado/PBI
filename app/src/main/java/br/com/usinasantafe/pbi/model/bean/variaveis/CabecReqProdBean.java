package br.com.usinasantafe.pbi.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecreqprodvar")
public class CabecReqProdBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecReqProd;
    @DatabaseField
    private Long idFuncCabecReqProd;
    @DatabaseField
    private Long aparelhoCabecReqProd;
    @DatabaseField
    private Long nroOSCabecReqProd;
    @DatabaseField
    private Long itemOSCabecReqProd;
    @DatabaseField
    private String dthrInicialCabecReqProd;
    @DatabaseField
    private String dthrFinalCabecReqProd;
    @DatabaseField
    private Long statusCabecReqProd; //1 - Aberto; 2 - Fechado

    public CabecReqProdBean() {
    }

    public Long getIdCabecReqProd() {
        return idCabecReqProd;
    }

    public void setIdCabecReqProd(Long idCabecReqProd) {
        this.idCabecReqProd = idCabecReqProd;
    }

    public Long getIdFuncCabecReqProd() {
        return idFuncCabecReqProd;
    }

    public void setIdFuncCabecReqProd(Long idFuncCabecReqProd) {
        this.idFuncCabecReqProd = idFuncCabecReqProd;
    }

    public Long getAparelhoCabecReqProd() {
        return aparelhoCabecReqProd;
    }

    public void setAparelhoCabecReqProd(Long aparelhoCabecReqProd) {
        this.aparelhoCabecReqProd = aparelhoCabecReqProd;
    }

    public Long getNroOSCabecReqProd() {
        return nroOSCabecReqProd;
    }

    public void setNroOSCabecReqProd(Long nroOSCabecReqProd) {
        this.nroOSCabecReqProd = nroOSCabecReqProd;
    }

    public Long getItemOSCabecReqProd() {
        return itemOSCabecReqProd;
    }

    public void setItemOSCabecReqProd(Long itemOSCabecReqProd) {
        this.itemOSCabecReqProd = itemOSCabecReqProd;
    }

    public String getDthrInicialCabecReqProd() {
        return dthrInicialCabecReqProd;
    }

    public void setDthrInicialCabecReqProd(String dthrInicialCabecReqProd) {
        this.dthrInicialCabecReqProd = dthrInicialCabecReqProd;
    }

    public String getDthrFinalCabecReqProd() {
        return dthrFinalCabecReqProd;
    }

    public void setDthrFinalCabecReqProd(String dthrFinalCabecReqProd) {
        this.dthrFinalCabecReqProd = dthrFinalCabecReqProd;
    }

    public Long getStatusCabecReqProd() {
        return statusCabecReqProd;
    }

    public void setStatusCabecReqProd(Long statusCabecReqProd) {
        this.statusCabecReqProd = statusCabecReqProd;
    }
}
