package com.example.spring_film_api.mapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.example.spring_film_api.dto.ScreeningDTO;
import com.example.spring_film_api.model.Screening;


@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    ScreeningMapper INSTANCE = Mappers.getMapper(ScreeningMapper.class);

    @Mapping(source = "ticketPrice", target = "formattedTicketPrice",
            qualifiedByName = "formatCurrency")
    ScreeningDTO toScreeningDTO(Screening screening);

    List<ScreeningDTO> toScreeningDTOs(List<Screening> screenings);

    @Named("formatCurrency")
    default String formatCurrency(BigDecimal amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }
}
