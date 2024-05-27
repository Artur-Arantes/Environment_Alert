package br.com.fiap.environment.alert.domain;

import br.com.fiap.environment.alert.dto.GetRecordMeasureOutPutDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "record_measurements")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class RecordMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REC")
    @SequenceGenerator(name = "SEQ_REC", sequenceName = "SEQ_REC", allocationSize = 1)
    @Column(name = "id_rec")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rsc", nullable = false)
    private Resource resource;

    @Column(name = "loc")
    private String location;

    @Column(name = "mes")
    private BigDecimal measurement;

    @Column(name = "dat_tim")
    private LocalDateTime dateTime;


    public GetRecordMeasureOutPutDto toGetMeasureOutPutDto() {
        return new GetRecordMeasureOutPutDto(this.resource.getId(), this.dateTime, this.location, this.measurement);
    }

}
