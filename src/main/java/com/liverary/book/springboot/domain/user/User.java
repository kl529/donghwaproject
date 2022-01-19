package com.liverary.book.springboot.domain.user;

import com.liverary.book.springboot.domain.reading.Reading;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey;

    @Column(length = 500, nullable = false)
    private String email;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Reading> list ;

    @Builder
    public User(String email){
        this.email = email;
    }
}
