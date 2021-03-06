package tn.msvcs.cnbeerorderservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.msvcs.cnbeerorderservice.domain.Customer;
import tn.msvcs.cnbeerorderservice.loader.BeerOrderLoader;
import tn.msvcs.cnbeerorderservice.model.BeerOrderDto;
import tn.msvcs.cnbeerorderservice.model.BeerOrderLineDto;
import tn.msvcs.cnbeerorderservice.repositories.BeerOrderRepository;
import tn.msvcs.cnbeerorderservice.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;
    private final List<String> beerUpcs = new ArrayList<>(3);

    public TastingRoomService(CustomerRepository customerRepository, BeerOrderService beerOrderService, BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderRepository = beerOrderRepository;

        beerUpcs.add(BeerOrderLoader.BEER_1_UPC);
        beerUpcs.add(BeerOrderLoader.BEER_2_UPC);
        beerUpcs.add(BeerOrderLoader.BEER_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 2000) //run every 2 secs.
    public void placeTastingRoomOrder(){
        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderLoader.TASTING_ROOM);

        if(customerList.size() == 1){ //should be just one
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }

    }

    private void doPlaceOrder(Customer customer) {
        String beerToOrder = getRandomBeerUpc();

        BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6))
                .build();

        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLine);

        BeerOrderDto beerOrder = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineSet)
                .build();
        BeerOrderDto saveOrder = beerOrderService.placeOrder(customer.getId(), beerOrder);

    }

    private String getRandomBeerUpc() {
        return beerUpcs.get(new Random().nextInt( beerUpcs.size()));
    }
}
