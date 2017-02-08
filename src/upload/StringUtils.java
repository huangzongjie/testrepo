package upload;
import java.util.*; 
import java.text.*;


public class StringUtils {
  public static String toChinese(String strvalue) {
    try {
      if (strvalue == null) {
        return "";
      }
      else {

        strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
        return strvalue;
      }
    }
    catch (Exception e) {
      return "";
    }
  }

  public static String toChinese2(String strvalue) {
	    try {
	      if (strvalue == null) {
	        return "";
	      }
	      else {

	        strvalue = new String(strvalue.getBytes("ISO8859_1"), "utf-8");
	        return strvalue;
	      }
	    }
	    catch (Exception e) {
	      return "";
	    }
	  }
  
  public static final String escapeHTMLTags(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    StringBuffer buf = new StringBuffer(input.length());
    char ch = ' ';
    for (int i = 0; i < input.length(); i++) {
      ch = input.charAt(i);
      if (ch == '<') {
        buf.append("&lt;");
      }
      else if (ch == '>') {
        buf.append("&gt;");
      }
      else {
        buf.append(ch);
      }
    }
    return buf.toString();
  }
  
  public static String[] arr(String input,String inkey,String defaultv,int len) {
    String[] arr=new String[len]; 
    if (input == null || input.length() == 0) {
      for(int j=0;j<len;j++){
          arr[j]=defaultv;
      }
      return arr;
    }
    else{
      for(int j=0;j<len;j++){
          arr[j]="";
      }
    }
    
    String s="",ch,inputnew=input+"0";
    int m=0;
    for (int i = 0; i < input.length(); i++) {
      ch =inputnew.substring(i,i+1);
      if (ch.equals(inkey)) {
        m++;
      }
      else {
        arr[m]+=ch;
      }
    }
    m++;
    if(m<len){
        for(int n=m;n<len;n++){
            arr[n]=defaultv;
        }
    }
    return arr;
  }
  
  public static String str_replace(String from,String to,String source) {
    StringBuffer bf= new StringBuffer("");
    StringTokenizer st = new StringTokenizer(source,from,true);
    while (st.hasMoreTokens()) {
        String tmp = st.nextToken();
        //System.out.println("*"+tmp);
        if(tmp.equals(from)) {
            bf.append(to);
        } else {
            bf.append(tmp);
        }
    }
    return bf.toString();
 }

  public static String power_num2abc(String input) {
    String repower="a";
    try {
      if (input == null) {
        return repower;
      }
      else {
        if(input.equals("0000")){repower="a";}
        else if(input.equals("1000")){repower="b";}
        else if(input.equals("1100")){repower="c";}
        else if(input.equals("1010")){repower="d";}
        else if(input.equals("1001")){repower="e";}
        else if(input.equals("1110")){repower="f";}
        else if(input.equals("1101")){repower="g";}
        else if(input.equals("1011")){repower="h";}
        else if(input.equals("1111")){repower="i";}
        return repower;
      }
    }
    catch (Exception e) {
      return repower;
    }
  }
  public static String power_abc2num(String input) {
    String repower="0000";
    try {
      if (input == null) {
        return repower;
      }
      else {
        if(input.equals("a")){repower="0000";}
        else if(input.equals("b")){repower="1000";}
        else if(input.equals("c")){repower="1100";}
        else if(input.equals("d")){repower="1010";}
        else if(input.equals("e")){repower="1001";}
        else if(input.equals("f")){repower="1110";}
        else if(input.equals("g")){repower="1101";}
        else if(input.equals("h")){repower="1011";}
        else if(input.equals("i")){repower="1111";}
        return repower;
      }
    }
    catch (Exception e) {
      return repower;
    }
  }
  public static String tj_where(String table_head,String power1,String power2,String power3,String power4,String power5,String power1_v,String power2_v,String power3_v,String power4_v,String power5_v) {
    String repower="",repowerv="";
    try {
        if((!power1_v.equals(""))&&(!power1_v.equals("all"))&&(power1_v!=null)){
            String[] power1arr=arr(power1_v,"+","",100); 
            for(int i=0;i<100;i++){  
              	if((!power1arr[i].equals(""))&&(power1arr[i]!=null)){
                    if((!repowerv.equals(""))&&(repowerv!=null)){
              	        repowerv=repowerv+" or "+table_head+"custom."+power1+" like '%"+power1arr[i]+"%'";
              	    }
              	    else{              	        
              	        repowerv=repowerv+table_head+"custom."+power1+" like '%"+power1arr[i]+"%'";
              	    }
                }
                else{
                    break;
              	}
            }
            repower=repower+" and ("+repowerv+")";
            repowerv="";            
        }        
        if((!power2_v.equals(""))&&(!power2_v.equals("all"))&&(power2_v!=null)&&!power2.equals("bt_seller")){        	
            String[] power2arr=arr(power2_v,"+","",100); 
            
            for(int i=0;i<100;i++){  
              	if((!power2arr[i].equals(""))&&(power2arr[i]!=null)){
                    if((!repowerv.equals(""))&&(repowerv!=null)){
                    	
              	        repowerv=repowerv+" or "+table_head+"custom."+power2+" like '%"+power2arr[i]+"%'";
                    	    
                    	 }
              	    else{
              	    	
              	        repowerv=repowerv+table_head+"custom."+power2+" like '%"+power2arr[i]+"%'";
              	    	   
              	    	}
                }
                else{
                    break;
              	}
            }
            repower=repower+" and ("+repowerv+")";
            repowerv="";            
        }
        if((!power3_v.equals(""))&&(!power3_v.equals("all"))&&(power3_v!=null)&&!power3.equals("bt_seller")){
            String[] power3arr=arr(power3_v,"+","",100); 
            for(int i=0;i<100;i++){  
              	if((!power3arr[i].equals(""))&&(power3arr[i]!=null)){
                    if((!repowerv.equals(""))&&(repowerv!=null)){
                    	
              	        repowerv=repowerv+" or "+table_head+"custom."+power3+" like '%"+power3arr[i]+"%'";
                    	     
                    	}
              	    else{  
              	    	
              	        repowerv=repowerv+table_head+"custom."+power3+" like '%"+power3arr[i]+"%'";
              	    	    
              	    	}
                }
                else{
                    break;
              	}
            }
            repower=repower+" and ("+repowerv+")";
            repowerv="";            
        }
        if((!power4_v.equals(""))&&(!power4_v.equals("all"))&&(power4_v!=null)&&!power4.equals("bt_seller")){
            String[] power4arr=arr(power4_v,"+","",100); 
            for(int i=0;i<100;i++){  
              	if((!power4arr[i].equals(""))&&(power4arr[i]!=null)){              		
                    if((!repowerv.equals(""))&&(repowerv!=null)){
                    	
              	        repowerv=repowerv+" or "+table_head+"custom."+power4+" like '%"+power4arr[i]+"%'";
                    	     
                    	}
              	    else{
              	    	
              	        repowerv=repowerv+table_head+"custom."+power4+" like '%"+power4arr[i]+"%'";
             	    	     
             	       }
                }
                else{
                    break;
              	}
            }
            repower=repower+" and ("+repowerv+")";
            repowerv="";            
        }
        if((!power5_v.equals(""))&&(!power5_v.equals("all"))&&(power5_v!=null)&&!power5.equals("bt_seller")){
            String[] power5arr=arr(power5_v,"+","",100); 
            for(int i=0;i<100;i++){  
              	if((!power5arr[i].equals(""))&&(power5arr[i]!=null)){
                    if((!repowerv.equals(""))&&(repowerv!=null)){
                    	
              	        repowerv=repowerv+" or "+table_head+"custom."+power5+" like '%"+power5arr[i]+"%'";
                           
                    	}
              	    else{    
              	    	
              	        repowerv=repowerv+table_head+"custom."+power5+" like '%"+power5arr[i]+"%'";
            	    	    
            	    	}
                }
                else{
                    break;
              	}
            }
            repower=repower+" and ("+repowerv+")";
            repowerv="";            
        }
        return repower;
    }
    catch (Exception e) {
      return repower;
    }
  }
  public static String tj_where_bt(String table_head,String power1,String power2,String power3,String power4,String power5,String power1_v,String power2_v,String power3_v,String power4_v,String power5_v) {
	    String repower="",repowerv="";
	    try {	         
	        if((!power2_v.equals(""))&&(!power2_v.equals("all"))&&(power2_v!=null)&&power2.equals("bt_seller")){        	
	            String[] power2arr=arr(power2_v,"+","",100); 	            
	            for(int i=0;i<100;i++){  
	              	if((!power2arr[i].equals(""))&&(power2arr[i]!=null)){
	                    if((!repowerv.equals(""))&&(repowerv!=null)){	                    	 
	                    		 repowerv=repowerv+" or name like '"+power2arr[i]+"%'";	 
	                    	
	                    	 }
	              	    else{	              	    	
	              	         repowerv=repowerv+" name like '"+power2arr[i]+"%'";	              	    	
	              	    	}
	                }
	                else{
	                    break;
	              	}
	            }
	            repower=repower+" and ("+repowerv+")";
	            repowerv="";            
	        }
	        if((!power3_v.equals(""))&&(!power3_v.equals("all"))&&(power3_v!=null)&&power3.equals("bt_seller")){
	            String[] power3arr=arr(power3_v,"+","",100); 
	            for(int i=0;i<100;i++){  
	              	if((!power3arr[i].equals(""))&&(power3arr[i]!=null)){
	                    if((!repowerv.equals(""))&&(repowerv!=null)){
	                    	
	                    		repowerv=repowerv+" or name like '"+power3arr[i]+"%'";
	                    	
	                    	}
	              	    else{  
	              	    	
	              	        repowerv=repowerv+table_head+"custom."+power3+" like '%"+power3arr[i]+"%'";
	              	    	   
	              	    	}
	                }
	                else{
	                    break;
	              	}
	            }
	            repower=repower+" and ("+repowerv+")";
	            repowerv="";            
	        }
	        if((!power4_v.equals(""))&&(!power4_v.equals("all"))&&(power4_v!=null)&&power4.equals("bt_seller")){
	            String[] power4arr=arr(power4_v,"+","",100); 
	            for(int i=0;i<100;i++){  
	              	if((!power4arr[i].equals(""))&&(power4arr[i]!=null)){              		
	                    if((!repowerv.equals(""))&&(repowerv!=null)){
	                    	
	                    		repowerv=repowerv+" or name like '"+power4arr[i]+"%'";
	                    	
	                    	}
	              	    else{
	              	    	
	             	    		 repowerv=repowerv+" name like '"+power4arr[i]+"%'";
	             	    	
	             	       }
	                }
	                else{
	                    break;
	              	}
	            }
	            repower=repower+" and ("+repowerv+")";
	            repowerv="";            
	        }
	        if((!power5_v.equals(""))&&(!power5_v.equals("all"))&&(power5_v!=null)&&power5.equals("bt_seller")){
	            String[] power5arr=arr(power5_v,"+","",100); 
	            for(int i=0;i<100;i++){  
	              	if((!power5arr[i].equals(""))&&(power5arr[i]!=null)){
	                    if((!repowerv.equals(""))&&(repowerv!=null)){	                    	
	                    		repowerv=repowerv+" or name like '"+power5arr[i]+"%'";
	                    	
	                    	}
	              	    else{                 	    	
	            	    		 repowerv=repowerv+" name like '"+power5arr[i]+"%'";	            	    	
	            	    	}
	                }
	                else{
	                    break;
	              	}
	            }
	            repower=repower+" and ("+repowerv+")";
	            repowerv="";            
	        }
	        return repower;
	    }
	    catch (Exception e) {
	      return repower;
	    }
	  }
	  
  public static String tj_where_purchase_man(String[] purchase_manarr,String table,String storage) {
    String repurchase_man="",repurchase_manv="";
    try {
            for(int i=0;i<30;i++){  
              	if((!purchase_manarr[i].equals(""))&&(purchase_manarr[i]!=null)){
                    if((!repurchase_manv.equals(""))&&(repurchase_manv!=null)){
              	        repurchase_manv=repurchase_manv+" or  "+table+"."+storage+" like '%"+purchase_manarr[i]+"%'";
              	    }
              	    else{              	        
              	        repurchase_manv=" "+table+"."+storage+" like '%"+purchase_manarr[i]+"%'";
              	    }
                }
                else{
                    break;
              	}
            }
            repurchase_man=repurchase_man+" and ("+repurchase_manv+")";
            repurchase_manv="";
        return repurchase_man;
    }
    catch (Exception e) {
      return repurchase_man;
    }
  }
  

/*
Function name:    lTimeCompare
Description:      Compare two times
Input:            String CompTime1,String CompTime2 (Format: yyyy-MM-dd)
Output:           time diffrent by seconds
*/
  static String TimeFormat1="yyyy-MM-dd";
  public static long lTimeCompare(String CompTime1,String CompTime2){
      long iReturn=-1;
      SimpleDateFormat sdf = new SimpleDateFormat(TimeFormat1);
      Date DateTime1=sdf.parse(CompTime1,new ParsePosition(0));
      Date DateTime2=sdf.parse(CompTime2,new ParsePosition(0));
      iReturn=(DateTime2.getTime()-DateTime1.getTime())/(3600*24*1000)+1;
      return iReturn;  
  }
  public static String int2abc(int input) {
    String re="z";
    try {
      if (input<1) {
        return re;
      }
      else {
        if(input==1){re="A";}
        else if(input==2){re="B";}
        else if(input==3){re="C";}
        else if(input==4){re="D";}
        else if(input==5){re="E";}
        else if(input==6){re="F";}
        else if(input==7){re="G";}
        else if(input==8){re="H";}
        else if(input==9){re="I";}
        else if(input==10){re="J";}
        else if(input==11){re="K";}
        else if(input==12){re="L";}
        else if(input==13){re="M";}
        else if(input==14){re="N";}
        else if(input==15){re="O";}
        else if(input==16){re="P";}
        else if(input==17){re="Q";}
        else if(input==18){re="R";}
        else if(input==19){re="S";}
        else if(input==20){re="T";}
        else if(input==21){re="U";}
        else if(input==22){re="V";}
        else if(input==23){re="W";}
        else if(input==24){re="X";}
        else if(input==25){re="Y";}
        else if(input==26){re="Z";}
        return re;
      }
    }
    catch (Exception e) {
      return re;
    }
  }
  
//�ж�һ��string�Ƿ��ǿգ�����ǿգ����任��&nbsp;
	public static String nullStringToblank(String source){
		String result="";
		if(source==null||"null".equalsIgnoreCase(source.trim())){
			result="";
		}else{
			result=source;
		}
		return result;
	}
	
	/**
	 * �ж������ַ��͵������Ƿ����
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isTwoStringToDoubleEquals(String s1,String s2){
		boolean result=false;
		double d1=0,d2=0;
		try{
			d1=Double.parseDouble(s1);
		}catch(Exception e){
			e.printStackTrace();
			d1=0;
		}
		try{
			d2=Double.parseDouble(s2);
		}catch(Exception e){
			e.printStackTrace();
			d2=0;
		}
		if(d1==d2){
			result=true;
		}
		return result;
	}
	
	/**
	 * ��stringת����double
	 * @param s
	 * @return
	 */
	public static double parseDouble(String s){
		double result=0;
		if(s!=null&&s.trim().length()>0){
			try{
				result=Double.parseDouble(s);
			}catch(Exception e){
				e.printStackTrace();
				result=0;
			}
		}
		return result;
	}
	
	/**
	 * �ж�һ����ݿ��ַ��Ƿ��ǿ�
	 * @param s
	 * @return
	 */
	public static boolean isDatabaseStringNull(String s){
		boolean result=false;
		if(s==null||s.trim().length()==0||"null".equalsIgnoreCase(s)){
			result=true;
		}
		return result;
	}

}
