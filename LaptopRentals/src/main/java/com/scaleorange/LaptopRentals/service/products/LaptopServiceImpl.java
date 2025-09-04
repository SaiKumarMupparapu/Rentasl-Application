package com.scaleorange.LaptopRentals.service.products;

import com.scaleorange.LaptopRentals.dto.PageResponse;
import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.entity.Laptops;
import com.scaleorange.LaptopRentals.repo.ProductsRepo;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import com.scaleorange.LaptopRentals.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        item.setIsAvailable(Boolean.TRUE);
        Laptops saved = laptopsRepo.save(item);
        return Mapper.convertToItemsResponse(saved);
    }

    @Override
    public List<ItemResponse> getAll() {

        List<Laptops> all = laptopsRepo.findAll();
//        List<Laptops> allByPage = getAllByPage(0, 2);
//        System.out.println(allByPage);

        return  all.stream().map(Mapper::convertToItemsResponse).collect(Collectors.toList());
    }

    @Override
    public PageResponse<ItemResponse> getAllByPage(Integer pageNum, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNum, size);
        Page<Laptops> all = laptopsRepo.findAll(pageRequest);

        List<ItemResponse> laptops = all.getContent()
                .stream()
                .map(Mapper::convertToItemsResponse)
                .collect(Collectors.toList());
System.out.println(laptops);
        return new PageResponse<>(
                laptops,
                all.getNumber(),       // current page
                all.getSize(),         // page size
                all.getTotalElements(),// total items
                all.getTotalPages()    // total pages
        );
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
