package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvacuationDTO {
    private Long id;
    private String createDate;
    private String objet;
    private String zonetxt;

}
