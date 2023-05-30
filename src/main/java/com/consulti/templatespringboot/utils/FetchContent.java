package com.consulti.templatespringboot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class FetchContent {

  ObjectMapper objectMapper = new ObjectMapper();
  HttpClient httpClient = HttpClient.newHttpClient();

  public JsonNode[] fetchContent(List<String> endpoints) throws Exception {
    JsonNode[] responses = new JsonNode[endpoints.size()];

    for (int i = 0; i < endpoints.size(); i++) {
      String url = endpoints.get(i);
      HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(url))
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .method(HttpMethod.GET.name(), HttpRequest.BodyPublishers.noBody())
        .build();

      HttpResponse<String> response = httpClient.send(
        request,
        HttpResponse.BodyHandlers.ofString()
      );
      String responseBody = response.body();
      JsonNode jsonNode = objectMapper.readTree(responseBody);
      responses[i] = jsonNode;
    }

    return responses;
  }
}
