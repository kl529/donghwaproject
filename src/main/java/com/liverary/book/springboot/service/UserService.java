package com.liverary.book.springboot.service;


import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User findByEmailUser(String email){
        User entity = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("email을 이용해서 userKey를 찾는 중 문제가 발생했습니다."));
        return entity;
    }

    // 현재 사용자의 email을 통해 userKey알아내기
    public Long getUserKey(String email){
        User entity = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("email을 이용해서 userKey를 찾는 중 문제가 발생했습니다."));
        return entity.getUserKey();
    }

    public void delete(String email){
        User entity = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException());
        userRepository.delete(entity);
    }
}
