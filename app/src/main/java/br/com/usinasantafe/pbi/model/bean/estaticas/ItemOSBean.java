package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbitemosest")
public class ItemOSBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idItemOS;
    @DatabaseField
    private Long idOS;
    @DatabaseField
    private Long seqItemOS;
    @DatabaseField
    private Long idOficSecaoItemOS;

    public ItemOSBean() {
    }

    public Long getIdItemOS() {
        return idItemOS;
    }

    public void setIdItemOS(Long idItemOS) {
        this.idItemOS = idItemOS;
    }

    public Long getIdOS() {
        return idOS;
    }

    public void setIdOS(Long idOS) {
        this.idOS = idOS;
    }

    public Long getSeqItemOS() {
        return seqItemOS;
    }

    public void setSeqItemOS(Long seqItemOS) {
        this.seqItemOS = seqItemOS;
    }

    public Long getIdOficSecaoItemOS() {
        return idOficSecaoItemOS;
    }

    public void setIdOficSecaoItemOS(Long idOficSecaoItemOS) {
        this.idOficSecaoItemOS = idOficSecaoItemOS;
    }

}
