package az.xazar.msadvertisement.dao.repository;


import az.xazar.msadvertisement.dao.entity.AdEntity;
import az.xazar.msadvertisement.model.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdRepository extends PagingAndSortingRepository<AdEntity, Long> {

    Optional<Page<AdEntity>> findAllByUserId(Long userId, Pageable pageable);
    Page<AdEntity> findAllByStatusAndDeletedFalse(StatusEnum status, Pageable pageable);
    Page<AdEntity> findByDeletedFalse(Pageable pageable);


}
