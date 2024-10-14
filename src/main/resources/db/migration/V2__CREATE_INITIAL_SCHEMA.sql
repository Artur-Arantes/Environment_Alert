CREATE TABLE person (
    nam VARCHAR(255) NOT NULL,
    eml VARCHAR(255) PRIMARY KEY
);

CREATE TABLE permission (
    id_prm BIGINT PRIMARY KEY AUTO_INCREMENT,
    nam VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id_use BIGINT PRIMARY KEY AUTO_INCREMENT,
    log VARCHAR(50) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    id_per VARCHAR(255) NOT NULL,
    act TINYINT(1) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    snd_nfc TINYINT(1) DEFAULT 0,
    FOREIGN KEY (id_per) REFERENCES person(eml)
);

CREATE TABLE resources (
    id_rsc BIGINT PRIMARY KEY AUTO_INCREMENT,
    nam VARCHAR(100),
    unt_mes VARCHAR(50)
);

CREATE TABLE resource_index (
    id_rsc BIGINT PRIMARY KEY,
    ind_min DECIMAL(38,2),
    ind_nrm DECIMAL(38,2),
    ind_max DECIMAL(38,2),
    FOREIGN KEY (id_rsc) REFERENCES resources(id_rsc)
);

CREATE TABLE record_measurements (
    id_rec BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_rsc BIGINT NOT NULL,
    dat_tim TIMESTAMP,
    mes DECIMAL(10,2),
    loc VARCHAR(200),
    val TINYINT(1),
    FOREIGN KEY (id_rsc) REFERENCES resources(id_rsc)
);

CREATE TABLE alert_status (
    id_alt BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_rec BIGINT NOT NULL,
    dsc_alt VARCHAR(500),
    dat_tim_alt TIMESTAMP,
    snd_ntc TINYINT(1),
    sts VARCHAR(50),
    FOREIGN KEY (id_rec) REFERENCES record_measurements(id_rec)
);

CREATE TABLE notification_users (
    id_use BIGINT NOT NULL,
    id_alt BIGINT NOT NULL,
    PRIMARY KEY (id_alt, id_use),
    FOREIGN KEY (id_alt) REFERENCES alert_status(id_alt),
    FOREIGN KEY (id_use) REFERENCES users(id_use)
);

CREATE TABLE user_permission(
id_prm BIGINT NOT NULL,
id_use BIGINT NOT NULL,
PRIMARY KEY(id_use, id_prm),
FOREIGN KEY (id_use) REFERENCES users(id_use),
FOREIGN KEY (id_prm) REFERENCES permission(id_prm)
)
