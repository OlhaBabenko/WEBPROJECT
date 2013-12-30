package ua.kpi.model;

public class UserType {

    private int id;
    private String userType;

    public UserType(int id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    public UserType() {
    }

    public int getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.userType != null ? this.userType.hashCode() : 0);
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
        final UserType other = (UserType) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.userType == null) ? (other.userType != null) : !this.userType.equals(other.userType)) {
            return false;
        }
        return true;
    }
}
