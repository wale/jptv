package au.id.wale.jptv.models.base;

public class TimetableStatus {
    public String version;
    public int health;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
