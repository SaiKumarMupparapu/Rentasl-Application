package com.scaleorange.LaptopRentals.service.laptops;

import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.entity.Laptops;
import com.scaleorange.LaptopRentals.repo.ProductsRepo;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import com.scaleorange.LaptopRentals.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService{

    @Autowired
    private ProductsRepo laptopsRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Override
    public ItemResponse saveItem(ItemRequest laptops) {
        Laptops item = Mapper.convertToLaptops(laptops);
        item.setProvider(organizationRepo.findById(laptops.getProviderId()).orElseThrow(()->new RuntimeException("Provider Not Found")));
        Laptops saved = laptopsRepo.save(item);
        return Mapper.convertToItemsResponse(saved);
    }

    @Override
    public List<ItemResponse> getAll() {
        List<Laptops> all = laptopsRepo.findAll();
      return  all.stream().map(Mapper::convertToItemsResponse).collect(Collectors.toList());
    }

    @Override
    public ItemResponse getLaptopById(Integer id) {
        Laptops items = laptopsRepo.findById(id).orElseThrow(() -> new RuntimeException("No item Found"));
        return Mapper.convertToItemsResponse(items);
    }

    @Override
    public Boolean removeLaptop(Integer id) {
        Laptops item = laptopsRepo.findById(id).orElseThrow(() -> new RuntimeException("No item Found"));
        laptopsRepo.delete(item);
        return true;
    }
}
