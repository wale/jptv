package au.id.wale.jptv.models.outlets;

import au.id.wale.jptv.models.TimetableBaseResponse;
import au.id.wale.jptv.models.base.TimetableStatus;

import java.util.List;

public class Outlets extends TimetableBaseResponse {
    private TimetableStatus status;
    private List<Outlet> outlets;

    public List<Outlet> getOutlets() {
        return outlets;
    }

    @Override
    public TimetableStatus getStatus() {
        return status;
    }
}