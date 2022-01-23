package com.liverary.book.springboot.domain.user;

<<<<<<< HEAD
import com.liverary.book.springboot.domain.reading.Reading;
=======
import com.liverary.book.springboot.domain.BaseTimeEntity;
>>>>>>> origin/ykm
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
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
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

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

>>>>>>> origin/ykm
}
