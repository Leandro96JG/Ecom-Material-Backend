package com.FitCommerce.ecom.admin.mappers;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "img", target = "byteImg")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    //Ignorar el multipart cuando pasamos de entidad a dto
    @Mapping(target = "img", ignore = true)
    ProductDto toDto(ProductEntity productEntity);

    @Mapping(source = "byteImg", target = "img")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "categoryName", target = "category.name")
    ProductEntity toEntity(ProductDto productDto);

    default byte[] mapMultipartFileToByteArray(MultipartFile file) throws IOException {
        return file != null ? file.getBytes() : null;
    }
}
