package tn.msvcs.cnbeerorderservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.msvcs.cnbeerorderservice.domain.BeerOrder;
import tn.msvcs.cnbeerorderservice.domain.Customer;
import tn.msvcs.cnbeerorderservice.domain.OrderStatusEnum;
import tn.msvcs.cnbeerorderservice.mappers.BeerOrderMapper;
import tn.msvcs.cnbeerorderservice.model.BeerOrderDto;
import tn.msvcs.cnbeerorderservice.model.BeerOrderPageList;
import tn.msvcs.cnbeerorderservice.repositories.BeerOrderRepository;
import tn.msvcs.cnbeerorderservice.repositories.CustomerRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BeerOrderMapper beerOrderMapper;
    //private final ApplicationEventPublisher publisher;

    @Override
    public BeerOrderPageList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            Page<BeerOrder> beerOrderPage =
                    beerOrderRepository.findAllByCustomer(customerOptional.get(), pageable);

            return new BeerOrderPageList(beerOrderPage
                .stream()
                    .map(beerOrderMapper::beerOrderToDto)
                    .collect(Collectors.toList()), PageRequest.of(
                            beerOrderPage.getPageable().getPageNumber(),
                            beerOrderPage.getPageable().getPageSize()),
                            beerOrderPage.getTotalElements());
        } else {
            return null;
        }
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null); //should not be set by outside client
            beerOrder.setCustomer(customerOptional.get());

            beerOrder.getBeerOrderLines().forEach(line -> line.setBeerOrder(beerOrder));

            BeerOrder saveBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

            log.debug("Saved Beer Order: " + beerOrder.getId());

            //todo impl
            //  publisher.publishEvent(new NewBeerOrderEvent(savedBeerOrder));

            return beerOrderMapper.beerOrderToDto(saveBeerOrder);
        }
        //todo add exception type
        throw new RuntimeException("Customer not found");
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return beerOrderMapper.beerOrderToDto(getOrder(customerId, orderId));
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);

        beerOrderRepository.save(beerOrder);
    }

    private BeerOrder getOrder(UUID customerId, UUID orderId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);
            if(beerOrderOptional.isPresent()){
                BeerOrder beerOrder = beerOrderOptional.get();

                // fall to exception if customer id's do not match - order not for customer
                if(beerOrder.getCustomer().getId().equals(customerId)){
                    return beerOrder;
                }
            }
            throw new RuntimeException("Beer Order not found");
        }
        throw new RuntimeException("Customer not found");
    }
}
