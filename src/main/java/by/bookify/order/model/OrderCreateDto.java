package by.bookify.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private List<Long> bookIds;
    private String address;
    private String comment;
    private Double totalPrice;
}
