package upload;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;



public class ParamUtils {

  public static String getParameter(HttpServletRequest request,
                                    String paramName) {
    return getParameter(request, paramName, false);
  }

  public static String getParameter(HttpServletRequest request,
                                    String paramName, String defaultStr) {
    String temp = request.getParameter(paramName);

    if (temp != null) {
      if (temp.equals("")) {
        return defaultStr;
      }
      else {
        return nullToString(temp);
      }
    }
    else {
      return defaultStr;
    }

  }

  public static String getEscapeHTMLParameter(HttpServletRequest request,
                                              String paramName) {
    return nullToString(StringUtils.escapeHTMLTags(ParamUtils.getParameter(
        request, paramName, true)));
  }

  public static String getEscapeHTMLParameter(HttpServletRequest request,
                                              String paramName,
                                              String defaultValue) {
    String temp = StringUtils.escapeHTMLTags(ParamUtils.getParameter(request,
        paramName, true));
    if ( (temp == null) || (temp.equals(""))) {
      temp = defaultValue;
    }
    return nullToString(temp);
  }

  public static String getParameter(HttpServletRequest request,
                                    String paramName, boolean emptyStringsOK) {
    String temp = request.getParameter(paramName);
    if (temp != null) {
      if (temp.equals("") && !emptyStringsOK) {
        return "";
      }
      else {
        return temp;
      }
    }
    else {
      return "";
    }
  }


  public static int getIntParameter(HttpServletRequest request,
                                    String paramName, int defaultNum) {
    String temp = request.getParameter(paramName);
    if (temp != null && !temp.equals("")) {
      int num = defaultNum;
      try {
        num = Integer.parseInt(temp);
      }
      catch (Exception ignored) {}
      return num;
    }
    else {
      return defaultNum;
    }
  }

  public static int getIntParameter(HttpServletRequest request,
                                    String paramName) {
    return getIntParameter(request, paramName, 0);
  }

  public static double getDoubleParameter(HttpServletRequest request,String paramName, double defaultNum) {
    String temp = request.getParameter(paramName);
    if (temp != null && !temp.equals("")) {
      double num = defaultNum;
      String nums="";
      try {
      	DecimalFormat myformat = new DecimalFormat(".00");
        nums = myformat.format(temp);
        num=Double.parseDouble(temp);
      }
      catch (Exception ignored) {}
      return num;
    }
    else {
      return defaultNum;
    }
  }

  public static double getDoubleParameter(HttpServletRequest request,
                                    String paramName) {
    return getDoubleParameter(request, paramName, 0.00);
  }
  
  public static String nullToString(String oldString) {
    if (oldString == null) {
      return "";
    }
    return oldString;
  }
  
  public static double getDoubleParameter2(HttpServletRequest request,String paramName, double defaultNum) {
	  double result=0; 
	  String temp = request.getParameter(paramName);
	    if (temp != null && !"".equals(temp.trim())) {
	      try {
	      	result=Double.parseDouble(temp);
	      }
	      catch (Exception ignored) {
	    	  return defaultNum;
	      }
	    }else {
	      return defaultNum;
	    }
	    return result;
	  }
  
}
