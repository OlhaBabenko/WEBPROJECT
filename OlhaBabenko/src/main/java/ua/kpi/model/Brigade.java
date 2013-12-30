package ua.kpi.model;

public class Brigade {

    private int id;
    private String brigadeKind;

    public Brigade(int id, String brigadeKind) {
        this.id = id;
        this.brigadeKind = brigadeKind;
    }

    public Brigade() {
    }

    public int getId() {
        return id;
    }

    public String getBrigadeKind() {
        return brigadeKind;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrigadeKind(String brigadeKind) {
        this.brigadeKind = brigadeKind;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + (this.brigadeKind != null ? this.brigadeKind.hashCode() : 0);
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
        final Brigade other = (Brigade) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.brigadeKind == null) ? (other.brigadeKind != null) : !this.brigadeKind.equals(other.brigadeKind)) {
            return false;
        }
        return true;
    }
}
