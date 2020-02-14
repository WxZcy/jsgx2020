package net.luculent.liems.business.em;

import java.util.ArrayList;

import net.luculent.core.base.Log;
import net.luculent.core.database.Database;
import net.luculent.core.database.Rowset;
import net.luculent.liems.business.bf.engine.BaseBfForm;
import net.luculent.liems.business.bf.engine.BfColumn;
import net.luculent.liems.business.bf.engine.BfPageInfo;
import net.luculent.liems.business.bf.engine.BfRow;
import net.luculent.liems.business.bf.engine.BfTable;
import net.luculent.liems.component.sequence.CommSequence;
import net.luculent.liems.util.Tools;

public class BfCalc {
	/**   
	* ���ؼ�����ʱ���õĽű�����   
	* @param bf ��չ�ű�������ڵ��β�   
	* @param params �磨"a;b;c"�� 
	* @return   
	*/ 
	public String DemoVal(BaseBfForm bf, String params){
		String[] ss = params.split(";");//";"�ָ��������Զ��壬ֻҪ�봫�ݲ����ķָ���һ�¼��ɡ�
		String s0 = ss[0];
		String s1 = ss[1];
		String s2 = ss[2]; 
		
		String val = "";
		String s = "";
		BfPageInfo bpi = bf.getPageInfo();//��������Ϣ����
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		for(int i=0;i<rows.size();i++){    
			BfRow row = rows.get(i);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
			String textId = "tab0_text1";
			BfColumn col = row.getColumnByName(textId);//���ݿؼ�id��ȡ�ؼ�����
			val = col.getColVal();//��ȡ�ؼ���ֵ��
		} 
		return val;
	}

	public String getNomByCin(BaseBfForm bf){
		String val = "";
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		for(int i=0;i<rows.size();i++){    
			BfRow row = rows.get(i);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
			String textId = "tab0_text95";
			BfColumn col = row.getColumnByName(textId);//���ݿؼ�id��ȡ�ؼ�����
			val = col.getColVal();//��ȡ�ؼ���ֵ��
		} 
		Database localDatabase = null;
		String nom = "";
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select tab0_text4 from B1BFG40022mst where tab0_text5 ='"+ val + "'");
			if (!(localRowset.next())) {}
				nom = localRowset.getString("tab0_text4");
			return nom;
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�������֤��";
	}
	
	public String getTime1(BaseBfForm bf){		
		double time = 0;

		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		for(int i=0; i<rows.size(); i++){    
			BfRow row = rows.get(i);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
			for(int n=1; n<=31; n++){
				BfColumn col = row.getColumnByName("tab0_text"+n);//���ݿؼ�id��ȡ�ؼ�����
				String str = col.getColVal();//��ȡ�ؼ���ֵ��
				if(str!=null && !"".equals(str)){
					time += Double.parseDouble(str);
				}
			}	
		} 
		return Double.toString(time);
	}
	
	public String getTime2(BaseBfForm bf){		
		double time = 0;

		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		for(int i=0; i<rows.size(); i++){    
			BfRow row = rows.get(i);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
			for(int n=32; n<=62; n++){
				BfColumn col = row.getColumnByName("tab0_text"+n);//���ݿؼ�id��ȡ�ؼ�����
				String str = col.getColVal();//��ȡ�ؼ���ֵ��
				if(str!=null && !"".equals(str)){
					time += Double.parseDouble(str);
				}
			}	
		} 
		return Double.toString(time);
	}
	
	public String getTime3(BaseBfForm bf){		
		double time = 0;

		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		for(int i=0; i<rows.size(); i++){    
			BfRow row = rows.get(i);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
			for(int n=63; n<=93; n++){
				BfColumn col = row.getColumnByName("tab0_text"+n);//���ݿؼ�id��ȡ�ؼ�����
				String str = col.getColVal();//��ȡ�ؼ���ֵ��
				if(str!=null && !"".equals(str)){
					time += Double.parseDouble(str);
				}
			}	
		} 
		return Double.toString(time);
	}
	
	public String getTimeSaturday(BaseBfForm bf){		
		double time = 0;
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String year = row.getColumnByName("tab0_text96").getColVal();//���
		String month = row.getColumnByName("tab0_text97").getColVal();//�·�

		Database localDatabase = null;
		String val = "";
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select * from B1BFG40048mst where tab0_text32 = '"+year+"' and tab0_text33 = '"+month+"'");
			if (!(localRowset.next())) {}
			
			for(int i=1; i<=31; i++){
				val = localRowset.getString("tab0_select"+i);
				if("1".equals(val)){
					String str = row.getColumnByName("tab0_text"+i).getColVal();
					if(str!=null && !"".equals(str)){
						time += Double.parseDouble(str);
					}
				}
			}
			return Double.toString(time);
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�����������ϵ����Ա";
	}
	
	public String getTimeSunday(BaseBfForm bf){		
		double time = 0;
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String year = row.getColumnByName("tab0_text96").getColVal();//���
		String month = row.getColumnByName("tab0_text97").getColVal();//�·�

		Database localDatabase = null;
		String val = "";
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select * from B1BFG40048mst where tab0_text32 = '"+year+"' and tab0_text33 = '"+month+"'");
			if (!(localRowset.next())) {}
			
			for(int i=1; i<=31; i++){
				val = localRowset.getString("tab0_select"+i);
				if("2".equals(val)){
					String str = row.getColumnByName("tab0_text"+i).getColVal();
					if(str!=null && !"".equals(str)){
						time += Double.parseDouble(str);
					}
				}
			}
			return Double.toString(time);
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�����������ϵ����Ա";
	}
	
	public String getTimeHld(BaseBfForm bf){		
		double time = 0;
		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String year = row.getColumnByName("tab0_text96").getColVal();//���
		String month = row.getColumnByName("tab0_text97").getColVal();//�·�

		Database localDatabase = null;
		String val = "";
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select * from B1BFG40048mst where tab0_text32 = '"+year+"' and tab0_text33 = '"+month+"'");
			if (!(localRowset.next())) {}
			
			for(int i=1; i<=31; i++){
				val = localRowset.getString("tab0_select"+i);
				if("3".equals(val)){
					String str = row.getColumnByName("tab0_text"+i).getColVal();
					if(str!=null && !"".equals(str)){
						time += Double.parseDouble(str);
					}
				}
			}
			return Double.toString(time);
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�����������ϵ����Ա";
	}
	
	public String createDOC(BaseBfForm bf){
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String oldYear = row.getColumnByName("tab0_text1").getColVal();//��ʷ���
		String oldMonth = row.getColumnByName("tab0_text2").getColVal();//��ʷ�·�
		String newYear = row.getColumnByName("tab0_text3").getColVal();//�ƻ����
		String newMonth = row.getColumnByName("tab0_text4").getColVal();//�ƻ��·�
		if(newYear.equals(oldYear) && newMonth.equals(oldMonth)){
			return "��ʷ�·���ƻ��·��ظ�";
		}else{
		    String TAB0_TEXT1="";
		    String TAB0_TEXT2="";
		    String TAB0_TEXT3="";
		    String TAB0_TEXT4="";
		    String TAB0_TEXT5="";
		    String TAB0_TEXT6="";
		    String TAB0_TEXT7="";
		    String TAB0_TEXT8="";
		    String TAB0_TEXT9="";
		    String insertSQL ="";
			
			Database localDatabase = null;
			try {
				localDatabase = Tools.getDatabase(true);
				Rowset localRowset = localDatabase.getRS("select TAB0_TEXT1,TAB0_TEXT2,TAB0_TEXT3,TAB0_TEXT4,TAB0_TEXT5,TAB0_TEXT6,TAB0_TEXT7,TAB0_TEXT8,TAB0_TEXT9 from B1BFG40022MST where tab0_text10 = '"+oldYear+"' and tab0_text11 = '"+oldMonth+"'");
				int i=0;
				while(localRowset.next()){
					i++;
					TAB0_TEXT1 = localRowset.getString("TAB0_TEXT1");
					TAB0_TEXT2 = localRowset.getString("TAB0_TEXT2");
					TAB0_TEXT3 = localRowset.getString("TAB0_TEXT3");
					TAB0_TEXT4 = localRowset.getString("TAB0_TEXT4");
					TAB0_TEXT5 = localRowset.getString("TAB0_TEXT5");
					TAB0_TEXT6 = localRowset.getString("TAB0_TEXT6");
					TAB0_TEXT7 = localRowset.getString("TAB0_TEXT7");
					TAB0_TEXT8 = localRowset.getString("TAB0_TEXT8");
					TAB0_TEXT9 = localRowset.getString("TAB0_TEXT9");
					insertSQL = "'"+TAB0_TEXT1+"','"+TAB0_TEXT2+"','"+TAB0_TEXT3+"','"+TAB0_TEXT4+"','"+TAB0_TEXT5+"','"+TAB0_TEXT6+"','"+TAB0_TEXT7+"','"+TAB0_TEXT8+"','"+TAB0_TEXT9+"','"+newYear+"','"+newMonth+"'";
					//System.out.println("-----------"+insertSQL);
					insertDOC(insertSQL);
				}
				if(i==0){
					return "������ʷ�·ݵ����Ƿ����";
				}else{
					return "������";
				}
			} catch (Exception localException) {
				Log.info(localException);
			} finally {
				if (localDatabase != null)
					localDatabase.cleanup();
			}
			return "��������ϵ����Ա";
		}
	}
	
	public void insertDOC(String insertSQL){
		CommSequence cs = CommSequence.getInstance("B1BFG40022MST_SEQ");
		String BFFRMIN_NO = cs.getNextValue();
	    insertSQL = "insert into B1BFG40022MST (BFFRMIN_NO,BFFRMED_NO,BFFRMIN_STA,ORG_NO,TAB0_TEXT1,TAB0_TEXT2,TAB0_TEXT3,TAB0_TEXT4,TAB0_TEXT5,TAB0_TEXT6,TAB0_TEXT7,TAB0_TEXT8,TAB0_TEXT9,TAB0_TEXT10,TAB0_TEXT11) values('"
		+BFFRMIN_NO+"','1081279059456950272','04','1075310369292943360',"+insertSQL+")";
	    //System.out.println("=========="+insertSQL);
		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(true);
			localDatabase.execSqlUpdate(insertSQL);
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
	}
	
	public String createEndDtm(BaseBfForm bf){
		BfPageInfo bpi = bf.getPageInfo();// ��������Ϣ����
		String BFFRMIN_NO = bpi.getPkValue();// ��ǰ������ֵ		
		//��ȡ������ؼ�ֵ
		BfTable tbl = bf.getRootTable();//��ȡ���������
		ArrayList<BfRow> rows = tbl.getRows();//��ȡ��ǰ���м�¼����һ ������¼�����¼ֻ��һ�����ӱ���ϸ��¼�����ж�����
		BfRow row = rows.get(0);//�����õ��м�¼��������ֻ��һ����ֻ����� һ�Σ��ӱ�����ж���
		String days = row.getColumnByName("tab0_text21").getColVal();//�ϼ�����
		String endDtm = "";

		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select to_char(to_date(TAB0_TEXT11, 'YYYY-MM-DD')+"+days+"-1, 'YYYY-MM-DD') from B1BFG40073MST where BFFRMIN_NO ="+BFFRMIN_NO);
			while(localRowset.next()){
				endDtm = localRowset.getString(1);
			}
			if("".equals(endDtm)){
				return "����";
			}else{
				return endDtm;
			}
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�����������ϵ����Ա";
	}
	
	public String calcDays(BaseBfForm bf){
		BfPageInfo bpi = bf.getPageInfo();// ��������Ϣ����
		String BFFRMIN_NO = bpi.getPkValue();// ��ǰ������ֵ		
		String days = "";//�ϼ�����

		Database localDatabase = null;
		try {
			localDatabase = Tools.getDatabase(true);
			Rowset localRowset = localDatabase.getRS("select TO_DATE(TAB0_TEXT8, 'YYYY-MM-DD')-TO_DATE(TAB0_TEXT7, 'YYYY-MM-DD') from B1BFG40074MST where BFFRMIN_NO ="+BFFRMIN_NO);
			while(localRowset.next()){
				days = localRowset.getString(1);
			}
			if("".equals(days)){
				return "����";
			}else{
				return days;
			}
		} catch (Exception localException) {
			Log.info(localException);
		} finally {
			if (localDatabase != null)
				localDatabase.cleanup();
		}
		return "�����������ϵ����Ա";
	}
}
	