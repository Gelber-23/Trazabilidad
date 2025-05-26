package com.course.traceability.infraestructure.out.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "tracks")

public class TrackEntity {
    @Id
    private String id;
    private Long idOrder;
    private Long idClient;
    private Long idEmployee;
    private String email;
    private Date date;
    private String previousState;
    private String newState;
    private String emailEmployee;
}
