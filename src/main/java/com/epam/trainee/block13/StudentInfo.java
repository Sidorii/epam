package com.epam.trainee.block13;

public class StudentInfo{

    private static int idGenerator = 0;

    private int id;
    private String name;
    private float rate;
    private Speciality speciality;

    public StudentInfo(){
        this.id = ++idGenerator;
    }

    public StudentInfo(String name, float rate, Speciality speciality) {
        this.id = ++idGenerator;
        this.name = name;
        this.rate = rate;
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return "StudentInfo[ " +
                "id=" + id +
                ", rate=" + rate +
                ", speciality=" + speciality + " ]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
