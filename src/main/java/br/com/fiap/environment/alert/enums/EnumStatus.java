package br.com.fiap.environment.alert.enums;

public enum EnumStatus {
    DANGER("DANGER"),
    IMMINENT_DANGER("IMMINENT_DANGER"),
    CALAMITY("CALAMITY"),
    ;


    private String description;

    EnumStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
