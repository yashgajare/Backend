package com.backend.Services;

import com.backend.Repository.ContactRepository;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.ContactTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public ApiResponse<List<ContactTable>> getAllContact() {
        return new ApiResponse<>(200, "success","List of all Contact forms : ",contactRepository.findAll());
    }

    public ApiResponse<ContactTable> addContact(ContactTable contact) {
        if(contact.getContactNo() == null || contact.getContactNo().isEmpty()) return new ApiResponse<>(400,"Failed","Contact must be provided");
        if(contact.getContactNo().length() != 10) return new ApiResponse<>(400,"Failed","Contact must contain 10 digit");
        if(contact.getDescription()==null || contact.getDescription().isEmpty()) return new ApiResponse<>(400,"Failed","Please Provide description");
        if(contact.getName()==null || contact.getName().isEmpty()) return new ApiResponse<>(400,"Failed","Name can't be empty");
        if(contact.getEmail()==null || contact.getEmail().isEmpty()) return new ApiResponse<>(400,"Failed","Please provide your email");
        if(!contact.getEmail().matches(EMAIL_REGEX)) return new ApiResponse<>(400,"Failed","Email badly formatted");
        Optional<ContactTable> ct = contactRepository.findOne(Example.of(contact));
        if(ct.isPresent()) return new ApiResponse<>(400,"Failed","We already have your query");
        contactRepository.save(contact);
        return new ApiResponse<>(200,"success","Thank you for contacting us");
    }

    public ApiResponse<ContactTable> deleteContact(Integer id) {
        Optional<ContactTable> ct = contactRepository.findById(id);
        if(!ct.isPresent()) return new ApiResponse<>(400,"Failed","Contact not found");
        contactRepository.deleteById(id);
        return new ApiResponse<>(200,"success","Contact delete successfully");
    }
}
