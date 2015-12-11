package file_operate_release;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;

public class Log_http extends Log_base {
	public int httpinfo_num;
	
	public ArrayList readbody(JSONArray json) {
		ArrayList<String> Log_http_head_sql = new ArrayList<String>();
		ArrayList<String> Log_httpinfo_sql = new ArrayList<String>();
		ArrayList<String> Log_http_sql = new ArrayList<String>();
		
		for (int log_http_i = 0; log_http_i < json.size(); log_http_i++) {
			// System.out.println(json);// startTime
			BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();
			JSONArray http_temp = (JSONArray) (json.get(log_http_i));
			datatokenInfo((ArrayList) http_temp.get(0));

			String Log_http_sql_temp = "'" + datatokenInfo((ArrayList) http_temp.get(0)) + "',to_timestamp('"
					+ BigdecimaltoNormal.normaltoLocalTime(http_temp.get(1).toString().toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff'),to_timestamp('"
					+ BigdecimaltoNormal.normaltoLocalTime(http_temp.get(2).toString().toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff'),'" + http_temp.get(3).toString() + "','"
					+ http_temp.get(4).toString() + "','" + http_temp.get(5).toString() + "','"
					+ http_temp.get(6).toString() + "','" + http_temp.get(7).toString() + "'";
			Log_http_head_sql.add(Log_http_sql_temp);

			ArrayList http_httpinfo = (ArrayList) (http_temp.get(8));

			httpinfo_num=http_httpinfo.size();


			for (int httpinfo_i = 0; httpinfo_i < http_httpinfo.size(); httpinfo_i++) {

				httpInfo((ArrayList) http_httpinfo.get(httpinfo_i));
				String Log_httpinfo_sql_temp = "'" + datatokenInfo((ArrayList) http_temp.get(0)) + "','"
						+ httpInfo((ArrayList) http_httpinfo.get(httpinfo_i)) ;

				Log_httpinfo_sql.add(Log_httpinfo_sql_temp);
			}

			// devInfo(dev_devinfo);
		}
		Log_http_sql.addAll(Log_http_head_sql);
		Log_http_sql.addAll(Log_httpinfo_sql);
		return Log_http_sql;
	}
}
