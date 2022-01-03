package az.xazar.msadvertisement.controller;

import az.xazar.msadvertisement.model.Ad.AdDto;
import az.xazar.msadvertisement.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ads")
public class AdController {
    private final AdService service;
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    public AdController(AdService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AdDto> createAd(@RequestBody AdDto adDto) {
        logger.info("Log Controller Ad : create Dto -> " + adDto.toString());
        return new ResponseEntity<>(service.createAd(adDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDto> editAd(@RequestBody AdDto adDto,
                                        @PathVariable Long id) {
        adDto.setId(id);
        logger.info("Log Controller Ad : edit Dto -> " + adDto);
        return new ResponseEntity<>(service.editAd(adDto), HttpStatus.OK);
    }


    @GetMapping
    public List<AdDto> getAds() {
        logger.info("Log Controller Ad : get Dtos ->");
        return service.getAds();
    }

    @GetMapping("/id/{id}")
    public AdDto getById(@PathVariable Long id) {
        return service.getAdById(id);
    }

    @GetMapping("/uid/{uId}")
    public List<AdDto> getByUserId(@PathVariable Long uId) {
        return service.getAdsByUserId(uId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteAd(id);
    }
}
