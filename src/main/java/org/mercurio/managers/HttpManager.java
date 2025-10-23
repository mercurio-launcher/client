package org.mercurio.managers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.mercurio.config.AuthConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpManager {

    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();


    public record AuthResult(String userCode, String deviceCode) {  }
    public record AuthTokenResult(String authToken) {  }
    public record AuthXboxResult(String xboxLiveToken, String userHash) {  }
    public record AuthXstsResult(String userHash) {  }
    public record AuthMinecraftResult(String minecraftAccessToken) {  }

    public static AuthResult getMicrosoftAuthorizationToken() throws IOException, InterruptedException {

        Map<String, String> formData = Map.of(
                "client_id", AuthConfig.CLIENT_ID,
                "scope", AuthConfig.SCOPE
        );

        String formBody = createFormBody(formData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AuthConfig.MS_AUTH_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);
            String deviceCode = responseJson.get("device_code").getAsString();
            String userCode = responseJson.get("user_code").getAsString();

            System.out.println("Code extracted: " + response.body());
            System.out.println("Code extracted: " + userCode);
            System.out.println("Device Code extracted: " + deviceCode);

            return new AuthResult(userCode, deviceCode);

        } else {
            throw new IOException("Could not connect to microsoft.");
        }

    }

    public static AuthTokenResult fetchMicrosoftToken(String deviceCode) throws IOException, InterruptedException {
        Map<String, String> formData = Map.of(
                "client_id", AuthConfig.CLIENT_ID,
                "grant_type", AuthConfig.GRANT_TYPE,
                "device_code", deviceCode
        );

        String formBody = createFormBody(formData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AuthConfig.MS_TOKEN_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);

        if (response.statusCode() == 200) {
            String accessToken = responseJson.get("access_token").getAsString();
            return new AuthTokenResult(accessToken);
        } else {
            throw new IOException("Error obtaining the token: " + response.body());
        }

    }

    public static AuthXboxResult fetchXboxTokens(String msToken) throws IOException, InterruptedException {
        Map<String, Object> properties = Map.of(
                "AuthMethod", "RPS",
                "SiteName", "user.auth.xboxlive.com",
                "RpsTicket", "d=" + msToken
        );

        Map<String, Object> requestBodyMap = Map.of(
                "Properties", properties,
                "RelyingParty", "http://auth.xboxlive.com",
                "TokenType", "JWT"
        );
        String jsonBody = gson.toJson(requestBodyMap);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AuthConfig.XBL_AUTH_URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);

        if (response.statusCode() == 200) {
            System.out.println("Autenticación Xbox Live exitosa.");

            String xblToken = responseJson.get("Token").getAsString();
            String userHash = responseJson.getAsJsonObject("DisplayClaims")
                                       .getAsJsonArray("xui")
                                       .get(0).getAsJsonObject()
                                        .get("uhs").getAsString();

            return new AuthXboxResult(xblToken, userHash);
        } else {
            throw new IOException("Error at Xbox Live: " + response.body());
        }

    }

    public static AuthXstsResult fetchXstsToken(String xboxToken) throws IOException, InterruptedException {
        Map<String, Object> properties = Map.of(
                "UserTokens", List.of(xboxToken),
                "SandboxId", "RETAIL"
        );

        Map<String, Object> requestBodyMap = Map.of(
                "Properties", properties,
                "RelyingParty", "rp://api.minecraftservices.com/",
                "TokenType", "JWT"
        );

        String jsonBody = gson.toJson(requestBodyMap);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AuthConfig.XSTS_AUTH_URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);

        if (response.statusCode() == 200) {

            String userHash = responseJson.getAsJsonObject("DisplayClaims")
                            .getAsJsonArray("xui").get(0).getAsJsonObject()
                            .get("uhs").getAsString();

            return new AuthXstsResult(userHash);
        } else {
            throw new IOException("Error at XSTS: " + response.body());
        }

    }

    public static AuthMinecraftResult fetchMinecraftProfile(String uhs, String xstsToken) throws IOException, InterruptedException {
        String identityTokenValue = String.format("XBL3.0 x=%s;%s", uhs, xstsToken);
        Map<String, String> requestBodyMap = Map.of(
                "identityToken", identityTokenValue
        );
        String jsonBody = gson.toJson(requestBodyMap);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AuthConfig.MC_LOGIN_URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);

        if (response.statusCode() == 200) {
            String minecraftAccessToken = responseJson.get("access_token").getAsString();
            return new AuthMinecraftResult(minecraftAccessToken);
        } else {
            throw new IOException("Error login with xbox: " + response.body());
        }
    }

    private static String createFormBody(Map<String, String> formData) {
        return formData.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
