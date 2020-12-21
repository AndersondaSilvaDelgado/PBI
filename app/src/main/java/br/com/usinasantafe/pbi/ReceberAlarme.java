package br.com.usinasantafe.pbi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.pst.DatabaseHelper;
import br.com.usinasantafe.pbi.util.EnvioDadosServ;
import br.com.usinasantafe.pbi.util.Tempo;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().dataHora());
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PMM", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}

		teste();

	}

	public void teste() {

		BoletimIndBean boletimIndBean = new BoletimIndBean();
		List boletimList = boletimIndBean.all();

		Log.i("PMM", "AKI");

		for (int i = 0; i < boletimList.size(); i++) {

			boletimIndBean = (BoletimIndBean) boletimList.get(i);
			Log.i("PBM", "BOLETIM");
			Log.i("PBM", "idBoletim = " + boletimIndBean.getIdBoletim());
			Log.i("PBM", "idExtBoletim = " + boletimIndBean.getIdExtBoletim());
			Log.i("PBM", "idFuncBoletim = " + boletimIndBean.getIdFuncBoletim());
			Log.i("PBM", "EquipBoletim = " + boletimIndBean.getNroAparelhoBoletim());
			Log.i("PBM", "dthrInicialBoletim = " + boletimIndBean.getDthrInicialBoletim());
			Log.i("PBM", "dthrFinalBoletim = " + boletimIndBean.getDthrFinalBoletim());
			Log.i("PBM", "statusBoletim = " + boletimIndBean.getStatusBoletim());

		}

		ApontIndBean apontTO = new ApontIndBean();
		List apontList = apontTO.all();

		for (int i = 0; i < apontList.size(); i++) {

			apontTO = (ApontIndBean) apontList.get(i);
			Log.i("PBM", "APONTAMENTO");
			Log.i("PBM", "idApont = " + apontTO.getIdApont());
			Log.i("PBM", "idBolApont = " + apontTO.getIdBolApont());
			Log.i("PBM", "idExtBolApont = " + apontTO.getIdExtBolApont());
			Log.i("PBM", "osApont = " + apontTO.getOsApont());
			Log.i("PBM", "itemOSApont = " + apontTO.getItemOSApont());
			Log.i("PBM", "paradaApont = " + apontTO.getParadaApont());
			Log.i("PBM", "dthrInicialAponta = " + apontTO.getDthrInicialApont());
			Log.i("PBM", "dthrFinalAponta = " + apontTO.getDthrFinalApont());
			Log.i("PBM", "realizAponta = " + apontTO.getRealizApont());
			Log.i("PBM", "statusAponta = " + apontTO.getStatusApont());

		}

		OSBean osBean = new OSBean();
		List osList = osBean.all();

		for (int i = 0; i < osList.size(); i++) {

			osBean = (OSBean) osList.get(i);
			Log.i("PBM", "OS");
			Log.i("PBM", "idOS = " + osBean.getIdOS());
			Log.i("PBM", "nroOS = " + osBean.getNroOS());
			Log.i("PBM", "tipoOS = " + osBean.getTipoOS());

		}

		ItemOSBean itemOSBean = new ItemOSBean();
		List itemOSList = itemOSBean.all();

		for (int i = 0; i < itemOSList.size(); i++) {

			itemOSBean = (ItemOSBean) itemOSList.get(i);
			Log.i("PBM", "ItemOS");
			Log.i("PBM", "idItemOS = " + itemOSBean.getIdItemOS());
			Log.i("PBM", "idOS = " + itemOSBean.getIdOS());
			Log.i("PBM", "seqItemOS = " + itemOSBean.getSeqItemOS());
			Log.i("PBM", "idOficSecaoItemOS = " + itemOSBean.getIdOficSecaoItemOS());

		}

	}

}