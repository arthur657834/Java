package file_operate_release;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class Log_device extends Log_base {

	public ArrayList readbody(JSONArray json) {
		ArrayList<String> Log_device_sql=new ArrayList<String>();
		for(int log_device_i=0;log_device_i<json.size();log_device_i++){		
			BigdecimaltoLocalTime BigdecimaltoNormal=new BigdecimaltoLocalTime();
			JSONArray dev_temp=(JSONArray) (json.get(log_device_i));

			ArrayList dev_appinfo=(ArrayList) (dev_temp.get(1));
			ArrayList dev_devinfo=(ArrayList) (dev_temp.get(2));
			appInfo(dev_appinfo);
			devInfo(dev_devinfo);
			String Log_device_sql_temp="to_timestamp('" +BigdecimaltoNormal.normaltoLocalTime(dev_temp.get(0).toString())+ "', 'syyyy-mm-dd hh24:mi:ss.ff') ," +appInfo(dev_appinfo)+"," +devInfo(dev_devinfo);
			Log_device_sql.add(Log_device_sql_temp);

		}
		return Log_device_sql;

	}
}
