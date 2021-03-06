package tn.msvcs.cnbeerorderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BeerOrderOnline extends BaseEntity {

    @ManyToOne
    private BeerOrder beerOrder;

    private UUID beerId;

    private String upc;

    private Integer orderQuantity = 0;

    private Integer quantityAllocated = 0;
}
