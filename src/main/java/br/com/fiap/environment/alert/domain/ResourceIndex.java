package br.com.fiap.environment.alert.domain;

import br.com.fiap.environment.alert.dto.GetIndexOutPut;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "resource_index")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class ResourceIndex {
    @Id
    @Column(name = "id_rsc")
    private Long id;
    @Column(name = "ind_min")
    private BigDecimal indMin;

    @Column(name = "ind_nrm")
    private BigDecimal indNrm;

    @Column(name = "ind_max")
    private BigDecimal indMax;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Resource.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rsc", referencedColumnName = "id_rsc")
    private Resource resource;


    public GetIndexOutPut toOutPutDto(){
        return  new GetIndexOutPut(this.getResource().getId(),this.indMin,this.indNrm,this.indMax);
    }
}
