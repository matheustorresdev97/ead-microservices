package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.records.UserRecord;
import com.ead.authuser.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationControlller {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> resgisterUser(@RequestBody UserRecord userRecord) {

        if (userService.existsByUsername(userRecord.username())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: username is already taken!");
        }

        if( userService.existsByEmail(userRecord.email() ) ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: email is already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecord, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
