package au.id.wale.jptv.models.routetypes;

import au.id.wale.jptv.models.TimetableBaseResponse;
import au.id.wale.jptv.models.base.TimetableStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RouteTypes extends TimetableBaseResponse {
    private TimetableStatus status;

    @JsonProperty("route_types")
    private List<RouteType> routeTypes;

    @Override
    public TimetableStatus getStatus() {
        return status;
    }

    public List<RouteType> getRouteTypes() {
        return routeTypes;
    }
}
