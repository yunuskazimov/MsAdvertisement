package az.xazar.msadvertisement.util;

import az.xazar.msadvertisement.dao.entity.AdEntity;
import az.xazar.msadvertisement.dao.repository.AdRepository;
import az.xazar.msadvertisement.model.exception.AdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static az.xazar.msadvertisement.model.exception.ErrorCodes.NOT_FOUND;

@Component
public class AdUtil {
    private final AdRepository adRepo;

    public AdUtil(AdRepository adRepo) {
        this.adRepo = adRepo;
    }

    public AdEntity findAd(Long id) {
        return adRepo.findById(id)
                .orElseThrow(() ->
                        new AdNotFoundException(NOT_FOUND));
    }

    public Page<AdEntity> findAdByUserId(Long userId, Pageable pageable) {
        Page<AdEntity> entityList = adRepo.findAllByUserId(userId, pageable).get();
        if (entityList.isEmpty()) {
            throw new AdNotFoundException(NOT_FOUND);
        } else {
            return entityList;
        }
    }
}
