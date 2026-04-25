package xyz.erotskoob.expensetrackerapp.Categories;

import jakarta.persistence.*;
import lombok.*;
import xyz.erotskoob.expensetrackerapp.Users.User;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Builder.Default
    private boolean deleted = false;

    @Column(nullable = false)
    private String hexCode;

    private Instant createdDate;
    private Instant updatedDate;
    private Instant deletedDate;

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedDate = Instant.now();
    }

    private void delete() {
        this.deleted = true;
        this.deletedDate = Instant.now();
    }

}
