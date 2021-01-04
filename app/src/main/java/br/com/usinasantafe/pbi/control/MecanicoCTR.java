package br.com.usinasantafe.pbi.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EscalaTrabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ParametroBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.dao.ApontIndDAO;
import br.com.usinasantafe.pbi.model.dao.BoletimIndDAO;
import br.com.usinasantafe.pbi.model.dao.ColabDAO;
import br.com.usinasantafe.pbi.model.dao.EscalaTrabDAO;
import br.com.usinasantafe.pbi.model.dao.ItemOSDAO;
import br.com.usinasantafe.pbi.model.dao.OSDAO;
import br.com.usinasantafe.pbi.model.dao.ParadaDAO;
import br.com.usinasantafe.pbi.model.dao.ParametroDAO;
import br.com.usinasantafe.pbi.util.AtualDadosServ;
import br.com.usinasantafe.pbi.util.Tempo;
import br.com.usinasantafe.pbi.util.VerifDadosServ;

public class MecanicoCTR {

    private ApontIndBean apontIndBean;

    public MecanicoCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean hasElementsColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.hasElements();
    }

    public boolean verMatricColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verMatricColab(matricColab);
    }

    public boolean verApont(){
        List<ApontIndBean> apontList = apontBolApontList();
        boolean ret = (apontList.size() > 0);
        apontList.clear();
        return ret;
    }

    public boolean verOSApont(Long nroOS){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        OSDAO osDAO = new OSDAO();
        return (apontIndDAO.verOSApont(boletimIndDAO.getBoletimApont().getIdBoletim(), nroOS) && osDAO.verOS(nroOS));
    }

    public boolean verBoletimFechado(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        return boletimIndDAO.verBoletimFechado();
    }

    public boolean verApontSemEnvio(){
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        return apontIndDAO.verApontSemEnvio();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void insertParametro(String parametros){

        try {

            JSONObject jObj = new JSONObject(parametros);
            JSONArray jsonArray = jObj.getJSONArray("parametro");

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ParametroDAO parametroDAO = new ParametroDAO();
                    parametroDAO.insert(gson.fromJson(objeto.toString(), ParametroBean.class));
                }

            }

        } catch (Exception e) {
        }
    }

    public void atualSalvarBoletim(){

        ConfigCTR configCTR = new ConfigCTR();
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        boletimIndDAO.atualBoletimSApont();
        ColabBean colabBean = getColab(configCTR.getConfig().getMatricFuncConfig());
        boletimIndDAO.atualSalvarBoletim(configCTR.getConfig().getAparelhoConfig(), colabBean.getIdColab(), getEscalaTrab(colabBean.getIdEscalaTrabColab()).getHorarioEntEscalaTrab());

    }


    public void salvarApont(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        apontIndDAO.salvarApont(apontIndBean, getEscalaTrab(getColabApont().getIdEscalaTrabColab()).getHorarioEntEscalaTrab(), boletimIndDAO.getBoletimApont());

    }

    public void fecharBoletim(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        boletimIndDAO.fecharBoletim(boletimIndDAO.getBoletimApont());
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        apontIndDAO.fecharApont(getUltApont());
    }

    public void forcarFechBoletim(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        String dthrFinal = Tempo.getInstance().dataFinalizarBol(boletimIndDAO.getBoletimApont().getDthrInicialBoletim(), getEscalaTrab(getColabApont().getIdEscalaTrabColab()).getHorarioSaiEscalaTrab());
        boletimIndDAO.fecharBoletim(boletimIndDAO.getBoletimApont(), dthrFinal);
        apontIndDAO.fecharApont(getUltApont(), dthrFinal);
    }

    public void finalizarApont(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        apontIndDAO.finalizarApont(apontIndDAO.apontList(boletimIndDAO.getBoletimApont().getIdBoletim()).get(0));
    }

    public void interroperApont(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        apontIndDAO.interroperApont(apontIndDAO.apontList(boletimIndDAO.getBoletimApont().getIdBoletim()).get(0));
    }

    public void delBolSApont() {

        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();

        List<BoletimIndBean> boletimList = boletimIndDAO.boletimAllList();

        for(BoletimIndBean boletimIndBean : boletimList){
            if(!apontIndDAO.verApont(boletimIndBean.getIdBoletim())){
                boletimIndDAO.delBoletim(boletimIndBean);
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// GET DADOS /////////////////////////////////////////////

    public BoletimIndBean getBoletimApont(){
        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        return boletimIndDAO.getBoletimApont();
    }

    public EscalaTrabBean getEscalaTrab(Long idEscalaTrab){
        EscalaTrabDAO escalaTrabDAO = new EscalaTrabDAO();
        return escalaTrabDAO.getEscalaTrab(idEscalaTrab);
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getMatricColab(matricColab);
    }

    public ColabBean getColabApont(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getIdColab(getBoletimApont().getIdFuncBoletim());
    }

    public List<ApontIndBean> apontBolApontList(){
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        return apontIndDAO.apontList(getBoletimApont().getIdBoletim());
    }

    public ApontIndBean getUltApont(){
        List<ApontIndBean> apontList = apontBolApontList();
        ApontIndBean apontIndBean = apontList.get(0);
        apontList.clear();
        return apontIndBean;
    }

    public ApontIndBean getApontIndBean() {
        return apontIndBean;
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(apontIndBean.getOsApont());
    }

    public List<ApontIndBean> apontList(){
        ApontIndDAO apontIndDAO = new ApontIndDAO();
        return apontIndDAO.apontList(getBoletimApont().getIdBoletim());
    }

    public ArrayList<ItemOSBean> itemOSList(){
        ArrayList<ItemOSBean> itemOSArrayList = new ArrayList<>();
        ItemOSDAO itemOSDAO = new ItemOSDAO();
        List<ItemOSBean> itemOSList = itemOSDAO.itemOSList(getOS().getIdOS());
        if(getOS().getTipoOS() == 2L){
            for(ItemOSBean itemOSBean : itemOSList){
                if(itemOSBean.getIdOficSecaoItemOS() == getColabApont().getIdOficSecaoColab()){
                    itemOSArrayList.add(itemOSBean);
                }
            }
        }
        else if(getOS().getTipoOS() == 22L){
            for(ItemOSBean itemOSBean : itemOSList){
                itemOSArrayList.add(itemOSBean);
            }
        }
        return itemOSArrayList;
    }

    public List<ParadaBean> paradaList(){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.paradaCodList();
    }

    public ParadaBean getParadaCod(Long codParada){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaCod(codParada);
    }

    public ParadaBean getParadaId(Long idParada){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaId(idParada);
    }

    public ParametroBean getParametro(){
        ParametroDAO parametroDAO = new ParametroDAO();
        return parametroDAO.getParametro();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// SET DADOS /////////////////////////////////////////////

    public void setApontIndBean(ApontIndBean apontIndBean) {
        this.apontIndBean = apontIndBean;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void verOS(String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dados, telaAtual, telaProx, progressDialog);
    }

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosItemOS(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList itemOSArrayList = new ArrayList();
        itemOSArrayList.add("ComponenteBean");
        itemOSArrayList.add("ServicoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, itemOSArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// RECEBER DADOS SERVIDOR ///////////////////////////////////////

    public void recDadosOS(String result){

        try {

            if (!result.contains("exceeded")) {

                int posicao = result.indexOf("#") + 1;
                String objPrinc = result.substring(0, result.indexOf("#"));
                String objSeg = result.substring(posicao);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    OSDAO osDAO = new OSDAO();
                    osDAO.deleteAllOS();

                    Gson gson = new Gson();

                    JSONObject osObj = jsonArray.getJSONObject(0);
                    osDAO.insertOS(gson.fromJson(osObj.toString(), OSBean.class));

                    jObj = new JSONObject(objSeg);
                    jsonArray = jObj.getJSONArray("dados");

                    ItemOSDAO itemOSDAO = new ItemOSDAO();
                    itemOSDAO.deleteAllItemOS();

                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject itemOSObj = jsonArray.getJSONObject(j);
                        itemOSDAO.insertItemOS(gson.fromJson(itemOSObj.toString(), ItemOSBean.class));
                    }

                    ConfigCTR configCTR = new ConfigCTR();
                    OSBean osBean = gson.fromJson(osObj.toString(), OSBean.class);

                    boolean ver = false;

                    if((configCTR.getColab().getFlagApontMecanColab() == 1L)
                        && (osBean.getTipoOS() == 2L)){
                        ver = true;
                    }

                    if((configCTR.getColab().getFlagApontIndColab() == 1L)
                            && (osBean.getTipoOS() == 22L)){
                        ver = true;
                    }

                    if(ver){
                        VerifDadosServ.getInstance().pulaTelaComTerm();
                    }
                    else{
                        VerifDadosServ.getInstance().msgComTerm("OS INVÁLIDA PARA O USUÁRIO.");
                    }


                } else {
                    VerifDadosServ.getInstance().msgComTerm("NÃO EXISTE O.S. PARA ESSE COLABORADOR! POR FAVOR, ENTRE EM CONTATO COM A AREA QUE CRIA O.S. PARA APONTAMENTO.");
                }

            } else {
                VerifDadosServ.getInstance().msgComTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            Log.e("PBI", "ERRO = " + e);
            VerifDadosServ.getInstance().msgComTerm("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public void atualBolAberto(String retorno) {

        try {

            int pos1 = retorno.indexOf("#") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            JSONObject boletimJsonObject = new JSONObject(objPrinc);
            JSONArray boletimJsonArray = boletimJsonObject.getJSONArray("boletim");

            JSONObject apontJsonObject = new JSONObject(objSeg);
            JSONArray apontJsonArray = apontJsonObject.getJSONArray("apont");

            if (boletimJsonArray.length() > 0) {

                for (int i = 0; i < boletimJsonArray.length(); i++) {

                    JSONObject objeto = boletimJsonArray.getJSONObject(i);
                    Gson gson = new Gson();

                    BoletimIndBean boletimIndBean = gson.fromJson(objeto.toString(), BoletimIndBean.class);

                    BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
                    boletimIndDAO.atualIdExtBol(boletimIndBean);

                    ApontIndDAO apontIndDAO = new ApontIndDAO();
                    apontIndDAO.updApontIdExtBoletim(boletimIndBean.getIdBoletim(), boletimIndBean.getIdExtBoletim());

                }

                for (int i = 0; i < apontJsonArray.length(); i++) {

                    JSONObject objeto = apontJsonArray.getJSONObject(i);
                    Gson gson = new Gson();

                    ApontIndBean apontIndBean = gson.fromJson(objeto.toString(), ApontIndBean.class);

                    ApontIndDAO apontIndDAO = new ApontIndDAO();
                    apontIndDAO.updApontEnviado(apontIndBean.getIdApont(), apontIndBean.getIdBolApont());

                }

            }



        } catch (Exception e) {
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delBolFechado(String retorno) {

        try {

            int pos1 = retorno.indexOf("#") + 1;
            String dados = retorno.substring(pos1);

            JSONObject jObj = new JSONObject(dados);
            JSONArray jsonArray = jObj.getJSONArray("boletim");

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();

                    BoletimIndBean boletimIndBean = gson.fromJson(objeto.toString(), BoletimIndBean.class);

                    BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
                    boletimIndDAO.delBoletim(boletimIndBean);

                    ApontIndDAO apontIndDAO = new ApontIndDAO();
                    apontIndDAO.delApont(boletimIndBean.getIdBoletim());

                }

            }

        } catch (Exception e) {
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void atualApont(String retorno) {

        try {

            int pos1 = retorno.indexOf("#") + 1;
            String dados = retorno.substring(pos1);

            JSONObject jObj = new JSONObject(dados);
            JSONArray jsonArray = jObj.getJSONArray("boletim");

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();

                    ApontIndDAO apontIndDAO = new ApontIndDAO();
                    apontIndDAO.updApont(gson.fromJson(objeto.toString(), ApontIndBean.class));

                }

            }

        } catch (Exception e) {
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////

    public String dadosEnvioBolFechado() {

        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        String dadosBoletim = boletimIndDAO.dadosBolFechado();

        ApontIndDAO apontIndDAO = new ApontIndDAO();
        String dadosApont = apontIndDAO.dadosEnvioApont(boletimIndDAO.idBolFechadoList());

        return dadosBoletim + "_" + dadosApont;

    }

    public String dadosEnvioBolSemEnvio() {

        BoletimIndDAO boletimIndDAO = new BoletimIndDAO();
        ApontIndDAO apontIndDAO = new ApontIndDAO();

        String dadosBoletim = boletimIndDAO.dadosBolAbertoSemEnvio(apontIndDAO.idBolAbertoList());
        String dadosApont = apontIndDAO.dadosEnvioApont(apontIndDAO.idBolAbertoList());

        return dadosBoletim + "_" + dadosApont;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
