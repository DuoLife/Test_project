package json_to_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonToJavaDemo {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("abc");
		list.add("123");
		list.add("ABC");
		Map map = new HashMap();
		map.put("age", "18");
		map.put("name", "xuming");
		map.put("weight", "80");
		map.put("list", list);
		Gson g = new Gson();
		String json = g.toJson(map);
		System.out.println("json:"+json);
		Map jsonMap = g.fromJson(json, Map.class);
		List<String> l = (List) jsonMap.get("list");
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println(jsonMap);
	}
}
