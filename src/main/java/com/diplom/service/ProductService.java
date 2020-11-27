package com.diplom.service;

import com.diplom.dto.ProductDto;
import com.diplom.model.Product;
import com.diplom.repository.ProductRepository;

import static com.diplom.utils.ProductConverter.convertProductEntityToDto;
import static com.diplom.utils.ProductConverter.converterProductDtoToEntity;

import com.diplom.utils.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(ProductConverter::convertProductEntityToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(int id) {
        return convertProductEntityToDto(productRepository.getOne(id));
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(converterProductDtoToEntity(productDto));
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductDto productDto){
        productRepository.save(converterProductDtoToEntity(productDto));
    }
}

