package com.epsi.gosecuri;

import java.util.List;

public class Agent {
    private String name;
    private String surname;
    private String password;
    private String assignment;
    private List<String> toolsList;
    private String photo;

    public Agent(String name, String surname, String password, String assignment, List<String> toolsList){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.assignment = assignment;
        this.toolsList = toolsList;
        this.photo = surname.charAt(0) + name + ".jpg";
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public List<String> getToolsList() {
        return toolsList;
    }
    public String getPhoto() {
        return photo;
    }
}
