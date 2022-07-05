package com.esi.bpms.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "HBA1C_TBL")
public class HbA1c extends BilanParaclinique{

    private String glycemie_a_jeun;
    private String hemoglobine_glyquee;
}
