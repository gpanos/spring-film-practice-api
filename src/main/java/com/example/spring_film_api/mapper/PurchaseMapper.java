package com.example.spring_film_api.mapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.example.spring_film_api.dto.PurchaseDTO;
import com.example.spring_film_api.model.Purchase;


@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(source = "totalAmount", target = "formattedTotalAmount",
            qualifiedByName = "formatCurrency")
    PurchaseDTO toPurchaseDTO(Purchase purchase);

    @Named("formatCurrency")
    default String formatCurrency(BigDecimal amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }
}
