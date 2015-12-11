package file_operate_release;


import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.internal.LinkedTreeMap;

public class Log_ex extends Log_base {

	public ArrayList readbody(JSONArray json) {
		ArrayList<String> Log_ex_sql = new ArrayList<String>();
	
		for (int log_ex_i = 0; log_ex_i < json.size(); log_ex_i++) {

			JSONArray ex_temp = (JSONArray) (json.get(log_ex_i));
			for (int log_extmp_i = 0; log_extmp_i < 1; log_extmp_i++) {
//				for (int log_extmp_i = 0; log_extmp_i < ex_temp.size(); log_extmp_i++) {
				
//				System.out.println(ex_temp.get(log_extmp_i).getClass().toString() + "--------exinfo.size");// startTime
				
//				System.out.println(ex_temp.get(log_extmp_i) + "--------exinfo.size");
				LinkedTreeMap  ex_exinfo = (LinkedTreeMap ) (ex_temp.get(log_extmp_i));

//				ArrayList ex_exinfo = (ArrayList) (ex_temp.get(log_extmp_i));
				
//				System.out.println(ex_exinfo.size() + "--------exinfo.size");// startTime
//				System.out.println(ex_exinfo.get(1).getClass().toString() + "--------class");// startTime
//
//				for (int exinfo_i = 1; exinfo_i < ex_exinfo.size(); exinfo_i++) {
//
					
					Log_ex_sql.add(exInfo((LinkedTreeMap) ex_exinfo));
//				}
			}
		}
		return Log_ex_sql;
	}
}
