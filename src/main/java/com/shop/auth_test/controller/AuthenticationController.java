package com.shop.auth_test.controller;


import com.shop.auth_test.entity.AuthenticationResponse;
import com.shop.auth_test.entity.UserEntity;
import com.shop.auth_test.services.AuthenticationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


private  final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody UserEntity request){
try {
    boolean userExiste=authenticationService.checkUserExists(request);
    if (userExiste){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }else {
    return ResponseEntity.ok(authenticationService.register(request));}

}catch (Exception e) {
        // Handle exceptions appropriately
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    }

    @PostMapping("/login")

    public ResponseEntity<AuthenticationResponse>login(@RequestBody UserEntity request){
        return ResponseEntity.ok(authenticationService.authentication(request));
    }


}
