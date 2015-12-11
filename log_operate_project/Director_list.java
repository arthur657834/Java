package file_operate_release;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Director_list {
	public static void main(String[] args) {

		tree("H:\\Tools");
	}

	// 显示目录的方�?
	public static ArrayList tree(String dir) {
		ArrayList list = new ArrayList();
		File f = new File(dir);
		// 判断传入对象是否为一个文件夹对象
		if (!f.isDirectory()) {
			System.out.println("你输入的不是1个文件夹，请捡查路径是否有误!");
		} else {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				// 判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为�?
				if (t[i].isDirectory()) {
					// System.out.println(t[i].getName()+"\tdir");
					tree(t[i].toString());
				} else {
					// System.out.println(t[i].getName()+"\tFile");
					// 查找以Java�?�?,任意结尾的字符串
					Pattern pattern = Pattern.compile("^\\w+_debug.log");
					Matcher matcher = pattern.matcher(t[i].getName());
					if (matcher.matches()) {

						list.add(dir+"\\"+t[i].getName());
					}
					;
					// 当条件满足时，将返回true，否则返回false

				}
			}
		}
		return list;
	}
}