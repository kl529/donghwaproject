package com.liverary.book.springboot.domain.user;

import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey;

    @Column(length = 500, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Reading> list ;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Builder
    public User(String email, Role role){
        this.email = email;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
