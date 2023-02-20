package com.payday.trade.placeOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_seq",
            sequenceName = "orders_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_seq"
    )
    private Long id;
    private Double buyAmount;
    private Double cellAmount;
    private Integer tickerCount;
    private String tickerName;
    private Integer orderStatus;
    private String openDate;
    private String closeDate;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
}
