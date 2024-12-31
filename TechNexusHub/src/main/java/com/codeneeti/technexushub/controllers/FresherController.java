package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.*;
import com.codeneeti.technexushub.services.FresherService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/freshers")
public class FresherController {

    @Autowired
    private FresherService fresherService;

    private Logger logger = LoggerFactory.getLogger(FresherController.class);

    // Create a new Fresher
    @PostMapping("/create")
    public ResponseEntity<FresherDTO> createFresher(@Valid @RequestBody FresherDTO fresherDTO) {
        FresherDTO createdFresher = fresherService.createFresher(fresherDTO);
        return new ResponseEntity<>(createdFresher, HttpStatus.CREATED);
    }
//    // Login API
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            // Call the service layer to handle login logic
//            String token = fresherService.login(loginRequest);
//            return new ResponseEntity<>(token, HttpStatus.OK); // Return JWT token if login is successful
//        } catch (RuntimeException e) {
//            // In case of invalid credentials or other errors
//            return new ResponseEntity<>("Invalid credentials or error during login", HttpStatus.UNAUTHORIZED);
//        }
//    }

//    // Login API - Verify fresher credentials
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
//        try {
//            // Call the service layer to handle login logic
//            String result = fresherService.login(email, password); // Return success message or failure
//            return new ResponseEntity<>(result, HttpStatus.OK); // Return login result
//        } catch (RuntimeException e) {
//            // In case of invalid credentials or other errors
//            return new ResponseEntity<>("Invalid credentials or error during login", HttpStatus.UNAUTHORIZED);
//        }
//    }



    // Login API
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Call the service layer to handle login logic
            boolean loginSuccess = fresherService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

            if (loginSuccess) {
                // Return successful response with a custom message and data
                ApiResponse response = ApiResponse.builder()
                        .success(true)
                        .data(HttpStatus.OK)  // You can replace this with some data (e.g., fresher ID)
                        .message("Login successful")
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Return failed response
                ApiResponse response = ApiResponse.builder()
                        .success(false)
                        .data(HttpStatus.UNAUTHORIZED)  // You can replace this with some error code or message
                        .message("Invalid credentials")
                        .build();
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            ApiResponse response = ApiResponse.builder()
                    .success(false)
                    .data(null)
                    .message("An error occurred: " + e.getMessage())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update an existing Fresher
    @PutMapping("/update/{fresherId}")
    public ResponseEntity<FresherDTO> updateFresher(
            @PathVariable("fresherId") String fresherId,
            @Valid @RequestBody FresherDTO fresherDTO) {
        FresherDTO updatedFresher = fresherService.updateFresher(fresherId, fresherDTO);
        return new ResponseEntity<>(updatedFresher, HttpStatus.OK);
    }

    // Delete a Fresher by ID
    @DeleteMapping("/delete/{fresherId}")
    public ResponseEntity<ApiResponseMessage> deleteFresher(@PathVariable("fresherId") String fresherId) {
        fresherService.deleteFresher(fresherId);
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("Fresher deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Get all Freshers with pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<PageableResponse<FresherDTO>> getAllFreshers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "firstName", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        PageableResponse<FresherDTO> fresherPageResponse = fresherService.getAllFreshers(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(fresherPageResponse, HttpStatus.OK);
    }

    // Get a Fresher by ID
    @GetMapping("/{fresherId}")
    public ResponseEntity<FresherDTO> getFresherById(@PathVariable("fresherId") String fresherId) {
        FresherDTO fresherDTO = fresherService.getFresherById(fresherId);
        return new ResponseEntity<>(fresherDTO, HttpStatus.OK);
    }

    // Get a Fresher by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<FresherDTO> getFresherByEmail(@PathVariable("email") String email) {
        FresherDTO fresherDTO = fresherService.getFresherByEmail(email);
        return new ResponseEntity<>(fresherDTO, HttpStatus.OK);
    }

//    // Search Freshers by First Name
//    @GetMapping("/search/{keyword}")
//    public ResponseEntity<List<FresherDTO>> searchFreshers(@PathVariable("keyword") String keyword) {
//        List<FresherDTO> fresherList = fresherService.searchFresherByFirstName(keyword);
//        return new ResponseEntity<>(fresherList, HttpStatus.OK);
//    }
}
