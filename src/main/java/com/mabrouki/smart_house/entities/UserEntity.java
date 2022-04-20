package com.mabrouki.smart_house.entities;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;


@Data
@Document(value = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String password;


}