package au.id.wale.jptv.tests;

import au.id.wale.jptv.PTVClient;
import au.id.wale.jptv.models.routetypes.RouteTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test case for the /v3/route_types group")
public class RouteTypesTest {
    // create shared client with the required keys
    private final PTVClient client = new PTVClient(
            System.getenv("PTV_API_KEY"),
            Integer.parseInt(System.getenv("PTV_DEV_ID"))
    );

    @Test
    @DisplayName("Checks if the route type list is not empty.")
    void getRouteTypes() {
        try {
            RouteTypes types = client.getRouteTypesApi().getRouteTypes();
            assertNotEquals(0, types.getRouteTypes().size());
        } catch(Exception e) {
            fail(e);
        }
    }
}
