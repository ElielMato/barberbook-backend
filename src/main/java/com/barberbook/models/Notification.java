package com.barberbook.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "channel", nullable = false)
    private Integer channel;

    @Column(name = "send_at", nullable = false)
    private LocalDateTime sendAt;
}