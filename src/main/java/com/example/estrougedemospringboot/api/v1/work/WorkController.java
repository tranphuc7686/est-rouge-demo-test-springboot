package com.example.estrougedemospringboot.api.v1.work;

import com.example.estrougedemospringboot.models.Work;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/works")
public class WorkController {

    WorkRepository workRepository;

    public WorkController(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get works using paginate and sort ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "work not found")})
    public ResponseEntity<Object> getWorks(Pageable pageable) {
        Page<Work> workPage = workRepository.findAll(pageable);
        return new ResponseEntity<>(workPage.getContent(), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @Operation(summary = "Create work ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "work not found")})
    public Work createWork(@Valid @RequestBody Work Work) {
        return workRepository.save(Work);
    }

    @PutMapping(value = "/{workId}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @Operation(summary = "Update work ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create successful"),
            @ApiResponse(responseCode = "404", description = "work not found")})
    public Work updateWork(@PathVariable Integer workId,
                           @Valid @RequestBody Work workRequest) {
        return workRepository.findById(workId)
                .map(e -> {
                    e.setWorkName(workRequest.getWorkName());
                    e.setStatus(workRequest.getStatus());
                    e.setStartingDate(workRequest.getStartingDate());
                    e.setEndingDate(workRequest.getEndingDate());
                    return workRepository.save(e);
                }).orElseThrow(() -> new ResourceNotFoundException("Work not found with id " + workId));
    }

    @DeleteMapping("/{workId}")
    @Operation(summary = "Delete work")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successful"),
            @ApiResponse(responseCode = "404", description = "work not found")})
    public ResponseEntity<?> deleteWork(@PathVariable Integer workId) {
        return workRepository.findById(workId)
                .map(Work -> {
                    workRepository.delete(Work);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Work not found with id" + workId));
    }
}
