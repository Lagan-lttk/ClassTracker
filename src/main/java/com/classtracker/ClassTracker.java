package com.classtracker;

import com.classtracker.controller.ProfessorDAOimpl;
import com.classtracker.model.Professor;

public class ClassTracker {
    public static void main(String[] args) {
        Professor claudio = new Professor
                ("11322233345","claudiopinto@gmail.com","filosofia","Claudio"
                        ,"61996904466");

        Professor sandro = new Professor
                ("12122213345","sandroFera@gmail.com","plantio","Sandro"
                        ,"61993404466");

        ProfessorDAOimpl teste = new ProfessorDAOimpl();

        Professor seila = teste.getProfessor("11322233345");
        System.out.println("123");
        System.out.println(seila);
    }
}
