package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbdepprodest")
public class LocalProdBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idLocalProd;
    @DatabaseField
    private Long idLocal;
    @DatabaseField
    private Long idEmbProd;

    public LocalProdBean() {
    }

    public Long getIdLocalProd() {
        return idLocalProd;
    }

    public void setIdLocalProd(Long idLocalProd) {
        this.idLocalProd = idLocalProd;
    }

    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public Long getIdEmbProd() {
        return idEmbProd;
    }

    public void setIdEmbProd(Long idEmbProd) {
        this.idEmbProd = idEmbProd;
    }
}
