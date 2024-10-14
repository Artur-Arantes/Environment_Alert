package br.com.fiap.environment.alert.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "alert_status")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class AlertStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alt")
    private long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RecordMeasurement.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rec")
    private RecordMeasurement recordMeasurement;

    @Column(name = "dsc_alt")
    private String description;

    @Column(name = "dat_tim_alt")
    private LocalDateTime dateTimeAlert;

    @Column(name = "snd_ntc")
    private Boolean sendNotification;

    @Column(name = "sts")
    private String status;

    @OneToMany
    @JoinTable(name = "notification_users", joinColumns = @JoinColumn(name = "id_alt", referencedColumnName = "id_alt"),
            inverseJoinColumns = @JoinColumn(name = "id_use", referencedColumnName = "id_use"))
    private List<User> notificationUser;
}
