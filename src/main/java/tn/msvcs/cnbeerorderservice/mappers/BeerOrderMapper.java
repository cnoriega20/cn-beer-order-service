package tn.msvcs.cnbeerorderservice.mappers;

import org.mapstruct.Mapper;
import tn.msvcs.cnbeerorderservice.domain.BeerOrder;
import tn.msvcs.cnbeerorderservice.model.BeerOrderDto;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
