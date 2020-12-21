package br.com.usinasantafe.pbi.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimIndBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBoletim;
    @DatabaseField
    private Long idExtBoletim;
    @DatabaseField
    private Long idFuncBoletim;
    @DatabaseField
    private Long nroAparelhoBoletim;
    @DatabaseField
    private String dthrInicialBoletim;
    @DatabaseField
    private String dthrFinalBoletim;
    @DatabaseField
    private Long statusFechBoletim; // 0 - Automaticamente; 1 - Normal
    @DatabaseField
    private Long statusBoletim; // 1 - Aberto; 2 - Fechado
    @DatabaseField
    private Long apontBoletim; //0 - Não esta sendo apontado; 1 - Esta sendo Apontado

    public BoletimIndBean() {
    }

    public Long getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(Long idBoletim) {
        this.idBoletim = idBoletim;
    }

    public Long getIdFuncBoletim() {
        return idFuncBoletim;
    }

    public void setIdFuncBoletim(Long idFuncBoletim) {
        this.idFuncBoletim = idFuncBoletim;
    }

    public Long getIdExtBoletim() {
        return idExtBoletim;
    }

    public void setIdExtBoletim(Long idExtBoletim) {
        this.idExtBoletim = idExtBoletim;
    }

    public String getDthrInicialBoletim() {
        return dthrInicialBoletim;
    }

    public void setDthrInicialBoletim(String dthrInicialBoletim) {
        this.dthrInicialBoletim = dthrInicialBoletim;
    }

    public String getDthrFinalBoletim() {
        return dthrFinalBoletim;
    }

    public void setDthrFinalBoletim(String dthrFinalBoletim) {
        this.dthrFinalBoletim = dthrFinalBoletim;
    }

    public Long getStatusBoletim() {
        return statusBoletim;
    }

    public void setStatusBoletim(Long statusBoletim) {
        this.statusBoletim = statusBoletim;
    }

    public Long getApontBoletim() {
        return apontBoletim;
    }

    public void setApontBoletim(Long apontBoletim) {
        this.apontBoletim = apontBoletim;
    }

    public Long getNroAparelhoBoletim() {
        return nroAparelhoBoletim;
    }

    public void setNroAparelhoBoletim(Long nroAparelhoBoletim) {
        this.nroAparelhoBoletim = nroAparelhoBoletim;
    }

    public Long getStatusFechBoletim() {
        return statusFechBoletim;
    }

    public void setStatusFechBoletim(Long statusFechBoletim) {
        this.statusFechBoletim = statusFechBoletim;
    }
}
