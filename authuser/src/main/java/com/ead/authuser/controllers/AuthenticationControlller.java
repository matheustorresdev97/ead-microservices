package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.records.UserRecord;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationControlller {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> resgisterUser(
            @RequestBody @Validated(UserRecord.UserView.RegistrationPost.class) @JsonView(UserRecord.UserView.RegistrationPost.class) UserRecord userRecord) {

        log.debug("POST registerUser userDto received {} ", userRecord.toString());
        if (userService.existsByUsername(userRecord.username())) {
            log.warn("Username {} is Already Taken ", userRecord.username());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: username is already taken!");
        }

        if (userService.existsByEmail(userRecord.email())) {
            log.warn("Email {} is Already Taken ", userRecord.email());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: email is already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecord, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        log.debug("POST registerUser userModel saved {} ", userModel.toString());
        log.info("User saved successfully userId {} ", userModel.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index() {
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        return "Logging Spring Boot...";
    }
}
