package my.project.kargotakipsistemi.dto.saveReqDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSaveReqDto {

    private String name;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phoneNumber;
}
