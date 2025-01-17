package com.ead.authuser.records;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRecord(
        UUID userId,

        @JsonView(UserView.RegistrationPost.class)
        String username,

        @JsonView(UserView.RegistrationPost.class)
        String email,

        @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
        String password,

        @JsonView({UserView.PasswordPut.class})
        String oldPassword,

        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String fullName,

        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String phoneNumber,

        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String cpf,

        @JsonView({UserView.ImagePut.class})
        String imageUrl) {

                public interface UserView {
                        public static interface RegistrationPost{}
                        public static interface UserPut{}
                        public static interface PasswordPut{}
                        public static interface ImagePut{}
                }
}
