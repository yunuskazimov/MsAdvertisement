package az.xazar.msadvertisement.controller;

import az.xazar.msadvertisement.model.AdDto;
import az.xazar.msadvertisement.model.AdGetDto;
import az.xazar.msadvertisement.model.PageDto;
import az.xazar.msadvertisement.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ads")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AdController {
    private final AdService service;

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'ADMIN'})")
    @PostMapping
    public AdDto createAd(@RequestHeader(name = "User-Id") Long userId,
                          @RequestBody AdDto adDto) {
        log.info("createAd controller started name: {}", adDto.getName());
        adDto.setUserId(userId);
        return service.createAd(adDto);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'ADMIN'})")
    @PutMapping("/{id}")
    public AdDto editAd(@RequestHeader(name = "User-Id") Long userId,
                        @RequestBody AdDto adDto,
                        @PathVariable Long id) {
        log.info("editAd controller started id: {}", id);
        return service.editAd(id, adDto);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'ADMIN'})")
    @GetMapping
    public Page<AdGetDto> getAdList(@RequestHeader(name = "User-Id") Long userId,
                                    PageDto page) {
        log.info("getAds controller started");
        return service.getAdList(page);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'USER'})")
    @GetMapping("/shared")
    public Page<AdGetDto> getAdSharedList(@RequestHeader(name = "User-Id") Long userId,
                                          PageDto page) {
        log.info("getAds controller started");
        return service.getSharedAdList(page);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'USER'})")
    @GetMapping("/id/{id}")
    public AdDto getById(@RequestHeader(name = "User-Id") Long userId,
                         @PathVariable Long id) {
        log.info("getById controller started");
        return service.getAdById(id);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'USER'})")
    @GetMapping("/uid")
    public Page<AdGetDto> getByUserId(@RequestHeader(name = "User-Id") Long userId,
                                   PageDto page) {
        log.info("getByUserId controller started");
        return service.getAdListByUserId(userId, page);
    }

    @PreAuthorize(value = "@permissionService.checkRole(#userId, {'ADMIN'})")
    @DeleteMapping("/{id}")
    public void deleteById(@RequestHeader(name = "User-Id") Long userId,
                           @PathVariable Long id) {
        log.info("deleteById controller started");
        service.deleteAd(id);
    }
}
