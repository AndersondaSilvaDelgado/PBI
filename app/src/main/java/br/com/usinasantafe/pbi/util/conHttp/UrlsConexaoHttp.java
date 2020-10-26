package br.com.usinasantafe.pbi.util.conHttp;

import br.com.usinasantafe.pbi.PBIContext;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pbidev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pbidev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pbi.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pbi.util.conHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PBIContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String ComponenteBean = urlPrincipal + "componente.php" + put;
    public static String EscalaTrabBean = urlPrincipal + "escalatrab.php" + put;
    public static String ParadaBean = urlPrincipal + "parada.php" + put;
    public static String ServicoBean = urlPrincipal + "servico.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInsertBolFechado() {
        return urlPrincEnvio + "inserirbolfechado.php" + put;
    }

    public String getsInsertBolAberto() {
        return urlPrincEnvio + "inserirbolaberto.php" + put;
    }

    public String getsInsertReqProduto() {
        return urlPrincEnvio + "inserirreqproduto.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "verequip.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        } else if (classe.equals("OS")) {
            retorno = urlPrincipal + "os.php" + put;
        }
        return retorno;
    }

}
