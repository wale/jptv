package au.id.wale.jptv.api;

import au.id.wale.jptv.errors.APIException;
import au.id.wale.jptv.models.routetypes.RouteTypes;
import au.id.wale.jptv.util.APIBuilder;

public class RouteTypesApi {
    private final int developerId;
    private final String developerKey;

    public RouteTypesApi(String developerKey, int developerId) {
        this.developerKey = developerKey;
        this.developerId = developerId;
    }

    public RouteTypes getRouteTypes() throws APIException {
        try {
            return APIBuilder.get(RouteTypes.class, "v3/route_types", this.developerKey, this.developerId);
        } catch(Exception e) {
            throw new APIException("/v3/route_types returned an error.");
        }
    }
    
    
}
