package tn.msvcs.cnbeerorderservice.mappers;

import org.mapstruct.Mapper;
import tn.msvcs.cnbeerorderservice.domain.BeerOrderOnline;
import tn.msvcs.cnbeerorderservice.model.BeerOrderLineDto;

@Mapper(uses = DateMapper.class)
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderOnline line);

    BeerOrderOnline dtoToBeerOrderLine(BeerOrderLineDto dto);
}
