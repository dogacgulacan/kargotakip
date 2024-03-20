package my.project.kargotakipsistemi.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.dto.getRespDtos.WarehouseRespDto;
import my.project.kargotakipsistemi.repositories.WarehouseRepository;
import my.project.kargotakipsistemi.services.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public List<WarehouseRespDto> findAll() {
        return this.warehouseRepository.findCustomAll();
    }
}
