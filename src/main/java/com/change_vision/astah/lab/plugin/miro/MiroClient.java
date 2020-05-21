package com.change_vision.astah.lab.plugin.miro;

import com.change_vision.astah.lab.plugin.miro.authorization.Authorization;
import com.change_vision.astah.lab.plugin.miro.board.BoardList;
import com.change_vision.astah.lab.plugin.miro.widget.Collection;
import com.change_vision.astah.lab.plugin.miro.widget.IWritableWidget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class MiroClient {
    private MiroAuth miroAuth;
    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public MiroClient() {
        auth();
    }

    public void auth() {
        // TODO: Use OAuth 2.0
        try {
            final String user_home = System.getProperty("user.home");
            final Path authFilePath = Paths.get(user_home, ".astah-miro.json");
            final ObjectMapper mapper = new ObjectMapper();
            miroAuth = mapper.readValue(authFilePath.toFile(), MiroAuth.class);
            if (miroAuth.getUnsafeSSL() == null) {
                this.client = new OkHttpClient();
            } else {
                this.client = getUnsafeOkHttpClient();
            }
        } catch (IOException e) {
            e.printStackTrace();
            miroAuth = null;
        }
    }

    private String getAPIPathBase() {
        return "https://api.miro.com/v1/boards/";
    }

    public Collection findMiroWidgets(final String boardId, final String type) throws IOException {
        final HttpUrl.Builder httpBuilder = HttpUrl.parse(getAPIPathBase() + boardId + "/widgets").newBuilder()
                .addQueryParameter("widgetType", type);
        final Request request = new Request.Builder()
                .header("Authorization", "Bearer " + miroAuth.getToken())
                .url(httpBuilder.build())
                .get().build();
        final Response response = client.newCall(request).execute();
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body().string(), Collection.class);
    }

    public BoardList getBoardList(final String teamId) throws IOException {
        final HttpUrl.Builder httpBuilder = HttpUrl.parse("https://api.miro.com/v1/teams/" + teamId + "/boards").newBuilder();
        final Request request = new Request.Builder()
                .header("Authorization", "Bearer " + miroAuth.getToken())
                .url(httpBuilder.build())
                .get().build();
        final Response response = client.newCall(request).execute();
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body().string(), BoardList.class);
    }

    public Authorization getAuthorization() throws IOException {
        final HttpUrl.Builder httpBuilder = HttpUrl.parse("https://api.miro.com/v1/oauth-token").newBuilder();
        final Request request = new Request.Builder()
                .header("Authorization", "Bearer " + miroAuth.getToken())
                .url(httpBuilder.build())
                .get().build();
        final Response response = client.newCall(request).execute();
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body().string(), Authorization.class);
    }

    public IWritableWidget createMiroWidget(final String boardId, final IWritableWidget newWidget) {
        try {
            final RequestBody requestBody = RequestBody.create(newWidget.toJSON(), JSON);
            final Request request = new Request.Builder()
                    .url(getAPIPathBase() + boardId + "/widgets")
                    .header("Authorization", "Bearer " + miroAuth.getToken())
                    .post(requestBody)
                    .build();
            final Response response = client.newCall(request).execute();
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().string(), newWidget.getClass());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IWritableWidget updateMiroWidget(final String boardId, final String id, final IWritableWidget updateWidget) {
        try {
            final String body = updateWidget.toPartialJSON();
            final RequestBody requestBody = RequestBody.create(body, JSON);

            final Request request = new Request.Builder()
                    .url(getAPIPathBase() + boardId + "/widgets/" + id)
                    .header("Authorization", "Bearer " + miroAuth.getToken())
                    .patch(requestBody)
                    .build();
            final Response response = client.newCall(request).execute();
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().string(), updateWidget.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
