package andr.mxg167030_asg4;

import java.io.Serializable;
import java.util.Date;

public class HighScore implements Comparable<HighScore>, Serializable {
    private String name;
    private Date date;
    private int score;

    public HighScore(String name, Date date, int score) {
        this.name = name;
        this.date = date;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(HighScore o) {

        if(this.getScore() == o.getScore()){
            return this.getDate().compareTo(o.getDate());
        }

        return this.getScore() > o.getScore()? 1 : -1;

    }

}
