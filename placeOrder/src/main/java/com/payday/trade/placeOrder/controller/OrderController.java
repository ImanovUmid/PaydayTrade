package com.payday.trade.placeOrder.controller;

import com.payday.trade.placeOrder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "placeOrderAPI")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/{userId}/{buyPrice}/{cellPrice}/{orderStatus}/{tickerCount}/{tickerName}/{date1}/{date2}")
    public ResponseEntity<Long> crateNewOrder(@PathVariable Long userId, @PathVariable Double buyPrice, @PathVariable Double cellPrice,
                                                @PathVariable Integer orderStatus, @PathVariable Integer tickerCount,
                                                @PathVariable String tickerName, @PathVariable String date1, @PathVariable String date2) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder( userId,  buyPrice,  cellPrice,  orderStatus,  tickerCount,  tickerName,  date1,  date2));
    }

    @PostMapping(value = "/{orderId}")
    public void tradeOrder(@PathVariable Long orderId) throws JSONException, IOException {
        orderService.buyOrCellOrder(orderId);
    }

}
