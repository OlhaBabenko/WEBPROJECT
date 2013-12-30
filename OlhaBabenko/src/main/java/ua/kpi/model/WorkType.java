package ua.kpi.model;

public class WorkType {

    private int id;
    private String workType;

    public WorkType(int id, String workType) {
        this.id = id;
        this.workType = workType;
    }

    public WorkType() {
    }

    public int getId() {
        return id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + (this.workType != null ? this.workType.hashCode() : 0);
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
        final WorkType other = (WorkType) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.workType == null) ? (other.workType != null) : !this.workType.equals(other.workType)) {
            return false;
        }
        return true;
    }
}
