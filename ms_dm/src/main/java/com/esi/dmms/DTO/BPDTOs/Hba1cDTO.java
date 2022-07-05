package com.esi.dmms.DTO.BPDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hba1cDTO extends BPDTO{

    private String glycemie_a_jeun;
    private String hemoglobine_glyquee;
}
