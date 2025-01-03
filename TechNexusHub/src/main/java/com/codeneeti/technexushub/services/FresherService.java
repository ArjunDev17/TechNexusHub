package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.FresherDTO;
import com.codeneeti.technexushub.dtos.PageableResponse;

public interface FresherService {

    FresherDTO createFresher(FresherDTO fresherDTO);

    FresherDTO updateFresher(String fresherId, FresherDTO fresherDTO);
    FresherDTO updateFresherByEmail(String fresherId, FresherDTO fresherDTO);

    void deleteFresher(String fresherId);

    PageableResponse<FresherDTO> getAllFreshers(int pageNumber, int pageSize, String sortBy, String sortDir);

    FresherDTO getFresherById(String fresherId);

    FresherDTO getFresherByEmail(String email);

    String login(String email, String password);  // Simplified login method

    public boolean authenticate(String email, String password) ;

}
