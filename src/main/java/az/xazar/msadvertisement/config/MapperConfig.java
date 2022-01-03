package az.xazar.msadvertisement.config;

import az.xazar.msadvertisement.mapper.AdMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public AdMapper adMapper() {
        return AdMapper.INSTANCE;
    }

}
