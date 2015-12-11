package file_operate_release;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;

public class BigdecimaltoLocalTime {
	public static String bigdecimaltoNormal(String timestamp) {
		BigDecimal bd = new BigDecimal(timestamp);
		return bd.toPlainString();
	}

	public static String toLocalTime(String unix) {
		Long timestamp = Long.parseLong(unix);
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
		return date;
	}

	public static long toUnixTime(String local) throws ParseException {
		Date date = new Date();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (simpleDateFormat.parse(simpleDateFormat.format(date))).getTime() / 1000;
	}

	public static String normaltoLocalTime(String timestamp) {
		BigdecimaltoLocalTime time_tmp = new BigdecimaltoLocalTime();

		return time_tmp.toLocalTime(time_tmp.bigdecimaltoNormal(timestamp));
	}

}
