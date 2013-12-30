package ua.kpi.model;

public class Bid {

    private int id;
    private Tenant tenant;
    private WorkType workType;
    private Scale workScale;
    private String timeToDoWish;
    private String dateOfFilling;
    private String status;

    public Bid(int id, Tenant tenant, WorkType workType, Scale workScale, String timeToDoWish, String dateOfFilling, String status) {
        this.id = id;
        this.tenant = tenant;
        this.workType = workType;
        this.workScale = workScale;
        this.timeToDoWish = timeToDoWish;
        this.dateOfFilling = dateOfFilling;
        this.status = status;
    }

    public Bid() {
    }

    public int getId() {
        return id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public Scale getWorkScale() {
        return workScale;
    }

    public String getTimeToDoWish() {
        return timeToDoWish;
    }

    public String getDateOfFilling() {
        return dateOfFilling;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public void setWorkScale(Scale workScale) {
        this.workScale = workScale;
    }

    public void setTimeToDoWish(String timeToDoWish) {
        this.timeToDoWish = timeToDoWish;
    }

    public void setDateOfFilling(String dateOfFilling) {
        this.dateOfFilling = dateOfFilling;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (this.tenant != null ? this.tenant.hashCode() : 0);
        hash = 79 * hash + (this.workType != null ? this.workType.hashCode() : 0);
        hash = 79 * hash + (this.workScale != null ? this.workScale.hashCode() : 0);
        hash = 79 * hash + (this.timeToDoWish != null ? this.timeToDoWish.hashCode() : 0);
        hash = 79 * hash + (this.dateOfFilling != null ? this.dateOfFilling.hashCode() : 0);
        hash = 79 * hash + (this.status != null ? this.status.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bid other = (Bid) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tenant != other.tenant && (this.tenant == null || !this.tenant.equals(other.tenant))) {
            return false;
        }
        if (this.workType != other.workType && (this.workType == null || !this.workType.equals(other.workType))) {
            return false;
        }
        if (this.workScale != other.workScale && (this.workScale == null || !this.workScale.equals(other.workScale))) {
            return false;
        }
        if ((this.timeToDoWish == null) ? (other.timeToDoWish != null) : !this.timeToDoWish.equals(other.timeToDoWish)) {
            return false;
        }
        if ((this.dateOfFilling == null) ? (other.dateOfFilling != null) : !this.dateOfFilling.equals(other.dateOfFilling)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        return true;
    }
}
