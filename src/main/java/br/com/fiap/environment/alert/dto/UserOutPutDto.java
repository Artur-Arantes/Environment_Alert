package br.com.fiap.environment.alert.dto;

import br.com.fiap.environment.alert.domain.User;

public record UserOutPutDto(Long id, String email, boolean active) {

    public UserOutPutDto(User user) {
        this(user.getId(), user.getPerson().getEmail(), user.isEnabled());
    }

}
