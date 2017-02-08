package upload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TEST {

	public static void main(String[] args) {
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String date = sdf.format(new Date());
			System.out.print(date);
		
	}

}
