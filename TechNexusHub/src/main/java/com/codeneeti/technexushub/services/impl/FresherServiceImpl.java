package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.FresherDTO;
import com.codeneeti.technexushub.dtos.LoginRequest;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.entities.Fresher;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.repositories.FresherRepository;

import com.codeneeti.technexushub.services.FresherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FresherServiceImpl implements FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FresherDTO createFresher(FresherDTO fresherDTO) {
        // Convert FresherDTO to Fresher entity
        Fresher fresher = modelMapper.map(fresherDTO, Fresher.class);
        // Save fresher entity
        fresherRepository.save(fresher);
        // Convert saved fresher entity back to FresherDTO
        return modelMapper.map(fresher, FresherDTO.class);
    }

    @Override
    public FresherDTO updateFresher(String fresherId, FresherDTO fresherDTO) {
        Fresher fresher = fresherRepository.findById(Long.valueOf(fresherId))
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with ID: " + fresherId));
        fresher.setFirstName(fresherDTO.getFirstName());
//        fresher.setLastName(fresherDTO.getLastName());
        fresher.setEmail(fresherDTO.getEmail());
        fresher.setPhoneNumber(fresherDTO.getPhoneNumber());
//        fresher.setUsername(fresherDTO.getUsername());
        fresher.setPassword(fresherDTO.getPassword());
//        fresher.setGender(fresherDTO.getGender());
        fresherRepository.save(fresher);
        return modelMapper.map(fresher, FresherDTO.class);
    }

    @Override
    public void deleteFresher(String fresherId) {
        Fresher fresher = fresherRepository.findById(Long.valueOf(fresherId))
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with ID: " + fresherId));
        fresherRepository.delete(fresher);
    }

    @Override
    public PageableResponse<FresherDTO> getAllFreshers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Fresher> fresherPage = fresherRepository.findAll(pageable);
        return PageableResponse.createResponse(fresherPage, FresherDTO.class);
    }

    @Override
    public FresherDTO getFresherById(String fresherId) {
        Fresher fresher = fresherRepository.findById(Long.valueOf(fresherId))
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with ID: " + fresherId));
        return modelMapper.map(fresher, FresherDTO.class);
    }

    @Override
    public FresherDTO getFresherByEmail(String email) {
        Fresher fresher = fresherRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with email: " + email));
        return modelMapper.map(fresher, FresherDTO.class);
    }

    @Override
    public String login(String email, String password) {
        // Fetch fresher by email
        Optional<Fresher> fresherOptional = fresherRepository.findByEmail(email);

        if (fresherOptional.isPresent()) {
            Fresher fresher = fresherOptional.get();

            // Simple password check (no encoding/decoding)
            if (fresher.getPassword().equals(password)) {
                return "Login successful";  // Return a simple success message
            } else {
                throw new RuntimeException("Invalid password");  // If passwords don't match
            }
        } else {
            throw new RuntimeException("Fresher not found");  // If fresher is not found
        }
    }


    public boolean authenticate(String email, String password) {
        // Add logic to check if the email and password are correct
        Optional<Fresher> fresher = fresherRepository.findByEmail(email);
        if (fresher.isPresent() && password.equals(fresher.get().getPassword())) {
            return true; // Successful login
        }
        return false; // Invalid credentials
    }


}
//    // Implement the login method
//    @Override
//    public String login(LoginRequest loginRequest) {
//        // Fetch fresher by email
//        Fresher fresher = fresherRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with email: " + loginRequest.getEmail()));
//
//        // Verify password
//        if (!passwordEncoder.matches(loginRequest.getPassword(), fresher.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        // Generate JWT token
//        return jwtTokenUtil.generateToken(fresher);
//    }
//
