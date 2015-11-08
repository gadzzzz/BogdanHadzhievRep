package org.sqlcommand;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputFormatter {
	private int symbolCount;
	public OutputFormatter(int s) {
		this.symbolCount=s;
	}
	public String formatResult(List<Map<String, Object>> result){
		if(result.size()>0){
			String output = "\n\r|";
			Set<String> keys = result.get(0).keySet();
			Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext()) {
				output = output + formatValue(iterator.next())+"|";
			}
			int size = output.length();
			output = output + "\n\r";
			char[] chars = new char[size];
			Arrays.fill(chars, '-');
			output = output+ new String(chars)+"\n\r";
			iterator = keys.iterator();
			for (Map<String, Object> map : result) {
				output = output + "|";
				iterator = keys.iterator();
				while(iterator.hasNext()) {
					output = output + formatValue(String.valueOf(map.get(iterator.next())))+"|";
				}
				output = output + "\n\r";
			}
			return output;
		}
		return "No rows selected";
	}
	public String formatValue(String tmp){
		String res = "";
		if(tmp.length()>symbolCount)
			res = tmp.substring(0, symbolCount);
		else
			res = String.format("%-"+symbolCount+"s", tmp);
		return res;
	}
}
