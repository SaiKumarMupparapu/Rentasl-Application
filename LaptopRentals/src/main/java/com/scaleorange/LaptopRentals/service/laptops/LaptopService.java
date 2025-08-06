package com.scaleorange.LaptopRentals.service.laptops;

import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;


import java.util.List;

public interface LaptopService {

    public ItemResponse saveItem(ItemRequest laptops) ;

    public List<ItemResponse> getAll();

    public ItemResponse getLaptopById(Integer id);

    public Boolean removeLaptop(Integer id);


}
