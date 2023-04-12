package ch.unisg.ems.inventory.persistence;

import ch.unisg.ems.inventory.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {

}
