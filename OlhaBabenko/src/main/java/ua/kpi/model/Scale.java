package ua.kpi.model;

public class Scale {

    private int id;
    private String workScale;

    public Scale(int id, String workScale) {
        this.id = id;
        this.workScale = workScale;
    }

    public Scale() {
    }

    public int getId() {
        return id;
    }

    public String getWorkScale() {
        return workScale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkScale(String workScale) {
        this.workScale = workScale;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + (this.workScale != null ? this.workScale.hashCode() : 0);
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
        final Scale other = (Scale) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.workScale == null) ? (other.workScale != null) : !this.workScale.equals(other.workScale)) {
            return false;
        }
        return true;
    }
}
