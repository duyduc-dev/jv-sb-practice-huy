package com.example.practice.controllers;

import com.example.practice.controllers.models.request.AddressRequest;
import com.example.practice.controllers.models.response.PagingResponse;
import com.example.practice.entities.Address;
import com.example.practice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity getAllAddress() {
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAddressById(@PathVariable("id") String adddressId) {
        Address address = addressRepository.findById(adddressId).orElse(null);
        if(address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @PostMapping
    public ResponseEntity insertAddress(@RequestBody AddressRequest addressRequest) {
        Address address = Address.builder()
                .addressName(addressRequest.getAddressName())
                .userId(addressRequest.getUserId())
                .build();
        addressRepository.save(address);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/page")
    public ResponseEntity getPageAddress(
            @RequestParam(name = "search_key", required = false, defaultValue = "") String searchKey,
            @RequestParam(name = "page_number", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_direction", required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(new Sort.Order(sortDirection, "addressName"));
        Sort sort = Sort.by(sorts);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Address> addressPage = addressRepository.getPageAddress("%" + searchKey + "%", pageable);
        return ResponseEntity.ok(new PagingResponse(addressPage));
    }
}
