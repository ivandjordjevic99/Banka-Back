package si.banka.berza.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuturesUgovorDto {

    private Long id_hartije_od_vrednosti;
    private String oznaka_hartije;
    private Double cena;
    private Double promena_iznos;
    private Long volume;
    private Double maintenance_margin;

}
