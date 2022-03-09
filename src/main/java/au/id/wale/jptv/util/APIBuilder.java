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
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Builds the API URL for PTV's public transport API.
     * Adapted from Public Transport Victoria's official documentation on generating
     * a signature for use of the URL.
     * @param developerKey The developer key provided by PTV (e.g. '9c132d31-6a30-4cac-8d8b-8a1970834799')
     * @param uri Request URI with parameters. Must not start with a forward slash. (e.g. 'v3/disruptions')
     * @param developerId The developer ID provided by PTV.
     * @return A String object that represents the URL with the included signature.
     * @author Public Transport Victoria
     * @author Duale Siad
     */
    public static String buildApiUrl(final String developerKey, final String uri,
                                     final int developerId) throws Exception
    {
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        // the slash is required in this line because OkHTTP is dumb.
        // that and the URI needed to generate the signature needs a preceding slash
        String uriWithDeveloperID = "/" + uri + (uri.contains("?") ? "&" : "?") +
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

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("timetableapi.ptv.vic.gov.au")
                .addPathSegments(uri)
                .addQueryParameter("devid", Integer.toString(developerId))
                .addQueryParameter("signature", writer.toString())
                .build();
        return url.toString();
    }

    public static <T extends TimetableBaseResponse> T get(Class<T> type,
                                                          String endpoint,
                                                          String developerKey,
                                                          int developerId) throws Exception {
        Request request = new Request.Builder()
                .url(buildApiUrl(developerKey, endpoint, developerId))
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
