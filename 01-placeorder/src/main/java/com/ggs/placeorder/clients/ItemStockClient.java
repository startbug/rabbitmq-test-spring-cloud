package com.ggs.placeorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:36
 */
@FeignClient(value = "itemstock")
public interface ItemStockClient {

    @GetMapping("/decr")
    void desc();

}
