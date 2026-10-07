package alumno.gaboemi.CloudInforme.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "informes")
@Getter
@Setter
public class Informe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", nullable = false, unique = true)
    private String requestId;

    @Column(nullable = false)
    private String email;

    @Column(name = "requested_at", nullable = false)
    private Instant requestedAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "total_courses", nullable = false)
    private int totalCourses;

    @Column(name = "total_enrollments", nullable = false)
    private int totalEnrollments;

    @Column(name = "report_payload", columnDefinition = "TEXT")
    private String reportPayload;
}