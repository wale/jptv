package au.id.wale.jptv.models.routetypes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteType {
    @JsonProperty("route_type_name")
    public String routeTypeName;

    @JsonProperty("route_type")
    public int routeTypeId;

    public String getRouteTypeName() {
        return routeTypeName;
    }

    public int getRouteTypeId() {
        return routeTypeId;
    }
}
