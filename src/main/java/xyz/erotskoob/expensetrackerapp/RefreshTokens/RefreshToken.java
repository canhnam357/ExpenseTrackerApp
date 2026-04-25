package xyz.erotskoob.expensetrackerapp.RefreshTokens;

import jakarta.persistence.*;
import lombok.*;
import xyz.erotskoob.expensetrackerapp.Users.User;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    private boolean revoked = false;

    private Instant revokedDate;

    private Instant createdDate;

    private Instant expiresDate;

    @PrePersist
    private void prePersist() {
        createdDate = Instant.now();
    }

    public void revoke() {
        this.revoked = true;
        this.revokedDate = Instant.now();
    }

    public boolean isExpired() {
        return expiresDate != null && expiresDate.isBefore(Instant.now());
    }

    public boolean isActive() {
        return !revoked && !isExpired();
    }

}
