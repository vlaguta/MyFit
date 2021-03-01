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

import static com.diplom.utils.ProductConverter.converterProductDtoToEntity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

@Service
@RequiredArgsConstructor
public class DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;
    private final CustomerRepository customerRepository;
    private final ProductDailyMenuService productDailyMenuService;

    public DailyMenuDto getDailyMenu(String login) {

        DailyMenu dailyMenu = dailyMenuRepository.findByCustomerLogin(login /* LocalDate.now()*/)
                .orElse(null);

        List<ProductDailyMenu> productDailyMenus = productDailyMenuService.get(dailyMenu.getId());

        Map<Eating, List<Product>> productsByEating = productDailyMenus.stream()
                .collect(groupingBy(ProductDailyMenu::getEating,
                        mapping(ProductDailyMenu::getProduct, Collectors.toList())));

        return DailyMenuDto.builder()
                .id(dailyMenu.getId())
                .breakfast(getEatingProducts(productsByEating.get(Eating.BREAKFAST), dailyMenu.getId(), Eating.BREAKFAST))
                .dinner(getEatingProducts(productsByEating.get(Eating.DINNER),dailyMenu.getId(), Eating.DINNER))
                .supper(getEatingProducts(productsByEating.get(Eating.SUPPER),dailyMenu.getId(), Eating.SUPPER))
                .generalCalories(getGeneralCalories(getDailyMenuProducts(productsByEating)))
                //.generalProteins()
                //.generalFats()
                //.generalCarbonhydrates()
                .build();
    }

    private List<ProductDto> getEatingProducts(List<Product> products, int dailyMenuId, Eating eating) {

        return products
                .stream()
                .map(ProductConverter::convertProductEntityToDto)
                .map(productDto -> {
                    ProductDailyMenu productDailyMenu=productDailyMenuService.get(dailyMenuId, eating, productDto.getId());
                    productDto.setFactualCalories(productDto.getNominalCalories() *productDailyMenu.getProductWeight()/100);
                return productDto; })
                .collect(Collectors.toList());
    }

    private List<Product> getDailyMenuProducts(Map<Eating, List<Product>> productsByEating) {

        return productsByEating.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    //пока не используется (возвращает калорийность каждого приема пищи)
    //public int getEatingCalories(List<Product> products) {
    //    return products.stream()
    //            .map(Product::getCalories)
    //            .flatMapToInt(IntStream::of)
    //            .sum();
    //}

    private int getGeneralCalories(List<Product> products) {

        return products.stream()
                .map(Product::getCalories)
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

    public void deleteDailyMenu(int id) {
        dailyMenuRepository.deleteById(id);
    }


    //написать метод добавляющий продукты с учетом того, что лист продуктов может быть налл
    //public void addProductDailyMenu(int id, int productId /*Product product*/) {
    //    DailyMenu dailyMenu=dailyMenuRepository.findById(id).orElseThrow(()->new BusinessException("Пищевой дневник не найден"));
    //    Product product = ProductConverter.converterProductDtoToEntity(productService.getProduct(productId));
    //    List<Product> products = dailyMenu.getProducts();
    //    if(products==null){
    //        products= new ArrayList<>();
    //    }
    //    products.add(product);
    //    dailyMenu.setProducts(products);
    //    }
    //}

    public void addProductToDailyMenu(int id, ProductDto productDto, Eating eating /*Product product*/) {
        //
        //DailyMenu dailyMenu = dailyMenuRepository.findById(id).orElseThrow(() -> new BusinessException("Дневник питания не найден"));
        //Product product = converterProductDtoToEntity(productDto);
        //List<Product> products = dailyMenu.getProducts();
        //if (products == null) {
        //    products = new ArrayList<>();
        //}
        //products.add(product);
        //dailyMenu.setProducts(products);

        ProductDailyMenu productDailyMenu = new ProductDailyMenu();
        productDailyMenu.setDailyMenuId(id);
        productDailyMenu.setProduct(converterProductDtoToEntity(productDto));
        productDailyMenu.setEating(eating);
        productDailyMenu.setProductWeight(productDto.getWeight());
        productDailyMenuService.save(productDailyMenu);
    }

    public List<DailyMenuDto> getAllDailyMenus() {

        return dailyMenuRepository.findAll().stream()
                .map(DailyMenuConverter::convertDailyMenuEntityToDailyMenuDto)
                .collect(Collectors.toList());
    }
}
