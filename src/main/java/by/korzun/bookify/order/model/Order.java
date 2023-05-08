package by.korzun.bookify.order.model;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.user.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_order")
@Data
@Accessors(chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany
    @JoinTable(
            name = "user_orders_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
    private String comment;
    private String address;
    private Double totalPrice;
}
