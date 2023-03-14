package investec.com.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "buildClient")
@NoArgsConstructor
public class Client {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String identityNumber;
    private String physicalAddress;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client other = (Client) obj;
        return this.lastName.equals(other.lastName)
                && this.identityNumber.equals(other.identityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, identityNumber);
    }
}
