/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author administrater
 */
public class Planning { 
    
    private int id_planning ;
    private int id_coach ;
    private String coach_name;
    private String course;
    private String startLesson;
    private String endLesson;
    private int maxNbr ;
    private int nbractuel;

    public Planning() {
    }

    public Planning(int id_planning) {
        this.id_planning = id_planning;
    }
    

    public Planning(int id_planning, int id_coach, String coach_name, String course, String startLesson, String endLesson, int maxNbr, int nbractuel) {
        this.id_planning = id_planning;
        this.id_coach = id_coach;
        this.coach_name = coach_name;
        this.course = course;
        this.startLesson = startLesson;
        this.endLesson = endLesson;
        this.maxNbr = maxNbr;
        this.nbractuel = nbractuel;
    }

    public Planning(int id_coach, String coach_name, String course, String startLesson, String endLesson, int maxNbr, int nbractuel) {
        this.id_coach = id_coach;
        this.coach_name = coach_name;
        this.course = course;
        this.startLesson = startLesson;
        this.endLesson = endLesson;
        this.maxNbr = maxNbr;
        this.nbractuel = nbractuel;
    }
    

    public int getId_planning() {
        return id_planning;
    }

    public int getId_coach() {
        return id_coach;
    }

    public String getCoach_name() {
        return coach_name;
    }

    public String getCourse() {
        return course;
    }

    public String getStartLesson() {
        return startLesson;
    }

    public String getEndLesson() {
        return endLesson;
    }

    public int getMaxNbr() {
        return maxNbr;
    }

    public int getNbractuel() {
        return nbractuel;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setStartLesson(String startLesson) {
        this.startLesson = startLesson;
    }

    public void setEndLesson(String endLesson) {
        this.endLesson = endLesson;
    }

    public void setMaxNbr(int maxNbr) {
        this.maxNbr = maxNbr;
    }

    public void setNbractuel(int nbractuel) {
        this.nbractuel = nbractuel;
    }

    @Override
    public String toString() {
        return "planning{" + "id_planning=" + id_planning + ", id_coach=" + id_coach + ", coach_name=" + coach_name + ", course=" + course + ", startLesson=" + startLesson + ", endLesson=" + endLesson + ", maxNbr=" + maxNbr + ", nbractuel=" + nbractuel + '}';
    }
    
    
}
