package file_operate_release;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class Log_activity extends Log_base {
	public int ActivityTrace_num;

	public ArrayList readbody(JSONArray json) {
		ArrayList<String> Log_activity_sql = new ArrayList<String>();

		ArrayList<String> Log_activityTrace_sql = new ArrayList<String>();

		ArrayList<String> Log_activity_sql_all = new ArrayList<String>();

		for (int log_activity_i = 0; log_activity_i < json.size(); log_activity_i++) {
			BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();
			JSONArray activity_temp = (JSONArray) (json.get(log_activity_i));

			String Log_activity_sql_temp = "'" + datatokenInfo((ArrayList) activity_temp.get(0)) + "',to_timestamp('"
					+ BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(1).toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ,to_timestamp('"
					+ BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(2).toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ,'" + activity_temp.get(3) + "','" + activity_temp.get(4) + "','"
					+ activity_temp.get(5) + "','" + activity_temp.get(6) + "','" + activity_temp.get(7) + "','"
					+ activity_temp.get(8) + "'";
			Log_activity_sql.add(Log_activity_sql_temp);

			String a[] = ActivityTrace((ArrayList) activity_temp.get(8)).split("-");

			String Log_activityTrace_sql_temp = "'" + datatokenInfo((ArrayList) activity_temp.get(0))
					+ "',to_timestamp('" + BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(1).toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ,to_timestamp('"
					+ BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(2).toString())
					+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ";
			ActivityTrace_num = a.length - 1;
			String xy = "";
			for (int lj = 1; lj < a.length; lj++) {

				String b[] = a[lj].toString().split(",");
				String c[] = new String[b.length];

				for (int lj1 = 0; lj1 < b.length; lj1++) {

					if (b[lj1].contains("[")) {
						c[lj1] = b[lj1].replaceAll("\\[", "");

					} else if (b[lj1].contains("]")) {
						c[lj1] = b[lj1].replaceAll("\\]", "");

					} else {
						c[lj1] = b[lj1];
					}
					xy = xy + ",'" + c[lj1] + "'";

				}
				Log_activityTrace_sql_temp = Log_activityTrace_sql_temp + xy;
				Log_activityTrace_sql.add(Log_activityTrace_sql_temp);
				xy = "";

				Log_activityTrace_sql_temp = "'" + datatokenInfo((ArrayList) activity_temp.get(0)) + "',to_timestamp('"
						+ BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(1).toString())
						+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ,to_timestamp('"
						+ BigdecimaltoNormal.normaltoLocalTime(activity_temp.get(2).toString())
						+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ";

			}

		}

		Log_activity_sql_all.addAll(Log_activity_sql);
		Log_activity_sql_all.addAll(Log_activityTrace_sql);
		return Log_activity_sql_all;

	}
}
