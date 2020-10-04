package tn.msvcs.cnbeerorderservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class BeerOrderLineDto  extends BaseItem{

    private String upc;
    private String beerName;
    private UUID beerId;
    private Integer orderQuantity;

}
