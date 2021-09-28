package com.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer sid;
    private String sname;
    private String gender;
    private String sbir;
    private String hobby;
    private String photo;

    public Student(String sname, String gender) {
        this.sname = sname;
        this.gender = gender;
    }
}
