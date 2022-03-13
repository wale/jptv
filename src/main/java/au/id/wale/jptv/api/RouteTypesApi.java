package au.id.wale.jptv.api;

import au.id.wale.jptv.errors.APIException;
import au.id.wale.jptv.models.routetypes.RouteTypes;
import au.id.wale.jptv.util.APIBuilder;
import okhttp3.HttpUrl;

/**
 * The API group for the /v3/route_types endpoint.
 *
 * @author Duale Siad
 */
public class RouteTypesApi {
    private final int developerId;
    private final String developerKey;

    public RouteTypesApi(String developerKey, int developerId) {
        this.developerKey = developerKey;
        this.developerId = developerId;
    }

    /**
     * Gets a list of route types used in the timetable API.
     * Usually [TRAIN, TRAM, BUS, VLINE]
     * <br>
     * @return A {@link RouteTypes} object, detailing each type.
     * @throws APIException
     */
    public RouteTypes getRouteTypes() throws APIException {
        try {
            HttpUrl url = new APIBuilder()
                    .path("v3/route_types")
                    .developerId(this.developerId)
                    .developerKey(this.developerKey)
                    .build();
            return APIBuilder.get(RouteTypes.class, url);
        } catch(Exception e) {
            throw new APIException("/v3/route_types returned an error.", e);
        }
    }
}
