package com.ms_account.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", schema = "foo_fighters")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;



    @CreationTimestamp
    private LocalDateTime created;                   // дата создания аккаунта

    @UpdateTimestamp
    private LocalDateTime updated;                   // дата обновления аккаунта
}
