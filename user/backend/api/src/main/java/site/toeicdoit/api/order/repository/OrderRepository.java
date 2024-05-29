package site.toeicdoit.api.order.repository;

import site.toeicdoit.api.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    @Query("select o from OrderModel o" +
            " left join fetch o.payment p" +
            " left join fetch o.user m" +
            " where o.orderUid = :orderUid")
    Optional<OrderModel> findOrderAndPaymentAndMember(String orderUid);

    @Query("select o from OrderModel o" +
            " left join fetch o.payment p" +
            " where o.orderUid = :orderUid")
    Optional<OrderModel> findOrderAndPayment(String orderUid);
}
