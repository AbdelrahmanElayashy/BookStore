package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.data.Entity.AuthGroup;
import com.sunnah.BookStore.data.Entity.User;
import com.sunnah.BookStore.data.repository.AuthGroupRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class AuthGroupService {

    private final AuthGroupRepository authGroupRepository;


    public boolean addAuthGroupUser(User user, String role) {
        System.out.println("here before0");
        AuthGroup authGroup = new AuthGroup(user, role);
        //    user.getAuthGroups().add(authGroup);
        System.out.println("here before");
        authGroupRepository.save(authGroup);
        System.out.println("here after");
        return true;
    }
}
