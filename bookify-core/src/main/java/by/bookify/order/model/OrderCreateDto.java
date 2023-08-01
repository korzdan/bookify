package by.bookify.order.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderCreateDto {
    private List<Long> bookIds;
    private String address;
    private String comment;
    private Double totalPrice;
}
