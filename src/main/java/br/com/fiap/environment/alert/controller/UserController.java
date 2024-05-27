package br.com.fiap.environment.alert.controller;

import br.com.fiap.environment.alert.dto.UserDeleteInputDto;
import br.com.fiap.environment.alert.dto.UserInPutDto;
import br.com.fiap.environment.alert.dto.UserOutPutDto;
import br.com.fiap.environment.alert.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid @NonNull final UserInPutDto userInPutDto) {
        service.addUser(userInPutDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam(name = "id") String id) {
        UserOutPutDto outPut = service.getUser(id);
        return ResponseEntity.ok(outPut);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody @Valid @NonNull final UserDeleteInputDto userInPutDto) {
        service.deleteUser(userInPutDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("add/notification")
    public ResponseEntity addUserToNotificationUser(@RequestParam(name = "id") String id) {
        service.addToUserNotifications(id);
        return ResponseEntity.ok().build();
    }
}
