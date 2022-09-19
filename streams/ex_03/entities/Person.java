package entities;

public class Person {
    private String gender;
    private Integer height;
    
    public Person(String gender, Integer height) {
        this.gender = gender;
        this.height = height;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }

}