package az.xazar.msadvertisement.util;

import az.xazar.msadvertisement.entity.AdEntity;
import az.xazar.msadvertisement.model.Ad.exception.AdErrorCodes;
import az.xazar.msadvertisement.model.Ad.exception.AdNotFoundException;
import az.xazar.msadvertisement.repository.AdRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdUtil {
private final AdRepository adRepo;

    public AdUtil(AdRepository adRepo) {
        this.adRepo = adRepo;
    }

    public AdEntity findAd(Long id){
        return adRepo.findById(id)
                .orElseThrow(()->
                        new AdNotFoundException(AdErrorCodes.NOT_FOUND));
    }

    public List<AdEntity> findAdByUserId(Long userId){
        return adRepo.findAllByUserId(userId).orElseThrow(()->
                new AdNotFoundException(AdErrorCodes.NOT_FOUND));
    }
}
