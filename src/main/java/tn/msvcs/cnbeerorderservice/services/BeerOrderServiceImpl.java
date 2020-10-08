package tn.msvcs.cnbeerorderservice.services;

import org.springframework.data.domain.Pageable;
import tn.msvcs.cnbeerorderservice.model.BeerOrderDto;
import tn.msvcs.cnbeerorderservice.model.BeerOrderPageList;

import java.util.UUID;

public class BeerOrderServiceImpl implements BeerOrderService {

    /*private final BeerOrderRepository beerOrderRepository;
    private */
    @Override
    public BeerOrderPageList listOrders(UUID customerId, Pageable pageable) {
        return null;
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        return null;
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return null;
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {

    }
}
