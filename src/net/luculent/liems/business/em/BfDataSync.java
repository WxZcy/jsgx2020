package net.luculent.liems.business.em;

import java.util.ArrayList;

import net.luculent.core.base.FormatDate;
import net.luculent.core.base.Log;
import net.luculent.core.database.Database;
import net.luculent.core.database.Rowset;
import net.luculent.liems.business.bf.engine.BaseBfForm;
import net.luculent.liems.business.bf.engine.BfPageInfo;
import net.luculent.liems.business.bf.engine.BfRow;
import net.luculent.liems.business.bf.engine.BfTable;
import net.luculent.liems.component.sequence.CommSequence;
import net.luculent.liems.util.Tools;

public class BfDataSync {
	public String createBf(BaseBfForm bf, String params) {
		String[] ss = params.split(";");//";"�ָ��������Զ��壬ֻҪ�봫�ݲ����ķָ���һ�¼��ɡ�
		String tblName = ss[0];
		String BFFRMED_NO = ss[1];
		
		CommSequence cs = CommSequence.getInstance(tblName+"_SEQ");
	    String BFFRMIN_NO = cs.getNextValue();
		
		BfPageInfo bpi = bf.getPageInfo();// ��������Ϣ����
		String FSTUSR_ID = bpi.getCurrUser();// ��ǰ��¼�û�		
		String FRMTIME = bpi.getFrmTime();// ��Сʱ����Сʱʱ��;	
		String BFFORM_DTM = bpi.getFormDtm();// ��ǰ���������ڣ����ڸ�ʽ���ձ���Сʱ�����౨��// �ܱ���yyyy-mm-dd���±���yyyy-mm���걨��yyyy��	

		String BFFRMIN_STA = bpi.getFrmInSta();// ����ǰ״ֵ̬		
		String ORG_NO = bpi.getOrgNo();// ����ǰ��¼������˾		
		String RMSHIFT_ID = bpi.getRmShiftId();// ��ǰ�����		
		String RMTEAM_ID = bpi.getRmTeamId();// ��ǰ��ֵ��	
		
		String FSTUSR_DTM = FormatDate.toY_M_D_H_M_S(FormatDate.getCurrDate());
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String load = row.getColumnByName("tab0_select1").getColVal();//����
		String time = "";

		if("350".equals(load)){
			time = "2019-02-24 16:00:00";
		} else if("260".equals(load)){
			time = "2019-02-24 15:00:00";
		} else if("180".equals(load)){
			time = "2019-02-24 14:00:00";
		} else if("0".equals(load)){
			time = "2019-02-24 12:00:00";
		} else if("240".equals(load)){
			time = "2019-02-24 11:00:00";
		} else if("220".equals(load)){
			time = "2019-02-24 10:00:00";
		} else if("200".equals(load)){
			time = "2019-02-24 9:00:00";
		} 
		
		String selectSQL = "";
		String insertSQL = "";
		selectSQL = getSelectSQL(BFFRMED_NO);
		
		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(true);
			
			insertSQL = "insert into " + tblName + " (BFFRMIN_NO,BFFRMED_NO,BFFORM_DTM,RMSHIFT_ID,RMTEAM_ID,BFFRMIN_STA,FRMTIME,FSTUSR_ID,FSTUSR_DTM,ORG_NO,"
					+ selectSQL +") select '"
					+ BFFRMIN_NO + "','" 
					+ BFFRMED_NO + "',liems_to_date('"
					+ BFFORM_DTM + "','yyyy-mm-dd'),'"
					+ RMSHIFT_ID + "','"
					+ RMTEAM_ID + "','"
					+ BFFRMIN_STA + "',liems_to_date('"
					+ FRMTIME + "','yyyy-mm-dd hh24:mi:ss'),'"
					+ FSTUSR_ID + "',liems_to_date('"
					+ FSTUSR_DTM + "','yyyy-mm-dd hh24:mi:ss'),'"
					+ ORG_NO + "',"
					+ selectSQL + " from " + tblName + " where FRMTIME = to_date('"+ time +"','yyyy-mm-dd hh24:mi:ss')";
			localDatabase.execSqlUpdate(insertSQL);
			return "�ѽ���";
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "��������ϵ����Ա";
	}
	
	public String getSelectSQL(String BFFRMED_NO){
		String selectSQL = "";
		Database localDatabase = null;
		localDatabase = Tools.getDatabase(true);
		Rowset localRowset;
		try {
			localRowset = localDatabase.getRS("select BFFRMST_ID from bffrmstmst where BFFRMED_NO ='" + BFFRMED_NO + "'");
			int i=0;
			int n = localRowset.getRowCount();
			while(localRowset.next()){
				i++;
				selectSQL += localRowset.getString("BFFRMST_ID");
				if(i<n){
					selectSQL += ",";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return selectSQL;
	}
}
