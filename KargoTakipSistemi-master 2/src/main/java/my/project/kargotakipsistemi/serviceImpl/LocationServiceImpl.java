package my.project.kargotakipsistemi.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.domain.Location;
import my.project.kargotakipsistemi.repositories.LocationRepository;
import my.project.kargotakipsistemi.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return this.locationRepository.findAll();
    }
}
