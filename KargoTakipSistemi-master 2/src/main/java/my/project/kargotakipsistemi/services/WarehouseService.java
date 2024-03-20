package my.project.kargotakipsistemi.services;

import my.project.kargotakipsistemi.dto.getRespDtos.WarehouseRespDto;

import java.util.List;

public interface WarehouseService {
    List<WarehouseRespDto> findAll();
}
