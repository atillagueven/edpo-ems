package ch.unisg.ems.payment.persistence;

import ch.unisg.ems.payment.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
