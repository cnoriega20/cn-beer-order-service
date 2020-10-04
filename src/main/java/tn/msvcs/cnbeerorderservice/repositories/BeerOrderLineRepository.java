package tn.msvcs.cnbeerorderservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import tn.msvcs.cnbeerorderservice.domain.BeerOrderOnline;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderOnline, UUID> {
}
