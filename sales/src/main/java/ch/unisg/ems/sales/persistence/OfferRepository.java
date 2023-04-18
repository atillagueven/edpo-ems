package ch.unisg.ems.sales.persistence;

import ch.unisg.ems.sales.domain.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, String> {
}
