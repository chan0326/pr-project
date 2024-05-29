package site.toeicdoit.api.order;

import jakarta.servlet.http.HttpSession;
import site.toeicdoit.api.user.model.UserModel;
import site.toeicdoit.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.toeicdoit.api.order.model.OrderModel;
import site.toeicdoit.api.order.service.OrderService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final UserService memberService;
    private final OrderService orderService;


    @GetMapping("/order")
    public String order(@RequestParam(name = "message", required = false) String message,
                        @RequestParam(name = "orderUid", required = false) String id,
                        Model model) {

        model.addAttribute("message", message);
        model.addAttribute("orderUid", id);

        return "order";
    }

    @PostMapping("/order")
    public String autoOrder() {
        UserModel userModel = memberService.autoRegister();
        OrderModel orderModel = orderService.autoOrder(userModel);

        String message = "주문 실패";
        if(orderModel != null) {
            message = "주문 성공";
        }

        String encode = URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "redirect:/order?message="+encode+"&orderUid="+ orderModel.getOrderUid();
    }
}
