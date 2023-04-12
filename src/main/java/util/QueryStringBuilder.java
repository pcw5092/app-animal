package util;

import java.util.Map;

public class QueryStringBuilder {
	// Map 을 넘긴다.
	public static String build(Map<String, String> parmas) {
		// 요청인자를 보내기 위한 queryString 을 만들때

		// Map 으로 넘어온 값을 toString 을 replaceAll 이용해서 , 를 & 연결
		String str = parmas.toString();
		str = str.replaceAll(", ", "&");

		return str.substring(1, str.length() - 1);

	}
}
