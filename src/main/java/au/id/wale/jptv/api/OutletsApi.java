package au.id.wale.jptv.api;

import au.id.wale.jptv.errors.APIException;
import au.id.wale.jptv.models.outlets.Outlets;
import au.id.wale.jptv.util.APIBuilder;
import okhttp3.HttpUrl;

public class OutletsApi {
    private final int developerId;
    private final String developerKey;

    public OutletsApi(String developerKey, int developerId) {
        this.developerId = developerId;
        this.developerKey = developerKey;
    }

    /**
     * Lists a set of ticket outlets (which include electrical Myki machines and some stores).
     * @return A {@link Outlets} object, that provides a list of outlets
     * @throws APIException
     * @author Duale Siad
     */
    public Outlets getOutlets() throws APIException {
        try {
            HttpUrl url = new APIBuilder()
                    .path("v3/outlets")
                    .developerId(this.developerId)
                    .developerKey(this.developerKey)
                    .build();
            return APIBuilder.get(Outlets.class, url);
        } catch(Exception e) {
            throw new APIException("/v3/outlets returned an error.", e);
        }
    }

    /**
     * Lists a set of ticket outlets (which include electrical Myki machines and some stores).
     * <br>
     * This method allows you to filter the results and provide a maximum amount.
     * @param maxResults Integer representing the maximum amount of results
     * @return A {@link Outlets} object, that provides a list of outlets
     * @throws APIException
     * @author Duale Siad
     */
    public Outlets getOutlets(int maxResults) throws APIException {
        try {
            HttpUrl url = new APIBuilder()
                    .path("v3/outlets")
                    .developerId(this.developerId)
                    .developerKey(this.developerKey)
                    .addQueryParam("max_results", Integer.toString(maxResults))
                    .build();
            return APIBuilder.get(Outlets.class, url);
        } catch(Exception e) {
            throw new APIException("/v3/outlets returned an error.", e);
        }
    }

    /**
     * Lists a set of ticket outlets (which include electrical Myki machines and some stores), and allows you to
     * filter by location.
     * <br>
     * @param latitude Float representing the latitude
     * @param longitude Float representing the longitude
     * @return A {@link Outlets} object, that provides a list of outlets
     * @throws APIException
     * @author Duale Siad
     */
    public Outlets getOutletsByLocation(float latitude, float longitude) throws APIException {
        try {
            HttpUrl url = new APIBuilder()
                    .path(String.format("v3/outlets/location/%f,%f", latitude, longitude))
                    .developerId(this.developerId)
                    .developerKey(this.developerKey)
                    .build();
            return APIBuilder.get(Outlets.class, url);
        } catch(Exception e) {
            throw new APIException("/v3/outlets returned an error.", e);
        }
    }
}
