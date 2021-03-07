package com.diplom;

import com.diplom.dto.ProductDto;
import com.diplom.enums.Eating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AddProductRequest {

    private List<ProductDto> productDto;
    private String eating;
}
