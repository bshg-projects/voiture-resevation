package com.example.voitureresevation.ws.restapi;

import com.example.voitureresevation.service.facade.VoitureService;
import com.example.voitureresevation.ws.converter.VoitureConverter;
import com.example.voitureresevation.ws.dto.VoitureDto;
import com.example.voitureresevation.zutils.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voiture")
public class VoitureRestApi {
    @Autowired
    private VoitureService service;
    @Autowired
    private VoitureConverter converter;

    @GetMapping("/id/{id}")
    public ResponseEntity<VoitureDto> findById(@PathVariable Long id) {
        var result = service.findById(id);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping
    public ResponseEntity<List<VoitureDto>> findAll() {
        var result = service.findAll();
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/optimized")
    public ResponseEntity<List<VoitureDto>> findAllOptimized() {
        var result = service.findAllOptimized();
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Pagination<VoitureDto>> findPaginated(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        var result = service.findPaginated(page, size);
        var pagination = result.convert(converter::toDto);
        return ResponseEntity.ok(pagination);
    }

    @PostMapping
    public ResponseEntity<VoitureDto> save(@RequestBody VoitureDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        var result = service.create(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PostMapping("/all")
    public ResponseEntity<List<VoitureDto>> save(@RequestBody List<VoitureDto> dtos) {
        if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dtos);
        var result = service.create(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping()
    public ResponseEntity<VoitureDto> update(@RequestBody VoitureDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        var result = service.update(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/all")
    public ResponseEntity<List<VoitureDto>> update(@RequestBody List<VoitureDto> dtos) {
        if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dtos);
        var result = service.update(item);
        var resultDto = converter.toDto(result);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping
    public ResponseEntity<VoitureDto> delete(@RequestBody VoitureDto dto) {
        if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var item = converter.toItem(dto);
        service.delete(item);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/all")
    public ResponseEntity<List<VoitureDto>> delete(@RequestBody List<VoitureDto> dtos) {
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
}