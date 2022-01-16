package com.liverary.book.springboot.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey;

    @Column(length = 500, nullable = false)
    private String email;
}
