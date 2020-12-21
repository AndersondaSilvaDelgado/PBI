package br.com.usinasantafe.pbi.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public OSBean getOS(Long nroOS){
        List<OSBean> osList = osList(nroOS);
        OSBean osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public boolean verOS(Long nroOS){
        List<OSBean> osList = osList(nroOS);
        boolean ret = (osList.size() > 0);
        osList.clear();
        return ret;
    }

    public List<OSBean> osList(Long nroOS){
        OSBean osBean = new OSBean();
        return osBean.get("nroOS", nroOS);
    }

    public void verOS(String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verDados(dados, "OS", telaAtual, telaProx, progressDialog);
    }

    public void deleteAllOS(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

    public void insertOS(OSBean osBean){
        osBean.insert();
    }

}
