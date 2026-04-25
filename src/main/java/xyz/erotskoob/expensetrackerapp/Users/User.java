package xyz.erotskoob.expensetrackerapp.Users;

import jakarta.persistence.*;
import lombok.*;
import xyz.erotskoob.expensetrackerapp.Enums.Role;
import xyz.erotskoob.expensetrackerapp.RefreshTokens.RefreshToken;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
    private boolean locked = false;

    @Builder.Default
    private boolean enabled = false;

    private Instant lastLoginDate;

    private Instant lastPasswordResetDate;

    private Instant createdDate;

    private Instant updatedDate;

    private Instant lockedDate;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> refreshTokens = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> categories = new ArrayList<>();

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
