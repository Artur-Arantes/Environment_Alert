package br.com.fiap.environment.alert.domain;

import br.com.fiap.environment.alert.enums.EnumUserPermission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "permission")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "id_prm")
    private long id;

    @Column(name = "nam")
    @Enumerated(EnumType.STRING)
    private EnumUserPermission name;

    @Override
    public String getAuthority() {
        return name.getRole();
    }
}
