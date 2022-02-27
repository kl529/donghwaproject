package com.liverary.book.springboot.config.auth.dto;

import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class SessionUser implements Serializable {
    private String email;

    public SessionUser(User user){
        this.email = user.getEmail();
    }
}