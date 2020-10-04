package tn.msvcs.cnbeerorderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String customerName;

    @Column(length = 36, columnDefinition = "varchar")
    private UUID apikey;

    @OneToMany(mappedBy = "customer")
    private Set<BeerOrder> beerOrders;

}
