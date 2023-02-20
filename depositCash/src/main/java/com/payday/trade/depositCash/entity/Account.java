package com.payday.trade.depositCash.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @SequenceGenerator(
            name = "seq_account",
            sequenceName = "seq_account",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_account"
    )

    private Long id;
    private String customerName;
    private Integer accountNumber;
    private Date accountOpenDate;
    private Date accountCloseDate;
    private Double cashAmount;
    private Double tickerAmount;
    private String tickerName;
    private String accountType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;


}
