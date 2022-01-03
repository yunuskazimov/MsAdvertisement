package az.xazar.msadvertisement.entity;


import az.xazar.msadvertisement.model.Ad.AdStatusEnum;
import az.xazar.msadvertisement.model.Ad.AdTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ads")
@Builder
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private AdTypeEnum adType;
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    private AdStatusEnum status;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
