package br.com.usinasantafe.pbi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbcolabest")
public class ColabBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idColab;
    @DatabaseField
    private Long matricColab;
    @DatabaseField
    private String nomeColab;
    @DatabaseField
    private Long flagApontMecanColab;
    @DatabaseField
    private Long flagApontIndColab;
    @DatabaseField
    private Long flagSolicColab;
    @DatabaseField
    private Long idOficSecaoColab;
    @DatabaseField
    private Long idEscalaTrabColab;

    public ColabBean() {
    }

    public Long getIdColab() {
        return idColab;
    }

    public void setIdColab(Long idColab) {
        this.idColab = idColab;
    }

    public Long getMatricColab() {
        return matricColab;
    }

    public void setMatricColab(Long matricColab) {
        this.matricColab = matricColab;
    }

    public String getNomeColab() {
        return nomeColab;
    }

    public void setNomeColab(String nomeColab) {
        this.nomeColab = nomeColab;
    }

    public Long getFlagApontMecanColab() {
        return flagApontMecanColab;
    }

    public void setFlagApontMecanColab(Long flagApontMecanColab) {
        this.flagApontMecanColab = flagApontMecanColab;
    }

    public Long getFlagApontIndColab() {
        return flagApontIndColab;
    }

    public void setFlagApontIndColab(Long flagApontIndColab) {
        this.flagApontIndColab = flagApontIndColab;
    }

    public Long getFlagSolicColab() {
        return flagSolicColab;
    }

    public void setFlagSolicColab(Long flagSolicColab) {
        this.flagSolicColab = flagSolicColab;
    }

    public Long getIdOficSecaoColab() {
        return idOficSecaoColab;
    }

    public void setIdOficSecaoColab(Long idOficSecaoColab) {
        this.idOficSecaoColab = idOficSecaoColab;
    }

    public Long getIdEscalaTrabColab() {
        return idEscalaTrabColab;
    }

    public void setIdEscalaTrabColab(Long idEscalaTrabColab) {
        this.idEscalaTrabColab = idEscalaTrabColab;
    }
}
