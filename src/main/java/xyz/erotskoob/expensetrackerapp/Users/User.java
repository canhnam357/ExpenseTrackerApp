package xyz.erotskoob.expensetrackerapp.Users;

import jakarta.persistence.*;
import lombok.*;
import xyz.erotskoob.expensetrackerapp.Enums.Role;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean enabled = false;

    private Instant lastLoginDate;

    private Instant lastPasswordResetDate;

    private Instant createdDate;

    private Instant updatedDate;

    private Instant lockedDate;

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    private void preUpdate() {
        updatedDate = Instant.now();
    }
}
