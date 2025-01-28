package com.F.T.order_service.service;

import com.F.T.order_service.converter.OrderDtoConverter;
import com.F.T.order_service.model.Order;
import com.F.T.order_service.model.OrderItem;
import com.F.T.order_service.repository.OrderRepository;
import com.F.T.order_service.request.RequestForSendMailForSuccessOrder;
import com.F.T.order_service.util.FeignClientService;
import org.example.CartDto;
import org.example.CartItemDto;
import org.example.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;
    private final FeignClientService feignClientService;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository,
                        OrderDtoConverter orderDtoConverter,
                        FeignClientService feignClientService,
                        OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.converter = orderDtoConverter;
        this.feignClientService = feignClientService;
        this.orderItemService = orderItemService;
    }

    @Transactional
    public OrderDto createOrder(String userId, String cartId) {
        CartDto cartDto =feignClientService.findCartDtoByUserId(userId);
        List<OrderItem> orderItemList=createOrderItemList(cartDto);
        Order order=new Order(userId,cartId,orderItemList);
        order.calculatePrice();
        orderRepository.save(order);
        feignClientService.deleteCartByCartId(cartId);
        RequestForSendMailForSuccessOrder request=new RequestForSendMailForSuccessOrder(userId,
                "Sayın "+feignClientService.findUserNameByUserId(userId)+"\n"+
                        order.getPrice()+" değerinde siparişiniz tarafımıza ulaşmıştır ..."+
                "Şaka şaka Furkan Türk tarafından gönderilmiştir :)) (Test for F&T E-Commerce spring boot project)");
        feignClientService.sendMailForSuccess(request);
        return converter.convert(order);
    }

    public List<OrderDto> findOrderListByUserId(String userId) {
        List<Order> orderList=orderRepository.findByUserId(userId);
        return orderList
                .stream()
                .map(converter::convert)
                .toList();
    }

    private List<OrderItem> createOrderItemList(CartDto cartDto){
        return cartDto.getCartItemList()
                .stream()
                .map(cartItemDto -> {
                    String productId = feignClientService.findBookIdByBookName(cartItemDto.getBookName());
                    OrderItem orderItem=new OrderItem(productId,cartItemDto.getQuantity());
                    orderItem.calculatePrice(cartItemDto.getPrice());
                    orderItemService.saveOrderItem(orderItem);
                    return orderItem;
                }).toList();
    }
}
