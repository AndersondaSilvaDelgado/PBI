package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbembalprodest")
public class EmbalProdBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEmbalProd;
    @DatabaseField
    private Long idEmbal;
    @DatabaseField
    private Long idProd;

    public EmbalProdBean() {
    }

    public Long getIdEmbalProd() {
        return idEmbalProd;
    }

    public void setIdEmbalProd(Long idEmbalProd) {
        this.idEmbalProd = idEmbalProd;
    }

    public Long getIdEmbal() {
        return idEmbal;
    }

    public void setIdEmbal(Long idEmbal) {
        this.idEmbal = idEmbal;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }
}
