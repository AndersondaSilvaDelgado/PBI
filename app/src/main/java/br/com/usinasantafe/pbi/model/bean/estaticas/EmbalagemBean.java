package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbembalest")
public class EmbalagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEmbalagem;
    @DatabaseField
    private String sgEmbalagem;

    public EmbalagemBean() {
    }

    public Long getIdEmbalagem() {
        return idEmbalagem;
    }

    public void setIdEmbalagem(Long idEmbalagem) {
        this.idEmbalagem = idEmbalagem;
    }

    public String getSgEmbalagem() {
        return sgEmbalagem;
    }

    public void setSgEmbalagem(String sgEmbalagem) {
        this.sgEmbalagem = sgEmbalagem;
    }
}
