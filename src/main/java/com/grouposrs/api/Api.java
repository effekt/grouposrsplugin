package com.grouposrs.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grouposrs.GroupOSRSConfig;
import com.grouposrs.player.Player;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.RuneLiteProperties;
import net.runelite.client.config.ConfigManager;
import okhttp3.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Singleton
public class Api {
  @Inject
  Client client;
  @Inject
  GroupOSRSConfig config;
  @Inject
  private Gson gson;
  @Inject
  private OkHttpClient okHttpClient;
  @Inject
  private Player player;
  @Inject
  private ConfigManager configManager;

  private static final String PUBLIC_BASE_URL = "http://localhost:3000/api";
  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static final String USER_AGENT = "GroupOSRS/0.0.1 " + "RuneLite/" + RuneLiteProperties.getVersion();

  public void postToApi() {
    Map<String, Object> updates = new HashMap<>();

    RequestBody body = RequestBody.create(JSON, gson.toJson(player.getPlayer()));
     log.info("{}", gson.toJson(player.getPlayer()));
    Request request = new Request.Builder()
        .url(PUBLIC_BASE_URL + "/player")
        .header("Authorization", config.loginToken())
//        .header("User-Agent", USER_AGENT)
        .post(body)
        .build();
    Call call = okHttpClient.newCall(request);

    try (Response _response = call.execute()) {
    } catch(Exception _exception) {}
  }

  /**
   * Attempts to validate user credentials
   *
   * @param uuid
   * @param secretPhrase
   */
  public JsonObject logIn(String uuid, String secretPhrase) {
    Map<String, Object> credentials = new HashMap<>();
    credentials.put("uuid", uuid);
    credentials.put("secret_phrase", secretPhrase);

    RequestBody body = RequestBody.create(JSON, gson.toJson(credentials));
    Request request = new Request.Builder()
        .url(PUBLIC_BASE_URL + "/auth/login")
        .post(body)
        .build();
    Call call = okHttpClient.newCall(request);

    try (Response response = call.execute()) {
      String responseBody = response.body().string();
      log.info(responseBody);
      return new Gson().fromJson(responseBody, JsonObject.class);
    } catch(Exception exception) {
      log.info(exception.getMessage());
    }

    return null;
  }
}
