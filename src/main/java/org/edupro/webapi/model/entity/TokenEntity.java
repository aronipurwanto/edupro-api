package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_TOKEN")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TID", nullable = false)
    public Integer id;

    @Column(name = "TOKEN", unique = true)
    public String token;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE")
    public TokenType tokenType = TokenType.BEARER;

    @Column(name = "IS_REVOKED")
    public boolean revoked;

    @Column(name = "IS_EXPIRED")
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public UserEntity user;
}
