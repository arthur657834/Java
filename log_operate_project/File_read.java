package file_operate_release;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;

import file_operate_release.Oracle;

class Log_sql implements Runnable {
	private String sql;
	private Object[] params;

	public void set_tableparams(String sql, Object[] params) {
		this.sql = sql;
		this.params = params;
	}

	public void run()

	{
		// System.out.println(Thread.currentThread().getName() + sql);
		Oracle oracle = new Oracle();
		oracle.executeUpdate(sql, params);

	}
}

public class File_read extends File_preread {

	public static void main(String[] args) {

		String[] del_tables = new String[] { "apm_device", "apm_ex", "apm_crash", "apm_httpinfo", "apm_http",
				"apm_activity", "apm_activityTrace" };
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()) + "  del begin");

		for (String del_table : del_tables) {
			Log_sql log_del_sql = new Log_sql();
			Object[] del_params = new Object[] {};

			String xjz = "truncate table " + del_table;

			log_del_sql.set_tableparams("truncate table " + del_table, del_params);
			new Thread(log_del_sql, del_table).start();

		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		System.out.println(df.format(new Date()) + "  del done");

		ArrayList list = new ArrayList();

		Director_list log_list = new Director_list();

		if (args.length == 0) {
			System.out.println("No command line arguments!");
		} else if (args.length != 1) {
			System.out.println("command arguments is not one!");
		} else {
			System.out.println("Command line arguments were: ");
			for (int j = 0; j < args.length; j++) {
				System.out.println("Argument" + (j + 1) + ":" + args[j]);
				list = log_list.tree(args[j]);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).toString().contains("device")) {

						Oracle dev_oracle = new Oracle();
						Object[] dev_params = new Object[] {};

						JSONArray array_dev = new JSONArray();
						array_dev = readFileByLines2(list.get(i).toString());
						Log_device log_dev = new Log_device();
						ArrayList dev_sql = log_dev.readbody(array_dev);
						for (int dev_sql_i = 0; dev_sql_i < dev_sql.size(); dev_sql_i++) {

							dev_oracle.executeUpdate("insert into apm_device values('"
									+ data_token.get(dev_sql_i).toString() + "'," + dev_sql.get(dev_sql_i) + ")",
									dev_params);
						}
					}

					if (list.get(i).toString().contains("http")) {
						Oracle http_oracle = new Oracle();
						Object[] http_params = new Object[] {};

						JSONArray array_http = new JSONArray();

						array_http = readFileByLines2(list.get(i).toString());
						Log_http log_http = new Log_http();
						log_http.readbody(array_http);
						ArrayList http_sql = log_http.readbody(array_http);
						for (int http_sql_i = 0; http_sql_i < http_sql.size() / (log_http.httpinfo_num+1); http_sql_i++) {

							http_oracle.executeUpdate(
									"insert into apm_http values(" + http_sql.get(http_sql_i).toString() + ")",
									http_params);
						}

						for (int http_sql_i = http_sql.size() / (log_http.httpinfo_num+1); http_sql_i < http_sql
								.size(); http_sql_i++) {

							http_oracle.executeUpdate(
									"insert into apm_httpinfo values(" + http_sql.get(http_sql_i).toString() + ")",
									http_params);

						}
					}

					if (list.get(i).toString().contains("ex")) {
						Oracle ex_oracle = new Oracle();
						Object[] ex_params = new Object[] {};

						JSONArray array_ex = new JSONArray();
						array_ex = readFileByLines2(list.get(i).toString());
						Log_ex log_ex = new Log_ex();
						log_ex.readbody(array_ex);
						ArrayList ex_sql = log_ex.readbody(array_ex);

						for (int ex_sql_i = 0; ex_sql_i < ex_sql.size(); ex_sql_i++) {

							ex_oracle.executeUpdate(
									"insert into apm_ex values(" + ex_sql.get(ex_sql_i).toString() + ")", ex_params);

						}
					}

					if (list.get(i).toString().contains("crash")) {
						Oracle crash_oracle = new Oracle();
						Object[] crash_params = new Object[] {};
						JSONArray array_crash = new JSONArray();
						array_crash = readFileByLines2(list.get(i).toString());
						Log_crash log_crash = new Log_crash();
						log_crash.readbody(array_crash);
						ArrayList crash_sql = log_crash.readbody(array_crash);

						for (int crash_sql_i = 0; crash_sql_i < crash_sql.size(); crash_sql_i++) {

							crash_oracle.executeUpdate(
									"insert into apm_crash values(" + crash_sql.get(crash_sql_i).toString() + ")",
									crash_params);

						}
					}

					if (list.get(i).toString().contains("activity")) {
						Oracle activity_oracle = new Oracle();
						JSONArray array_activity = new JSONArray();
						Object[] activity_params = new Object[] {};

						array_activity = readFileByLines2(list.get(i).toString());
						Log_activity log_activity = new Log_activity();
						log_activity.readbody(array_activity);
						ArrayList activity_sql = log_activity.readbody(array_activity);

						for (int activity_sql_i = 0; activity_sql_i < activity_sql.size()
								/ (log_activity.ActivityTrace_num + 1); activity_sql_i++) {

							activity_oracle.executeUpdate("insert into apm_activity values("
									+ activity_sql.get(activity_sql_i).toString() + ")", activity_params);

						}

						for (int activity_sql_i = activity_sql.size()
								/ (log_activity.ActivityTrace_num + 1); activity_sql_i < activity_sql
										.size(); activity_sql_i++) {

							activity_oracle.executeUpdate("insert into apm_activityTrace values("
									+ activity_sql.get(activity_sql_i).toString() + ")", activity_params);

						}

					}

				}
			}
		}

	}
}
