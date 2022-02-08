package az.xazar.msadvertisement.service.impl;

import az.xazar.msadvertisement.client.UserClientRest;
import az.xazar.msadvertisement.dao.entity.AdEntity;
import az.xazar.msadvertisement.dao.repository.AdRepository;
import az.xazar.msadvertisement.mapper.AdMapper;
import az.xazar.msadvertisement.model.AdDto;
import az.xazar.msadvertisement.model.AdGetDto;
import az.xazar.msadvertisement.model.PageDto;
import az.xazar.msadvertisement.model.StatusEnum;
import az.xazar.msadvertisement.model.client.user.UserDto;
import az.xazar.msadvertisement.model.exception.AdNotFoundException;
import az.xazar.msadvertisement.model.exception.WrongEditIdException;
import az.xazar.msadvertisement.service.AdService;
import az.xazar.msadvertisement.util.AdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository repo;
    private final AdMapper adMapper;
    private final AdUtil adUtil;
    private final UserClientRest userClient;


    @Override
    public AdDto createAd(AdDto dto) {
        log.info("service createAd started with Name: {}", dto.getName());
        userClient.getById(dto.getUserId());
        dto.setDeleted(false);
        AdEntity entity = repo.save(adMapper.toAdEntity(dto));
        log.info("service createAd completed with id: {}", dto.getId());
        return adMapper.toAdDto(entity);
    }

    @Override
    public AdDto editAd(Long id, AdDto dto) {
        log.info("service editAd started with Name: {}", dto.getName());
        checkDtoId(id, dto);
        userClient.getById(getAdById(id).getUserId());
        adUtil.findAd(dto.getId());
        AdEntity entity = repo.save(adMapper.toAdEntity(dto));
        log.info("service editAd completed with id: {}", dto.getId());
        return adMapper.toAdDto(entity);
    }

    @Override
    public AdDto getAdById(Long id) {
        log.info("getAdById service started with id: {}", id);
        return adMapper.toAdDto(adUtil.findAd(id));
    }

    @Override
    public Page<AdGetDto> getAdList(PageDto page) {
        log.info("getAdList service started ");
        Page<AdGetDto> getListDto = repo.findByDeletedFalse(getPageable(page))
                .map(entity -> {
                    UserDto user = userClient.getById(entity.getUserId());
                    AdGetDto dto = adMapper.toAdGetDto(entity);
                    dto.setUserName(user.getName() + " " + user.getSurname());
                    return dto;
                });
        if (!getListDto.isEmpty()) {
            log.info("getAdList service completed ");
            return getListDto;
        } else {
            log.info("getAdList service run exception ");
            throw new AdNotFoundException("Advertisement Not Found");
        }

    }

    @Override
    public Page<AdGetDto> getSharedAdList(PageDto page) {
        log.info("getSharedAdList service started ");
        Page<AdGetDto> getListDto = repo
                .findAllByStatusAndDeletedFalse(StatusEnum.SHARE, getPageable(page))
                .map(entity -> {
                    UserDto user = userClient.getById(entity.getUserId());
                    AdGetDto dto = adMapper.toAdGetDto(entity);
                    dto.setUserName(user.getName() + " " + user.getSurname());
                    return dto;
                });
        if (!getListDto.isEmpty()) {
            log.info("getSharedAdList service completed ");
            return getListDto;
        } else {
            log.info("getSharedAdList service run exception ");
            throw new AdNotFoundException("Shared Advertisement Not Found");
        }
    }

    @Override
    public Page<AdGetDto> getAdListByUserId(Long userid, PageDto page) {
        log.info("getAdListByUserId service started with user id: {}", userid);
        UserDto user = userClient.getById(userid);
        Page<AdGetDto> getListDto = adUtil.findAdByUserId(userid, getPageable(page))
                .map(entity -> {
                    AdGetDto dto = adMapper.toAdGetDto(entity);
                    dto.setUserName(user.getName() + " " + user.getSurname());
                    return dto;
                });
        if (!getListDto.isEmpty()) {
            log.info("getAdListByUserId service completed ");
            return getListDto;
        } else {
            log.info("getAdListByUserId service run exception ");
            throw new AdNotFoundException("Advertisement Not Found");
        }
    }

    @Override
    public void deleteAd(Long id) {
        log.info("deleteAd service started with id: {}", id);
        AdEntity entity = adUtil.findAd(id);
        userClient.getById(entity.getUserId());
        entity.setDeleted(true);
        repo.save(entity);
        log.info("deleteAd service completed with id: {}", id);
    }

    private Pageable getPageable(PageDto page) {
        Sort sort = Sort.by(page.getSortDirection(), page.getSortBy());
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

    private void checkDtoId(Long id, AdDto dto) {
        if (!id.equals(dto.getId())) {
            throw new WrongEditIdException("Path Id not Same with dto id");
        }
    }
}
