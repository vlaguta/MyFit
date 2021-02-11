package com.diplom.service;

import com.diplom.dto.DailyMenuDto;
import com.diplom.dto.ProductDto;
import com.diplom.enums.EatingEnum;
import com.diplom.model.Customer;
import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.model.ProductDailyMenu;
import com.diplom.repository.CustomerRepository;
import com.diplom.repository.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.diplom.utils.DailyMenuConverter.convertDailyMenuDtoToDailyMenuEntity;
import static com.diplom.utils.DailyMenuConverter.convertDailyMenuEntityToDailyMenuDto;

@Service
@RequiredArgsConstructor
public class DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;
    private final CustomerRepository customerRepository;
    private final ProductDailyMenuService productDailyMenuService;
    private final ProductService productService;

    public DailyMenuDto getDailyMenu(String login) {
        Customer customer = customerRepository.findCustomerByLogin(login).orElse(null);
        DailyMenu dailyMenu = dailyMenuRepository.findByCustomerId(customer.getId()/*, LocalDate.now()*/)
                .orElse(null);
        return convertDailyMenuEntityToDailyMenuDto(dailyMenu); //исправить
    }

    public List<ProductDto> get(String login){
        Customer customer = customerRepository.findCustomerByLogin(login).orElse(null);
        DailyMenu dailyMenu = dailyMenuRepository.findByCustomerId(customer.getId()/*, LocalDate.now()*/)
                .orElse(null);
        List<ProductDailyMenu> productDailyMenus = productDailyMenuService.get(dailyMenu.getId());
        List<Integer> breakfast= productDailyMenus.stream()
                .filter(productDailyMenu -> productDailyMenu.getEating().equals(EatingEnum.BREAKFAST))
                .map(ProductDailyMenu::getProductId)
                .collect(Collectors.toList());
       List<ProductDto> e=new ArrayList<>();
        for (int i=0; i<breakfast.size(); i++){
        e.add(productService.getAllProduct().get(breakfast.get(i)));
        }
        return e;
    }

    public void saveDailyMenu(DailyMenuDto dailyMenuDto) {
        dailyMenuRepository.save(convertDailyMenuDtoToDailyMenuEntity(dailyMenuDto));
    }

    public void deleteDailyMenu(int id) {
        dailyMenuRepository.deleteById(id);
    }

    public void updateDailyMenu(int id, List<Product> products) {
        DailyMenu dailyMenu = dailyMenuRepository.getOne(id);
        dailyMenu.setProducts(products);
        dailyMenuRepository.save(dailyMenu);
    }
}
