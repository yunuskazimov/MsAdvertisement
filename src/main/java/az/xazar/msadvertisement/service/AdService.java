package az.xazar.msadvertisement.service;


import az.xazar.msadvertisement.model.AdDto;
import az.xazar.msadvertisement.model.AdGetDto;
import az.xazar.msadvertisement.model.PageDto;
import org.springframework.data.domain.Page;

public interface AdService {
    AdDto createAd(AdDto adDto);

    AdDto editAd(Long id, AdDto adDto);

    AdDto getAdById(Long id);

    Page<AdGetDto> getAdList(PageDto page);

    Page<AdGetDto> getSharedAdList(PageDto page);

    Page<AdGetDto> getAdListByUserId(Long userid, PageDto page);

    void deleteAd(Long id);
}
