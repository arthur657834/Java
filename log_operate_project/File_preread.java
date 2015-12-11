package file_operate_release;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

public class File_preread {
	public static ArrayList<String> data_token= new ArrayList() ;
	public static ArrayList readFileByLines(String fileName) {
		
		
		BufferedReader reader = null;
		try {
			System.out.println("begin read-------"+fileName);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String tempString = null;
			String a = null;
			int line = 1;
			
			
			ArrayList array = new ArrayList<>();
			String last = "";
			if (fileName.contains("device")) {
				last = ",suc,.*(ms).*]";

			} else {
				last = ",suc,.*(ms).*null";
			}
			// �?次读入一行，直到读入null为文件结�?
			while ((tempString = reader.readLine()) != null) {
				Pattern pattern_degin = Pattern.compile(".*Produce.* - send:");
				Matcher matcher_degin = pattern_degin.matcher(tempString);

				Pattern pattern_last = Pattern.compile(last);

				Matcher matcher_last = pattern_last.matcher(tempString);

				Pattern pattern_last_error = Pattern.compile(",err,.*(ms).*null");
				Matcher matcher_last_error = pattern_last_error.matcher(tempString);

				if (matcher_degin.matches()) {
					array.add(line);
				}
				;

				if (matcher_last.matches()) {
					array.add(line);
					int x = tempString.lastIndexOf("[");
					int y = tempString.lastIndexOf("]");
					if ((x != -1) && (y != -1)) {
						 data_token.add(tempString.substring(x + 1, y));
					}
				}
				;

				if (matcher_last_error.matches()) {
					array.remove(array.size() - 1);
				}
				;

				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
			return array;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}

	public static JSONArray readFileByLines2(String fileName) {

		BufferedReader reader = null;
		try {
			ArrayList array = new ArrayList<>();
			array = readFileByLines(fileName);

			ArrayList List = new ArrayList();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String tempString = null;
			String a = "";
			Gson gson = new Gson();
			JSONArray arraylj = new JSONArray();
			JSONArray arraylj_tmp = new JSONArray();
			int line = 1;
			for (int i = 0; i < array.size(); i = i + 2) {
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					// for (int i = 0; i < 2; i++) {
					// int line = i + 1;
					if (line > Integer.parseInt(String.valueOf(array.get(i)))
							&& line < Integer.parseInt(String.valueOf(array.get(i + 1)))) {

						a = a + tempString;
						List.add(tempString);
					}
					line++;
					if (line == Integer.parseInt(String.valueOf(array.get(i + 1)))) {
						break;
					}
				}

				if (fileName.contains("crash")) {
					a="["+a+"]";
				}
				arraylj_tmp = gson.fromJson(a, JSONArray.class);
				arraylj.add(arraylj_tmp);
				a = "";
				
			}

			reader.close();
			return arraylj;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}

		}
		return null;
	}
}
