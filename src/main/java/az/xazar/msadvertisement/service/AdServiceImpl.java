package az.xazar.msadvertisement.service;

import az.xazar.msadvertisement.entity.AdEntity;
import az.xazar.msadvertisement.mapper.AdMapper;
import az.xazar.msadvertisement.model.Ad.AdDto;
import az.xazar.msadvertisement.repository.AdRepository;
import az.xazar.msadvertisement.util.AdUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository repo;
    private final AdMapper adMapper;
    private final AdUtil adUtil;

    public AdServiceImpl(AdRepository repo,
                         AdMapper adMapper,
                         AdUtil adUtil) {
        this.repo = repo;
        this.adMapper = adMapper;
        this.adUtil = adUtil;
    }

    @Override
    public AdDto createAd(AdDto adDto) {
        AdEntity entity = repo.save(adMapper.toEntity(adDto));
        adDto.setId(entity.getId());
        return adDto;
    }

    @Override
    public AdDto editAd(AdDto adDto) {
        adUtil.findAd(adDto.getId());
        repo.save(adMapper.toEntity(adDto));
        return adDto;
    }

    @Override
    public AdDto getAdById(Long id) {
        return adMapper.toDto(adUtil.findAd(id));
    }

    @Override
    public List<AdDto> getAds() {
        return adMapper.toDtos(
                repo.findAll());
    }

    @Override
    public List<AdDto> getAdsByUserId(Long userid) {
        return adMapper.toDtos(adUtil.findAdByUserId(userid));
    }

    @Override
    public void deleteAd(Long id) {
        AdEntity entity = adUtil.findAd(id);
        entity.setDeleted(true);
        repo.save(entity);
    }
}
