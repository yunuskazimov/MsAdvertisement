package az.xazar.msadvertisement.service;



import az.xazar.msadvertisement.model.Ad.AdDto;

import java.util.List;

public interface AdService {
    AdDto createAd(AdDto adDto);

    AdDto editAd(AdDto adDto);

    AdDto getAdById(Long id);

    List<AdDto> getAds();

    List<AdDto> getAdsByUserId(Long userid);

    void deleteAd(Long id);
}
