package com.diplom.service;

import com.diplom.dto.DailyMenuDto;
import com.diplom.dto.ProductDto;
import com.diplom.enums.Eating;
import com.diplom.model.Customer;
import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.model.ProductDailyMenu;
import com.diplom.repository.CustomerRepository;
import com.diplom.repository.DailyMenuRepository;
import com.diplom.utils.DailyMenuConverter;
import com.diplom.utils.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.diplom.utils.ProductConverter.convertProductEntityToDto;
import static com.diplom.utils.ProductConverter.converterProductDtoToEntity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

@Service
@RequiredArgsConstructor
public class DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;
    private final CustomerRepository customerRepository;
    private final ProductDailyMenuService productDailyMenuService;

    public DailyMenuDto getDailyMenuDto(String login) {

        DailyMenu dailyMenu = dailyMenuRepository.findByCustomerLogin(login /* LocalDate.now()*/)
                .orElse(null);

        List<ProductDailyMenu> productDailyMenus = productDailyMenuService.get(dailyMenu.getId());

        Map<Eating, List<Product>> productsByEating = productDailyMenus.stream()
                .collect(groupingBy(ProductDailyMenu::getEating,
                        mapping(ProductDailyMenu::getProduct, Collectors.toList())));

        return  DailyMenuDto.builder()
                .id(dailyMenu.getId())
                .breakfast(getEatingProducts(productsByEating.get(Eating.BREAKFAST), dailyMenu.getId(), Eating.BREAKFAST))
                .dinner(getEatingProducts(productsByEating.get(Eating.DINNER), dailyMenu.getId(), Eating.DINNER))
                .supper(getEatingProducts(productsByEating.get(Eating.SUPPER), dailyMenu.getId(), Eating.SUPPER))
                .generalCalories(getGeneralCalories(getEatingProducts(productsByEating.get(Eating.BREAKFAST),dailyMenu.getId(), Eating.BREAKFAST))+
                (getGeneralCalories(getEatingProducts(productsByEating.get(Eating.DINNER),dailyMenu.getId(), Eating.DINNER)))+
                (getGeneralCalories(getEatingProducts(productsByEating.get(Eating.SUPPER),dailyMenu.getId(), Eating.SUPPER))))
                .build();

    }

    private List<ProductDto> getEatingProducts(List<Product> products, int dailyMenuId, Eating eating) {

        return products
                .stream()
                .map(ProductConverter::convertProductEntityToDto)
                .map(productDto -> {
                    ProductDailyMenu productDailyMenu = productDailyMenuService.get(dailyMenuId, eating, productDto.getId());
                    productDto.setFactualCalories(productDto.getNominalCalories() * productDailyMenu.getProductWeight() / 100);
                    productDto.setFactualCarbonhydrates(productDto.getCarbonhydrates() * productDailyMenu.getProductWeight() / 100);
                    productDto.setFactualProtein(productDto.getProtein() * productDailyMenu.getProductWeight() / 100);
                    productDto.setFactualFat(productDto.getFat() * productDailyMenu.getProductWeight() / 100);
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    private List<Product> getDailyMenuProducts(Map<Eating, List<Product>> productsByEating) {

        return productsByEating.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private int getGeneralCalories(List<ProductDto> products) {

        return products.stream()
                .map(ProductDto::getFactualCalories)
                .flatMapToInt(IntStream::of)
                .sum();
    }

    //метод создающий дэйли меню каждый день в 00,00 для каждого пользователя
    public void saveDailyMenuForEveryCustomer() {

        customerRepository.findAll()
                .forEach(this::saveDailyMenu);
    }

    public void saveDailyMenu(Customer customer) {

        DailyMenu dailyMenu = new DailyMenu();
        dailyMenu.setCreatedDate(LocalDate.now());
        dailyMenu.setCustomer(customer);
        dailyMenuRepository.save(dailyMenu);
    }

    //public void deleteDailyMenu(int id) {
    //    dailyMenuRepository.deleteById(id);
    //}

    public void addProductToDailyMenu(int dailyMenuId, ProductDto productDto, Eating eating) {

        ProductDailyMenu productDailyMenuFromDb = productDailyMenuService.get(dailyMenuId, eating, productDto.getId());
        //DailyMenu dailyMenu = dailyMenuRepository.findById(dailyMenuId).orElse(new DailyMenu());

        if (productDailyMenuFromDb == null) {
            ProductDailyMenu productDailyMenu = new ProductDailyMenu();
            productDailyMenu.setDailyMenuId(dailyMenuId);
            productDailyMenu.setProduct(converterProductDtoToEntity(productDto));
            productDailyMenu.setEating(eating);
            productDailyMenu.setProductWeight(productDto.getWeight());
            productDailyMenuService.save(productDailyMenu);
        } else {
            int weight = productDailyMenuFromDb.getProductWeight() + productDto.getWeight();
            productDailyMenuFromDb.setProductWeight(weight);
            productDailyMenuService.save(productDailyMenuFromDb);
        }
    }

    public List<DailyMenuDto> getAllDailyMenus() {

        return dailyMenuRepository.findAll().stream()
                .map(DailyMenuConverter::convertDailyMenuEntityToDailyMenuDto)
                .collect(Collectors.toList());
    }
}
