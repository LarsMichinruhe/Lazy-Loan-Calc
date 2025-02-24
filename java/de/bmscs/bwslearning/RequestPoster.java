package de.bmscs.bwslearning;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RequestPoster {

	// one instance, reuse
	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public static void main(String[] args) throws Exception {
        RequestPoster obj = new RequestPoster();
        obj.sendPost();
    }

    private void sendPost() throws Exception {
        String url = "http://localhost:8080/api/loan/calculate";
        String json = """
            {
                "principal": 10000,
                "annualInterestRate": 5.0,
                "years": 0,
                "monthlyPayment": 856.07
            }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        Object jsonResponse = mapper.readValue(response.body(), Object.class);
        ObjectWriter writer = mapper.writer(SerializationFeature.INDENT_OUTPUT);
        String prettyJson = writer.writeValueAsString(jsonResponse);

        System.out.println("Request: " + request.toString());
        System.out.println(json);
        System.out.println("Response: " + prettyJson);
    }
}