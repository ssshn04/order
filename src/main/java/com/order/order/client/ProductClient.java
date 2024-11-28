package com.order.order.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "product-service", url = "${stock.url}")
public interface ProductClient {

    @GetMapping("api/product/{id}/in-stock")
    boolean isProductInStock(@PathVariable("id") Integer productId);

    @GetMapping("api/product/{id}/price")
    BigDecimal getProductPrice(@PathVariable("id") Integer productId);
}
