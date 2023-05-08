package by.korzun.bookify.statistics.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Data
@Accessors(chain = true)
public class Statistics {
    @Id
    private Long id;
    private Long usersNum;
    private Long booksNum;
    private Long ordersNum;
}
