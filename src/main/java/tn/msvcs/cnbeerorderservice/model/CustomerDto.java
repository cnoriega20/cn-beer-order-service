package tn.msvcs.cnbeerorderservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class CustomerDto extends BaseItem{

    private String name;
}
