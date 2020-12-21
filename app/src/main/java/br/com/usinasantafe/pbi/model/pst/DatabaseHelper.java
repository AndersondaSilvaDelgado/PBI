package br.com.usinasantafe.pbi.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pbi.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalProdBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EmbalagemBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.EscalaTrabBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ItemOSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ParametroBean;
import br.com.usinasantafe.pbi.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ApontIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.BoletimIndBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pbi.model.bean.variaveis.ItemReqProdBean;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pbi_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;

	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;
		instance = this;
		
	}

	@Override
	public void close() {

		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {

		try{

			TableUtils.createTable(cs, ColabBean.class);
			TableUtils.createTable(cs, LocalBean.class);
			TableUtils.createTable(cs, LocalProdBean.class);
			TableUtils.createTable(cs, EmbalagemBean.class);
			TableUtils.createTable(cs, EmbalProdBean.class);
			TableUtils.createTable(cs, EscalaTrabBean.class);
			TableUtils.createTable(cs, ItemOSBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, ParametroBean.class);
			TableUtils.createTable(cs, ProdutoBean.class);

			TableUtils.createTable(cs, ApontIndBean.class);
			TableUtils.createTable(cs, BoletimIndBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, ItemReqProdBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
//				TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
