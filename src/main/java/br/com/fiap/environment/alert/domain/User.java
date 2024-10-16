package br.com.fiap.environment.alert.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_use")
    private Long id;

    @Column(name = "log")
    private String login;

    @Column(name = "pwd")
    @Getter(onMethod = @__(@Override))
    private String password;

    @Column(name = "act")
    @Getter(onMethod = @__(@Override))
    private boolean enabled;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Person.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "id_per")
    @ToString.Exclude
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "id_use", referencedColumnName = "id_use"),
            inverseJoinColumns = @JoinColumn(name = "id_prm", referencedColumnName = "id_prm"))
    List<Permission> permissions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "notification_users", joinColumns = @JoinColumn(name = "id_use", referencedColumnName = "id_use"),
            inverseJoinColumns = @JoinColumn(name = "id_alt", referencedColumnName = "id_alt"))
    private List<AlertStatus> alertStatuses;

    @Column(name = "snd_nfc")
    private boolean sendAlertNotification;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }
}