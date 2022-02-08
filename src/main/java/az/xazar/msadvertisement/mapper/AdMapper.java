package az.xazar.msadvertisement.mapper;

import az.xazar.msadvertisement.dao.entity.AdEntity;
import az.xazar.msadvertisement.model.AdDto;
import az.xazar.msadvertisement.model.AdGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    AdDto toAdDto(AdEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AdEntity toAdEntity(AdDto dto);

    @Mapping(target = "userName", ignore = true)
    AdGetDto toAdGetDto(AdEntity entity);


}
