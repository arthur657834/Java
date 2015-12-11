package file_operate_release;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.internal.LinkedTreeMap;

public class Log_base {

	public String exInfo(LinkedTreeMap array) {

		BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();

		LinkedTreeMap exInfo_temp = (LinkedTreeMap) (array.get("envInfo"));
		envInfo(exInfo_temp);

		LinkedTreeMap exception_temp = (LinkedTreeMap) (array.get("exception"));
		exception(exception_temp);
		ArrayList threads_temp = (ArrayList) (array.get("threads"));
		threads(threads_temp);

		String exInfo_sql = "'" + array.get("uuid") + "',to_timestamp('"
				+ BigdecimaltoNormal.normaltoLocalTime(array.get("timestamp").toString().toString())
				+ "', 'syyyy-mm-dd hh24:mi:ss.ff'),'" + datatokenInfo((ArrayList) array.get("dataToken")) + "','" + array.get("usageTime") + "','"
				+ array.get("userMessage") + "','" + array.get("countryCode") + "','" + array.get("regionCode") + "','"
				+ array.get("networkType") + "','" + array.get("simOperator") + "','" + array.get("location") + "','"
				+ envInfo(exInfo_temp) + "','" + exception(exception_temp) + "','" + threads(threads_temp) + "','"
				+ array.get("breadcrumbs") + "','" + array.get("activityHistory") + "','" + array.get("logMessage")
				+ "'";
		return exInfo_sql;
	}

	public String crashInfo(LinkedTreeMap array) {
		BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();

		LinkedTreeMap appInfo_LinkedTreeMap = (LinkedTreeMap) (array.get("appInfo"));
		appInfo_LinkedTreeMap(appInfo_LinkedTreeMap);

		ArrayList devInfo_temp = (ArrayList) (array.get("devInfo"));
		devInfo(devInfo_temp);

		LinkedTreeMap exInfo_temp = (LinkedTreeMap) (array.get("envInfo"));
		envInfo(exInfo_temp);

		LinkedTreeMap exception_temp = (LinkedTreeMap) (array.get("exception"));
		exception(exception_temp);

		ArrayList threads_temp = (ArrayList) (array.get("threads"));
		threads(threads_temp);

		String crashInfo_sql = "'" + array.get("uuid") + "',to_timestamp('"
				+ BigdecimaltoNormal.normaltoLocalTime(array.get("timestamp").toString().toString())
				+ "', 'syyyy-mm-dd hh24:mi:ss.ff'),'" + array.get("appKey") + "','"
				+ datatokenInfo((ArrayList) array.get("dataToken")) + "','" + array.get("usageTime") + "','"
				+ appInfo_LinkedTreeMap(appInfo_LinkedTreeMap) + "," + devInfo(devInfo_temp) + ",'"
				+ envInfo(exInfo_temp) + "','" + exception(exception_temp) + "','"

				+ threads(threads_temp) + "','" + array.get("breadcrumbs") + "','" + array.get("activityHistory")
				+ "','" + array.get("logMessage") + "'";
		return crashInfo_sql;

	}

	public String envInfo(LinkedTreeMap array) {
		String envInfo_sql = array.get("memoryUsage") + "','" + array.get("cpuUsage") + "','" + array.get("orientation")
				+ "','" + array.get("disk") + "','" + array.get("extDisk") + "','" + array.get("supervisor");
		return envInfo_sql;

	}

	public String exception(LinkedTreeMap array) {

		String exception_sql = array.get("name") + "','" + array.get("cause");
		return exception_sql;
	}

	public String threads(ArrayList array) {

		String threads_sql = array.get(0).toString();
		return threads_sql;
	}

	public String appInfo(ArrayList array) {

		String appInfo_sql = "'" + array.get(0).toString() + "','" + array.get(1) + "','" + array.get(2) + "'";
		return appInfo_sql;
	}

	public String appInfo_LinkedTreeMap(LinkedTreeMap array) {

		String appInfo_LinkedTreeMap_sql = array.get("appName").toString() + "','" + array.get("appVersion") + "','"
				+ array.get("processId") + "'";
		return appInfo_LinkedTreeMap_sql;
	}

	public String devInfo(ArrayList array) {

		String devInfo_sql = "'" + array.get(0).toString() + "','" + array.get(1) + "','" + array.get(2) + "','"
				+ array.get(3) + "','" + array.get(4) + "','" + array.get(5) + "','" + array.get(6) + "','"
				+ array.get(7) + "','" + array.get(8) + "','" + array.get(9) + "','" + array.get(10) + "','"
				+ array.get(11).toString() + "','" + array.get(12) + "','" + array.get(13) + "'";
		return devInfo_sql;
	}

	public String devInfo_LinkedTreeMap(LinkedTreeMap array) {

		String devInfo_LinkedTreeMap_sql = "'" + array.get(0).toString() + "','" + array.get(1) + "','" + array.get(2)
				+ "'" + "','" + array.get(3) + "','" + array.get(4) + "','" + array.get(5) + "','" + array.get(6)
				+ "','" + array.get(7) + "','" + array.get(8) + "','" + array.get(9) + "','" + array.get(10) + "','"
				+ array.get(11).toString() + "','" + array.get(12) + "','" + array.get(13) + "'";
		return devInfo_LinkedTreeMap_sql;
	}

	public String httpInfo(ArrayList array) {
		BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();

		String httpInfo_sql = array.get(0).toString() + "',to_timestamp('"
				+ BigdecimaltoNormal.normaltoLocalTime(array.get(1).toString()) + "', 'syyyy-mm-dd hh24:mi:ss.ff'),'"
				+ array.get(2) + "','" + array.get(3) + "','" + array.get(4) + "','" + array.get(5) + "','"
				+ array.get(6) + "','" + array.get(7) + "','" + array.get(8) + "','" + array.get(9) + "','"
				+ array.get(10) + "'";
		return httpInfo_sql;// http头参数，http错误时才有�??
	}

	public String datatokenInfo(ArrayList array) {
		BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();
		return BigdecimaltoNormal.bigdecimaltoNormal(array.get(0).toString()) + ","
				+ BigdecimaltoNormal.bigdecimaltoNormal(array.get(1).toString());

	}

	public String activityInfo(ArrayList array) {
		BigdecimaltoLocalTime BigdecimaltoNormal = new BigdecimaltoLocalTime();

		return BigdecimaltoNormal.bigdecimaltoNormal(array.get(0).toString()) + ","
				+ BigdecimaltoNormal.bigdecimaltoNormal(array.get(1).toString());

	}

	public String ActivityTrace(ArrayList array) {
		String ActivityTrace_tmp_sql="";
		for (int i=0;i<array.size();i++){
			ActivityTrace_tmp_sql=ActivityTrace_tmp_sql+"-"+array.get(i);
		}
		return ActivityTrace_tmp_sql;

	}
}
