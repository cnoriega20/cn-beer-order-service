package tn.msvcs.cnbeerorderservice.services;

import org.springframework.data.domain.Pageable;
import tn.msvcs.cnbeerorderservice.model.BeerOrderDto;
import tn.msvcs.cnbeerorderservice.model.BeerOrderPageList;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPageList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickUpOrder(UUID customerId, UUID orderId);
}
