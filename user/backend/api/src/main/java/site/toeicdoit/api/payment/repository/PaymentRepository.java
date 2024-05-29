package site.toeicdoit.api.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.toeicdoit.api.payment.model.PaymentModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long>{


}
