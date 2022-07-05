package esi_cardio.com.auth_cardio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGlucoseDTO {

    private Date dateEnregistrement;
    private float value;
}
