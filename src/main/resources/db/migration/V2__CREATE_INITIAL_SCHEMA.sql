CREATE TABLE person (
    nam VARCHAR2(255) NOT NULL,
    eml VARCHAR2(255) PRIMARY KEY
);

CREATE TABLE permission (
    id_prm NUMBER(20) PRIMARY KEY,
    nam VARCHAR2(255) NOT NULL
);

CREATE TABLE users (
    id_use NUMBER(20) PRIMARY KEY,
    log VARCHAR2(50) NOT NULL,
    pwd VARCHAR2(255) NOT NULL,
    id_per VARCHAR2(255) NOT NULL,
    act NUMBER(1) NOT NULL,
    role VARCHAR2(50) DEFAULT 'USER',
    snd_nfc NUMBER(1) DEFAULT 0,
    FOREIGN KEY (id_per) REFERENCES person(eml)
);

CREATE TABLE user_permission (
    id_prm NUMBER(20) NOT NULL,
    id_use NUMBER(20) NOT NULL,
    PRIMARY KEY (id_prm, id_use),
    FOREIGN KEY (id_prm) REFERENCES permission(id_prm),
    FOREIGN KEY (id_use) REFERENCES users(id_use)
);

CREATE TABLE resources (
    id_rsc NUMBER(20) PRIMARY KEY,
    nam VARCHAR2(100),
    unt_mes VARCHAR2(50)
);

CREATE TABLE resource_index (
    id_rsc NUMBER(20) PRIMARY KEY,
    ind_min number(38,2),
    ind_nrm number(38,2),
    ind_max number(38,2),
    FOREIGN KEY (id_rsc) REFERENCES resources(id_rsc)
);

CREATE TABLE record_measurements (
    id_rec NUMBER(20) PRIMARY KEY,
    id_rsc NUMBER(20) NOT NULL,
    dat_tim TIMESTAMP,
    mes NUMBER(10,2),
    loc VARCHAR2(200),
    val NUMBER(1),
    FOREIGN KEY (id_rsc) REFERENCES resources(id_rsc)
);

CREATE TABLE alert_status (
    id_alt NUMBER(20) PRIMARY KEY,
    id_rec NUMBER(20) NOT NULL,
    dsc_alt VARCHAR2(500),
    dat_tim_alt TIMESTAMP,
    snd_ntc NUMBER(1,0),
    sts VARCHAR2(50),
    FOREIGN KEY (id_rec) REFERENCES record_measurements(id_rec)
);

CREATE TABLE notification_users (
    id_use NUMBER(20) NOT NULL,
    id_alt NUMBER(20) NOT NULL,
    PRIMARY KEY (id_alt, id_use),
    FOREIGN KEY (id_alt) REFERENCES alert_status(id_alt),
    FOREIGN KEY (id_use) REFERENCES users(id_use)
);

