package au.id.wale.jptv;

import au.id.wale.jptv.api.OutletsApi;
import au.id.wale.jptv.api.RouteTypesApi;

public class PTVClient {
    private int developerId;
    private String developerKey;

    /**
     * Instantiates the PTV client with pre-defined values.
     * @param developerKey The developer key provided by PTV (e.g. '9c132d31-6a30-4cac-8d8b-8a1970834799')
     * @param developerId The developer ID provided by PTV.
     * @author Duale Siad
     */
    public PTVClient(String developerKey, int developerId) {
        this.developerId = developerId;
        this.developerKey = developerKey;
    }

    /**
     * An API group for the `/v3/route_types` endpoint.
     * <br>
     * This endpoint lists four route types (trains, trams, v-line trains, buses).
     * @return A {@link RouteTypesApi} object.
     * @author Duale Siad
     */
    public RouteTypesApi getRouteTypesApi() {
        return new RouteTypesApi(this.developerKey, this.developerId);
    }

    /**
     * An API group for the `/v3/outlets` endpoint.
     * <br>
     * This endpoint returns locations of various myki machines and stores, as well as paper outlets.
     * @return A {@link OutletsApi} object.
     * @author Duale Siad
     */
    public OutletsApi getOutletsApi() {
        return new OutletsApi(this.developerKey, this.developerId);
    }
}
