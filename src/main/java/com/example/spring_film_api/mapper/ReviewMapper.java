package com.example.spring_film_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.spring_film_api.dto.ReviewDTO;
import com.example.spring_film_api.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    ReviewDTO toReviewDTO(Review review);

    List<ReviewDTO> toReviewDTOs(List<Review> reviews);
}
