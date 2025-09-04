package com.scaleorange.LaptopRentals.controller.products;


import com.scaleorange.LaptopRentals.dto.PageResponse;
import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.service.products.LaptopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@PreAuthorize("hasAnyAuthority('COMPANY', 'PROVIDER')")
@CrossOrigin(origins = "http://localhost:4200")
public class LaptopsController {

    @Autowired
    private LaptopService laptopService;

    @PreAuthorize("hasAuthority('PROVIDER')")
    @PostMapping("/save")
    public ItemResponse saveItems(@RequestBody ItemRequest item){
     return laptopService.saveItem(item);
    }

    @GetMapping("/all")
    public List<ItemResponse> listOfItems(){
        return laptopService.getAll();
    }

//    @PreAuthorize("hasAnyAuthority('PROVIDER', 'COMPANY')")
    @GetMapping("/prds")
    public ResponseEntity<PageResponse<ItemResponse>> listOfItems(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
System.err.println(page +" "+size);
        return ResponseEntity.ok(laptopService.getAllByPage(page, size));
    }



    @PreAuthorize("hasAuthority('PROVIDER')")
    @DeleteMapping("/clear")
   public String removeLaptop(@RequestParam("id") Integer id){
       return laptopService.removeLaptop(id) ? "Successfully delete" : "failed to Delete";
   }

   @PreAuthorize("hasAuthority('PROVIDER')")
   @PutMapping("/update")
   public ItemResponse modifyItem(@RequestBody ItemRequest request){
      return laptopService.saveItem(request);
   }

}
