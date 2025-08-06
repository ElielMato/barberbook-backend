package com.barberbook.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_company", nullable = false)
    private Integer idCompany;

    @ElementCollection
    @Column(name = "days_of_week", nullable = false)
    private List<String> daysOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "time_interval", nullable = false)
    private Integer timeInterval;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
}