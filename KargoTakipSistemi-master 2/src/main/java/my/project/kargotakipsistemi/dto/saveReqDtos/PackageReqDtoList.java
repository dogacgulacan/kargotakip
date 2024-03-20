package my.project.kargotakipsistemi.dto.saveReqDtos;

import java.util.ArrayList;
import java.util.List;

public class PackageReqDtoList {
    private List<PackageReqDto> packageReqDtos;

    public PackageReqDtoList() {
        this.packageReqDtos = new ArrayList<>();
    }

    // getter ve setter
    public List<PackageReqDto> getPackageReqDtos() {
        return packageReqDtos;
    }

    public void setPackageReqDtos(List<PackageReqDto> packageReqDtos) {
        this.packageReqDtos = packageReqDtos;
    }
}