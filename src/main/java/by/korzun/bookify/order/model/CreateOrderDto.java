package by.korzun.bookify.order.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private List<Long> bookIds;
    private String address;
    private String comment;
    private Double totalPrice;
}
