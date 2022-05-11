package com.example.demo.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "conference")
@NoArgsConstructor
@Data
public class Conference {
    @Id
    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime finish;

//    @OneToMany(mappedBy = "conference")
//    Set<ThemeConference> themeConferences;

}
