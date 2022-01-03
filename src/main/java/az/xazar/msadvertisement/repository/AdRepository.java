package az.xazar.msadvertisement.repository;


import az.xazar.msadvertisement.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<AdEntity,Long> {

    Optional<List<AdEntity>> findAllByUserId(Long userId);
}
