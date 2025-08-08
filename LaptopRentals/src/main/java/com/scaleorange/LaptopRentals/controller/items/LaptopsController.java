package com.scaleorange.LaptopRentals.controller.items;


import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.service.laptops.LaptopService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@PreAuthorize("hasAuthority('PROVIDER')")
public class LaptopsController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping("/save")
    public ItemResponse saveItems(@RequestBody ItemRequest item){
     return laptopService.saveItem(item);
    }

    @GetMapping("/all")
    public List<ItemResponse> listOfItems(){
        return laptopService.getAll();
    }

    @DeleteMapping("/clear")
   public String removeLaptop(@PathParam("id") Integer id){
       return laptopService.removeLaptop(id) ? "Successfully delete" : "failed to Delete";
   }

   @PutMapping("/update")
   public ItemResponse modifyItem(@RequestBody ItemRequest request){
      return laptopService.saveItem(request);
   }

}
