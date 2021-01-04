package br.com.usinasantafe.pbi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.CabecReqProdBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;
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

		Log.i("PMM", "AKI");

		BoletimIndBean boletimIndBean = new BoletimIndBean();
		List boletimList = boletimIndBean.all();

		for (int i = 0; i < boletimList.size(); i++) {

			boletimIndBean = (BoletimIndBean) boletimList.get(i);
			Log.i("PBI", "BOLETIM");
			Log.i("PBI", "idBoletim = " + boletimIndBean.getIdBoletim());
			Log.i("PBI", "idExtBoletim = " + boletimIndBean.getIdExtBoletim());
			Log.i("PBI", "idFuncBoletim = " + boletimIndBean.getIdFuncBoletim());
			Log.i("PBI", "EquipBoletim = " + boletimIndBean.getNroAparelhoBoletim());
			Log.i("PBI", "dthrInicialBoletim = " + boletimIndBean.getDthrInicialBoletim());
			Log.i("PBI", "dthrFinalBoletim = " + boletimIndBean.getDthrFinalBoletim());
			Log.i("PBI", "statusBoletim = " + boletimIndBean.getStatusBoletim());

		}

		ApontIndBean apontTO = new ApontIndBean();
		List apontList = apontTO.all();

		for (int i = 0; i < apontList.size(); i++) {

			apontTO = (ApontIndBean) apontList.get(i);
			Log.i("PBI", "APONTAMENTO");
			Log.i("PBI", "idApont = " + apontTO.getIdApont());
			Log.i("PBI", "idBolApont = " + apontTO.getIdBolApont());
			Log.i("PBI", "idExtBolApont = " + apontTO.getIdExtBolApont());
			Log.i("PBI", "osApont = " + apontTO.getOsApont());
			Log.i("PBI", "itemOSApont = " + apontTO.getItemOSApont());
			Log.i("PBI", "paradaApont = " + apontTO.getParadaApont());
			Log.i("PBI", "dthrInicialAponta = " + apontTO.getDthrInicialApont());
			Log.i("PBI", "dthrFinalAponta = " + apontTO.getDthrFinalApont());
			Log.i("PBI", "realizAponta = " + apontTO.getRealizApont());
			Log.i("PBI", "statusAponta = " + apontTO.getStatusApont());

		}

		CabecReqProdBean cabecReqProdBean = new CabecReqProdBean();
		List cabecList = cabecReqProdBean.all();

		for (int i = 0; i < cabecList.size(); i++) {

			cabecReqProdBean = (CabecReqProdBean) cabecList.get(i);
			Log.i("PBI", "CABEC REQ");
			Log.i("PBI", "idCabecReqProd = " + cabecReqProdBean.getIdCabecReqProd());
			Log.i("PBI", "idFuncCabecReqProd = " + cabecReqProdBean.getIdFuncCabecReqProd());
			Log.i("PBI", "aparelhoCabecReqProd = " + cabecReqProdBean.getAparelhoCabecReqProd());
			Log.i("PBI", "nroOSCabecReqProd = " + cabecReqProdBean.getNroOSCabecReqProd());
			Log.i("PBI", "itemOSCabecReqProd = " + cabecReqProdBean.getItemOSCabecReqProd());
			Log.i("PBI", "dthrInicialCabecReqProd = " + cabecReqProdBean.getDthrInicialCabecReqProd());
			Log.i("PBI", "dthrFinalCabecReqProd = " + cabecReqProdBean.getDthrFinalCabecReqProd());
			Log.i("PBI", "statusCabecReqProd = " + cabecReqProdBean.getStatusCabecReqProd());
		}

		ItemReqProdBean itemReqProdBean = new ItemReqProdBean();
		List itemList = itemReqProdBean.all();

		for (int i = 0; i < itemList.size(); i++) {

			itemReqProdBean = (ItemReqProdBean) itemList.get(i);
			Log.i("PBI", "ITEM REQ");
			Log.i("PBI", "idItemReqProd = " + itemReqProdBean.getIdItemReqProd());
			Log.i("PBI", "idCabecItemReqProd = " + itemReqProdBean.getIdCabecItemReqProd());
			Log.i("PBI", "idProdItemReqProd = " + itemReqProdBean.getIdProdItemReqProd());
			Log.i("PBI", "idEmbItemReqProd = " + itemReqProdBean.getIdEmbItemReqProd());
			Log.i("PBI", "idLocalItemReqProd = " + itemReqProdBean.getIdLocalItemReqProd());
			Log.i("PBI", "qtdeItemReqProd = " + itemReqProdBean.getQtdeItemReqProd());
			Log.i("PBI", "dthrItemReqProd = " + itemReqProdBean.getDthrItemReqProd());
		}

	}

}