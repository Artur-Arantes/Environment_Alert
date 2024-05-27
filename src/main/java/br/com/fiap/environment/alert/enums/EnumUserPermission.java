package br.com.fiap.environment.alert.enums;

public enum EnumUserPermission {

    USER("USER",2),
    ADMIN("ROLE_ADMIN",1);

    private String role;
    private int code;

    EnumUserPermission(String role, int code) {
        this.role = role;
        this.code = code;
    }

    public String getRole() {
        return this.role;
    }

    public int getCode() {
        return this.code;
    }
}
