package file_operate_release;


import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.internal.LinkedTreeMap;

public class Log_crash extends Log_base {

	public ArrayList<String> readbody(JSONArray json) {
		ArrayList<String> Log_crash_sql = new ArrayList<String>();
		
		for (int log_crash_i = 0; log_crash_i < json.size(); log_crash_i++) {
			JSONArray crash_temp = (JSONArray) (json.get(log_crash_i));
			for (int log_crashtmp_i = 0; log_crashtmp_i < crash_temp.size(); log_crashtmp_i++) {
				
//				System.out.println(crash_temp.get(log_crashtmp_i).getClass().toString() + "--------crashinfo.size");// startTime
				
//				System.out.println(crash_temp.get(log_crashtmp_i) + "--------crashinfo.size");
				LinkedTreeMap  crash_crashinfo = (LinkedTreeMap ) (crash_temp.get(log_crashtmp_i));


//				ArrayList crash_crashinfo = (ArrayList) (crash_temp.get(log_crashtmp_i));
				
//				System.out.println(crash_crashinfo.size() + "--------crashinfo.size");// startTime
//				System.out.println(crash_crashinfo.get(1).getClass().toString() + "--------class");// startTime
//
//				for (int crashinfo_i = 1; crashinfo_i < crash_crashinfo.size(); crashinfo_i++) {
//
					crashInfo((LinkedTreeMap) crash_crashinfo);
					Log_crash_sql.add(crashInfo((LinkedTreeMap) crash_crashinfo));
//				}
			}
			
			

		}
		return Log_crash_sql;
	}
}
