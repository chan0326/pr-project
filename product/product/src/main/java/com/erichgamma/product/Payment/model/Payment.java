package com.erichgamma.product.Payment.model;

import com.erichgamma.product.User.model.User;
import com.erichgamma.product.common.compoent.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity(name = "payments")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id", nullable = false)
    private Long id; // 결제 번호
    @Setter
    private Long amount; // 결제 금액
    @Setter
    private PaymentStatus status; // 결제 상태
    @Setter
    private String paymentUid; // 결제 고유 번호


}
