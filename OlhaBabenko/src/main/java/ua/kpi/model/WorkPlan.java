/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.model;

/**
 *
 * @author Оля
 */
public class WorkPlan {

    private int id;
    private Bid bid;
    private Brigade brigade;
    private String timeToDo;

    public WorkPlan(int id, Bid bid, Brigade brigade, String timeToDo) {
        this.id = id;
        this.bid = bid;
        this.brigade = brigade;
        this.timeToDo = timeToDo;
    }

    public WorkPlan() {
    }

    public int getId() {
        return id;
    }

    public Bid getBid() {
        return bid;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public String getTimeToDo() {
        return timeToDo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    public void setTimeToDo(String timeToDo) {
        this.timeToDo = timeToDo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.bid != null ? this.bid.hashCode() : 0);
        hash = 97 * hash + (this.brigade != null ? this.brigade.hashCode() : 0);
        hash = 97 * hash + (this.timeToDo != null ? this.timeToDo.hashCode() : 0);
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
        final WorkPlan other = (WorkPlan) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.bid != other.bid) {
            return false;
        }
        if (this.brigade != other.brigade) {
            return false;
        }
        if ((this.timeToDo == null) ? (other.timeToDo != null) : !this.timeToDo.equals(other.timeToDo)) {
            return false;
        }
        return true;
    }
}
