package site.toeicdoit.api.order.service;

import site.toeicdoit.api.user.model.UserModel;
import site.toeicdoit.api.order.model.OrderModel;

public interface OrderService {
    OrderModel autoOrder(UserModel userModel);
}
