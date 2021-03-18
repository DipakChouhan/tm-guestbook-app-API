//package com.owneroftime.todoapp.security.repository.impl;
//
//import com.owneroftime.todoapp.security.repository.UserDetailsRepository;
//import com.owneroftime.todoapp.security.model.UserModel;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UserDetailsRepositoryImpl implements UserDetailsRepository {
//
//    @Override
//    public UserModel loadUserByUsername(String email) {
//        UserModel userModel = new UserModel();
//        userModel.setEmail("email@email.com");
//        userModel.setPassword(new BCryptPasswordEncoder().encode("1234"));
//        return userModel;
//    }
//}
