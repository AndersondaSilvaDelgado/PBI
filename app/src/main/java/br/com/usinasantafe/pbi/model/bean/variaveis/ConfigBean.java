package br.com.usinasantafe.pbi.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pbi.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
    @DatabaseField
    private Long aparelhoConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long matricFuncConfig;

    public ConfigBean() {
    }

    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public Long getAparelhoConfig() {
        return aparelhoConfig;
    }

    public void setAparelhoConfig(Long aparelhoConfig) {
        this.aparelhoConfig = aparelhoConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public Long getMatricFuncConfig() {
        return matricFuncConfig;
    }

    public void setMatricFuncConfig(Long matricFuncConfig) {
        this.matricFuncConfig = matricFuncConfig;
    }
}
