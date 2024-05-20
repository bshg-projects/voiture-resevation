package com.example.voitureresevation.ws.restapi;

import com.example.voitureresevation.service.facade.AdministrateurService;
import com.example.voitureresevation.ws.converter.AdministrateurConverter;
import com.example.voitureresevation.ws.dto.AdministrateurDto;
import com.example.voitureresevation.zutils.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurRestApi {
    @Autowired
    private AdministrateurService service;
    @Autowired
    private AdministrateurConverter converter;

    @GetMapping("/id/{id}")
    public ResponseEntity<AdministrateurDto> findById(@PathVariable Long id) {
        var result = service.findById(id);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping
    public ResponseEntity<List<AdministrateurDto>> findAll() {
        var result = service.findAll();
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/optimized")
    public ResponseEntity<List<AdministrateurDto>> findAllOptimized() {
        var result = service.findAllOptimized();
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Pagination<AdministrateurDto>> findPaginated(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        var result = service.findPaginated(page, size);
        var pagination = result.convert(converter::toDto);
        return ResponseEntity.ok(pagination);
    }

    @PostMapping
    public ResponseEntity<AdministrateurDto> save(@RequestBody AdministrateurDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        var result = service.create(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PostMapping("/all")
    public ResponseEntity<List<AdministrateurDto>> save(@RequestBody List<AdministrateurDto> dtos) {
        if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dtos);
        var result = service.create(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping()
    public ResponseEntity<AdministrateurDto> update(@RequestBody AdministrateurDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        var result = service.update(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/all")
    public ResponseEntity<List<AdministrateurDto>> update(@RequestBody List<AdministrateurDto> dtos) {
        if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dtos);
        var result = service.update(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping
    public ResponseEntity<AdministrateurDto> delete(@RequestBody AdministrateurDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        service.delete(item);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/all")
    public ResponseEntity<List<AdministrateurDto>> delete(@RequestBody List<AdministrateurDto> dtos) {
        if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dtos);
        service.delete(item);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/ids")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestParam("id") List<Long> ids) {
        service.deleteByIdIn(ids);
        return ResponseEntity.ok(ids);
    }

    @DeleteMapping("/reservations/id/{id}")
    public ResponseEntity<Long> deleteByReservationsId(@PathVariable Long id) {
        service.deleteByReservationsId(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/reservations/id/{id}")
    public ResponseEntity<List<AdministrateurDto>> findByReservationsId(@PathVariable Long id) {
        var result = service.findByReservationsId(id);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/offres/id/{id}")
    public ResponseEntity<Long> deleteByOffresId(@PathVariable Long id) {
        service.deleteByOffresId(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/offres/id/{id}")
    public ResponseEntity<List<AdministrateurDto>> findByOffresId(@PathVariable Long id) {
        var result = service.findByOffresId(id);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }
}