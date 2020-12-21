package br.com.usinasantafe.pbi.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pbi.control.MecanicoCTR;
import br.com.usinasantafe.pbi.control.ReqProdutoCTR;
import br.com.usinasantafe.pbi.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pbi.util.conHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    ////////////////////////////////// ENVIAR DADOS //////////////////////////////////////////////

    public void enviarBolAberto() {

        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        String dados = mecanicoCTR.dadosEnvioBolSemEnvio();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAberto()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolFechado() {

        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        String dados = mecanicoCTR.dadosEnvioBolFechado();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechado()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarReqProduto() {
//
//        ReqProdutoCTR reqProdutoCTR = new ReqProdutoCTR();
//        String dados = reqProdutoCTR.dadosEnvioReqProduto();
//
//        Log.i("PMM", "REQ PRODUTO = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInsertReqProduto()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void recebeDados(String result){

        try {
            setEnviando(false);
            Log.i("ECM", "VALOR RECEBIDO --> " + result);
            MecanicoCTR mecanicoCTR = new MecanicoCTR();
            ReqProdutoCTR reqProdutoCTR = new ReqProdutoCTR();
            if(result.trim().startsWith("BOLFECHADOMEC")){
                mecanicoCTR.delBolFechado(result);
            }
            else if(result.trim().startsWith("BOLABERTOMEC")){
                mecanicoCTR.atualBolAberto(result);
            }
            else if(result.trim().startsWith("APONTMEC")){
                mecanicoCTR.atualApont(result);
            }
            else if(result.trim().startsWith("REQPRODUTO")){
//                reqProdutoCTR.delReqProduto(result);
            }
        } catch (Exception e) {
            setEnviando(true);
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO///////////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        ReqProdutoCTR reqProdutoCTR = new ReqProdutoCTR();
        if (mecanicoCTR.verBoletimFechado()) {
            enviarBolFechado();
        } else {
            if (mecanicoCTR.verApontSemEnvio()) {
                enviarBolAberto();
            }
            else{
//                if(reqProdutoCTR.verReqProduto()){
//                    enviarReqProduto();
//                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        if ((!mecanicoCTR.verBoletimFechado())
                && (!mecanicoCTR.verApontSemEnvio())) {
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

}
