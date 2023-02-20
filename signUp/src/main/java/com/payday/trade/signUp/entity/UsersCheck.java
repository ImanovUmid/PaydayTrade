package com.payday.trade.signUp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersCheck {
    @Id
    @SequenceGenerator(
            name = "seq_users_check",
            sequenceName = "seq_users_check",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_users_check"
    )
    private Long id;
    private String username;
    private String sessionKey;
}
