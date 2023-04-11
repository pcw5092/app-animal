package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {
	// OPEN API 연동해서 데이터 받아와서 파싱해서 컨트롤러로 떤져주면 됨
	public static AnimalResponse getAnimals() {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";
			String queryString = "serviceKey=KF1l%2BnVkdaGwDeDIF%2BQe6LFzV9IPyLMUW36GmiXZJxC%2FZjKe7%2BO2K74RbaVctYEebdPtykdbcyZsKmj%2Bfh%2F1MA%3D%3D&_type=json";
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
}
