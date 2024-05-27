package br.com.fiap.environment.alert.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "resources")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class Resource {
    @Id
    @Column(name = "id_rsc")
    private Long id;

    @Column(name = "nam")
    private String name;

    @Column(name = "unt_mes")
    private String unitOfMeasure;

}
