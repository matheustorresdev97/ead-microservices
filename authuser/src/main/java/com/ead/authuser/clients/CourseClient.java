package com.ead.authuser.clients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.ead.authuser.dtos.ResponsePageDto;
import com.ead.authuser.records.CourseRecord;
import com.ead.authuser.services.UtilsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CourseClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsService utilsService;

    String REQUEST_URL_COURSE = "http://localhost:8082";

    @SuppressWarnings("null")
    public Page<CourseRecord> getAllCoursesByUser(UUID userId, Pageable pageable) {
        List<CourseRecord> searchResult = null;
        String url = REQUEST_URL_COURSE + utilsService.createUrlGetAllCoursesByUser(userId, pageable);
        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);
        try {
            ParameterizedTypeReference<ResponsePageDto<CourseRecord>> responseType = new ParameterizedTypeReference<ResponsePageDto<CourseRecord>>() {
            };
            ResponseEntity<ResponsePageDto<CourseRecord>> result = restTemplate.exchange(url, HttpMethod.GET, null,
                    responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /courses {} ", e);
        }
        log.info("Ending request /courses userId {} ", userId);
        return new PageImpl<>(searchResult);
    }

}
