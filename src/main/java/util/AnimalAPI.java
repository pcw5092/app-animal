package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.animal.AnimalItem;
import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {
	// OPEN API 연동해서 데이터 받아와서 파싱해서 컨트롤러로 떤져주면 됨
	public synchronized static AnimalResponse getAnimals(String upkind, String upr_cd, String pageNo, String bgnde,
			String endde) {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";
			Map<String, String> params = new LinkedHashMap<>();
			params.put("serviceKey",
					"KF1l%2BnVkdaGwDeDIF%2BQe6LFzV9IPyLMUW36GmiXZJxC%2FZjKe7%2BO2K74RbaVctYEebdPtykdbcyZsKmj%2Bfh%2F1MA%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "17");
			params.put("upkind", upkind == null ? "" : upkind);
			params.put("upr_cd", upr_cd == null ? "" : upr_cd);
			params.put("pageNo", pageNo == null ? "" : pageNo);
			params.put("bgnde", bgnde == null ? "" : bgnde);
			params.put("endde", endde == null ? "" : endde);

			String queryString = QueryStringBuilder.build(params);
			
			
			URI uri = new URI(target + "?" + queryString);

			// HttpClient 객체를 활용하는 방식
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			AnimalResponseResult responseResult = gson.fromJson(response.body(), AnimalResponseResult.class);

			return responseResult.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static AnimalItem findByDesertionNo(String no) {
		
		return null;
	}
}
