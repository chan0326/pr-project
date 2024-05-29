package site.toeicdoit.api.order.model;

import site.toeicdoit.api.payment.model.PaymentModel;
import site.toeicdoit.api.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private String itemName;
    private String orderUid; // 주문 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentModel paymentModel;

    @Builder
    public OrderModel(Long price, String itemName, String orderUid, UserModel userModel, PaymentModel paymentModel) {
        this.price = price;
        this.itemName = itemName;
        this.orderUid = orderUid;
        this.userModel = userModel;
        this.paymentModel = paymentModel;
    }


}
