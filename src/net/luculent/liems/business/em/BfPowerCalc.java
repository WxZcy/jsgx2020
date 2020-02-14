package net.luculent.liems.business.em;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.luculent.core.base.Log;
import net.luculent.core.database.Database;
import net.luculent.core.database.Rowset;
import net.luculent.liems.business.bf.engine.BaseBfForm;
import net.luculent.liems.component.sequence.CommSequence;
import net.luculent.liems.util.Tools;

public class BfPowerCalc {
	public String getPower(BaseBfForm bf){		
		getPowerRecursion(); //�ݹ����getPowerRecursion����
		return "OK";
	}
	
	public void getPowerRecursion(){
		String[] params = new String[5];
		params = getParams();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Database localDatabase = null;
		
		try {
			CommSequence cs = CommSequence.getInstance("POWERCALC_SEQ");
			localDatabase = Tools.getDatabase(false);

			ArrayList localArrayList = new ArrayList();
			
			long   T1 = simpleDateFormat.parse(params[0]).getTime(); //T1ʱ��
			double P1 = Double.parseDouble(params[1]); //T1ʱ�̸���
			double Pr = Double.parseDouble(params[2]); //Ŀ�긺��
			long   T2 = simpleDateFormat.parse(params[3]).getTime(); //T2ʱ��
			double V = Double.parseDouble(params[4]); //������������
			Date   T  = simpleDateFormat.parse(params[0]);
			double Pt = 0;
			double PrNew = 0;
			long diff = (T2 - T1)/(1000*60); //ָ������min
			
			int i = P1-Pr > 0 ? 1 : -1; //i��������ʾ����
			
			long aim  = (long) (T1+i*(P1-Pr)/V*(1000*60)); //�ﵽĿ�긺�ɵ�ʱ��
			
			if(diff>0 && diff<i*(P1-Pr)/V){ //�ﵽĿ�긺�ɵ�ʱ��С��
				while(T.getTime()<T2){
					Pt = Pr + i*V*((T.getTime()+1000*60-T1)/(1000*60)-0.5); //�м�ʱ�̸���
					localArrayList.add(new Object[] {cs.getNextValue(),simpleDateFormat.format(T),Pt}); //����ֵ ʱ�� ����
					T = new Date(T.getTime()+1000*60);
				}
				PrNew = Pr + i*V*diff;
			} else {
				while(T.getTime()<T2){
					if(T.getTime() < aim){
						Pt = Pr + i*V*((T.getTime()+1000*60-T1)/(1000*60)-0.5); //�м�ʱ�̸���
						localArrayList.add(new Object[] {cs.getNextValue(),simpleDateFormat.format(T),Pt}); //����ֵ ʱ�� ����
						T = new Date(T.getTime()+1000*60);
					} else {
						Pt = P1;
						localArrayList.add(new Object[] {cs.getNextValue(),simpleDateFormat.format(T),Pt});//����ֵ ʱ�� ����
						T = new Date(T.getTime()+1000*60);
					}
				}
				PrNew = P1; 
			}
			insertPower(localArrayList); //����ֵ ʱ�� ���� ����Ŀ���
			updatePr(PrNew); //������ʷĿ��ָ�����Ŀ��ָ����һ��
			if(!"over".equals(params[0])){
				getPowerRecursion();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
	}
	
	public String[] getParams(){ //����ʷĿ��ָ����л�ȡ����
		String[] params = new String[5];
		Database localDatabase = null;
		localDatabase = Tools.getDatabase(true);
		Rowset localRowset;
		try {
			//�ҵ���ʷĿ��ָ���������δ����ļ�¼
			localRowset = localDatabase.getRS("select tab0_text1, tab0_text2, tab0_text3, tab0_text4 from B1BFG40080mst where to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss') = (select max(to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss')) from B1BFG40080mst where tab0_text3 is not null) or to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss') = (select min(to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss')) from B1BFG40080mst where tab0_text3 is null) order by tab0_text1 asc");
			int i=0;
			if(localRowset.getRowCount()==2){
				while(localRowset.next()){
					i++;
					if(i<2){
						params[0] = localRowset.getString(1); //Ŀ��ָ���´�ʱ��
						params[1] = localRowset.getString(2); //Ŀ�긺��
						params[2] = localRowset.getString(3); //Ŀ��ָ���´�ʱ�̵ĸ���
					}else {
						params[3] = localRowset.getString(1);
						params[4] = localRowset.getString(4); //������������
					}
				}
			}else{
				params[0] = "over";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (localDatabase != null)
				
				
				localDatabase.cleanup();
		}
		return params;
	}
	
	public void insertPower(ArrayList localArrayList){
	    String insertSQL = "insert into POWERCALC (P_NO,P_DATE,P_POWER) values(?,?,?)"; //��Ŀ����в���ʱ�̡����ɣ���ȷ�����ӣ�
		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(false);
			localDatabase.execPrepareSqlBatch(insertSQL,localArrayList);
			localDatabase.commit();
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
	}
	
	public void updatePr(double Pr2){
		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(true);
			localDatabase.execSqlUpdate("update B1BFG40080mst set tab0_text3 = '"+Pr2+"' where to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss') = (select min(to_date(tab0_text1,'yyyy-mm-dd hh24:mi:ss')) from B1BFG40080mst where tab0_text3 is null)");
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
	}
}
