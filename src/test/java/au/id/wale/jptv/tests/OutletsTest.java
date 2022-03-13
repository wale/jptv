package au.id.wale.jptv.tests;

import au.id.wale.jptv.PTVClient;
import au.id.wale.jptv.errors.APIException;
import au.id.wale.jptv.models.outlets.Outlets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test case for the /v3/outlets group")
public class OutletsTest {
    // create shared client with the required keys
    private final PTVClient client = new PTVClient(
            System.getenv("PTV_API_KEY"),
            Integer.parseInt(System.getenv("PTV_DEV_ID"))
    );

    @Test
    @DisplayName("Check if the outlets list is not empty")
    void getOutlets() {
        try {
            Outlets outletList = client.getOutletsApi().getOutlets();
            assertNotEquals(0, outletList.getOutlets().size());
        } catch(Exception e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("Check if a list provided a specified location is not empty")
    void getOutletsByLocation() {
        try {
            Outlets outletList = client.getOutletsApi()
                    .getOutletsByLocation(-37.8116f, 144.9646f); // Melbourne Central station
            assertNotEquals(0, outletList.getOutlets().size());
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
