package au.id.wale.jptv.util;

import au.id.wale.jptv.errors.APIException;
import au.id.wale.jptv.errors.APIHealthException;
import au.id.wale.jptv.models.TimetableBaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class APIBuilder {
    private int developerId;
    private String developerKey;
    private String path;

    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    private final HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("timetableapi.ptv.vic.gov.au");

    public APIBuilder path(String uri) {
        urlBuilder.addPathSegment(uri);
        this.path = uri;
        return this;
    }

    public APIBuilder addQueryParam(String key, String value) {
        urlBuilder.addQueryParameter(key, value);
        return this;
    }

    public APIBuilder developerId(int developerId) {
        this.developerId = developerId;
        return this;
    }

    public APIBuilder developerKey(String developerKey) {
        this.developerKey = developerKey;
        return this;
    }

    /**
     * Builds the API URL for PTV's public transport API.
     * Adapted from Public Transport Victoria's official documentation on generating
     * a signature for use of the URL.
     * @return A String object that represents the URL with the included signature.
     * @author Public Transport Victoria
     * @author Duale Siad
     */
    public HttpUrl build() throws Exception
    {
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        // the slash is required in this line because OkHTTP is dumb.
        // that and the URI needed to generate the signature needs a preceding slash
        String uriWithDeveloperID = "/" + this.path + (this.path.contains("?") ? "&" : "?") +
                "devid=" + developerId;
        SecretKey key = new SecretKeySpec(developerKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(key);
        byte[] signatureBytes = mac.doFinal(uriWithDeveloperID.getBytes());
        StringWriter writer = new StringWriter();

        for (byte signatureByte : signatureBytes)
        {
            writer.append(String.format("%02x", signatureByte));
        }

        HttpUrl url = urlBuilder.addQueryParameter("devid", Integer.toString(this.developerId))
                .addQueryParameter("signature", writer.toString())
                .build();
        return url;
    }

    public static <T extends TimetableBaseResponse> T get(Class<T> type,
                                                          HttpUrl url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response;
        try {
           response = client.newCall(request).execute();
           String body = Objects.requireNonNull(response.body()).string();
           try {
                T parsed = mapper.readValue(body, type);
                if(parsed.getStatus().getHealth() != 1) throw new APIHealthException("API failed to respond.");
                return parsed;
           } catch (Exception e) {
               throw new APIException(e.getMessage());
           }
        } catch (Exception e) {
            throw new APIException(e.getMessage());
        }
    }
}
