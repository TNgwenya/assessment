package investec.com.assessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    @NotBlank(message = "FirstName should not be blank")
    @NotNull(message = "FirstName should not be null")
    private String firstName;
    @NotBlank(message = "LastName should not be blank")
    @NotNull(message = "LastName should not be null")
    private String lastName;
    private String mobileNumber;
    @NotNull(message = "Id number should not be null")
    @Pattern(regexp = "^[0-9]{13}$", message = "Invalid Id Number, please try again.")
    private String identityNumber;
    private String physicalAddress;


}
