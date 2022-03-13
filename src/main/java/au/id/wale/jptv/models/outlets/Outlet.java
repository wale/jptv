package au.id.wale.jptv.models.outlets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Outlet {
    @JsonProperty("outlet_distance")
    public float outletDistance;

    @JsonProperty("outlet_slid_spid")
    public String outletSlidSpid;

    @JsonProperty("outlet_name")
    public String outletName;

    @JsonProperty("outlet_business")
    public String outletBusiness;

    @JsonProperty("outlet_latitude")
    public float outletLatitude;

    @JsonProperty("outlet_longitude")
    public float outletLongitude;

    @JsonProperty("outlet_suburb")
    public String outletSuburb;

    @JsonProperty("outlet_postcode")
    public int outletPostcode;

    @JsonProperty("outlet_notes")
    public String outletNotes;

    @JsonProperty("outlet_business_hour_mon")
    public String outletHoursMon;

    @JsonProperty("outlet_business_hour_tue")
    public String outletHoursTue;

    @JsonProperty("outlet_business_hour_wed")
    public String outletHoursWed;

    @JsonProperty("outlet_business_hour_thur")
    public String outletHoursThu;

    @JsonProperty("outlet_business_hour_fri")
    public String outletHoursFri;

    @JsonProperty("outlet_business_hour_sat")
    public String outletHoursSat;

    @JsonProperty("outlet_business_hour_sun")
    public String outletHoursSun;

    public String getOutletSlidSpid() {
        return outletSlidSpid;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getOutletBusiness() {
        return outletBusiness;
    }

    public float getOutletLatitude() {
        return outletLatitude;
    }

    public float getOutletLongitude() {
        return outletLongitude;
    }

    public String getOutletSuburb() {
        return outletSuburb;
    }

    public int getOutletPostcode() {
        return outletPostcode;
    }

    public String getOutletNotes() {
        return outletNotes;
    }

    public float getOutletDistance() {
        return outletDistance;
    }

    public String getOutletHoursMon() {
        return outletHoursMon;
    }

    public String getOutletHoursTue() {
        return outletHoursTue;
    }

    public String getOutletHoursWed() {
        return outletHoursWed;
    }

    public String getOutletHoursThu() {
        return outletHoursThu;
    }

    public String getOutletHoursFri() {
        return outletHoursFri;
    }

    public String getOutletHoursSat() {
        return outletHoursSat;
    }

    public String getOutletHoursSun() {
        return outletHoursSun;
    }
}
