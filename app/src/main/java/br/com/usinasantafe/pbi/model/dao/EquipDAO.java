package br.com.usinasantafe.pbi.model.dao;

import java.util.List;

public class EquipDAO {

    public EquipDAO() {
    }

    public EquipBean getIdEquip(Long idEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("idEquip", idEquip);
        equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public boolean verNroEquip(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("nroEquip", nroEquip);
        boolean ret = (equipList.size() > 0);
        equipList.clear();
        return ret;
    }

    public EquipBean getNroEquip(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("nroEquip", nroEquip);
        equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

}
