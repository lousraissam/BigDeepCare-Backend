package com.esi.dmms.DTO.BPDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LipidiqueDTO extends BPDTO{

    private String creatinine;
    private String triglycerides;
    private String cholesterolTotal;
    private String hdlCholesterol;
    private String ldlCholesterol;
}
