package com.liverary.book.springboot.config.auth.dto;

import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;

    public SessionUser(User user){
        this.email = user.getEmail();
    }
}