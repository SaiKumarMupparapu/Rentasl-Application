package com.scaleorange.LaptopRentals.service.products;

import com.scaleorange.LaptopRentals.dto.PageResponse;
import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.entity.Laptops;


import java.util.List;

public interface LaptopService {

    public ItemResponse saveItem(ItemRequest laptops) ;

    public List<ItemResponse> getAll();

    public PageResponse<ItemResponse> getAllByPage(Integer pageNum, Integer size) ;

    public ItemResponse getLaptopById(Integer id);

    public Boolean removeLaptop(Integer id);


}
