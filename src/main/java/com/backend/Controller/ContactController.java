package com.backend.Controller;

import com.backend.Services.ContactService;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.ContactTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllContact(){
        ApiResponse<List<ContactTable>> res = contactService.getAllContact();
        return new ResponseEntity<ApiResponse>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addContact(@RequestBody ContactTable contact){
        ApiResponse<ContactTable> res = contactService.addContact(contact);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteContact(@PathVariable Integer id){
        ApiResponse<ContactTable> res = contactService.deleteContact(id);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }

}
