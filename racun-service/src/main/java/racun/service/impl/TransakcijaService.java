package racun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racun.model.Racun;
import racun.model.Transakcija;
import racun.repository.RacunRepository;
import racun.repository.TransakcijaRepository;
import racun.repository.ValutaRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TransakcijaService {

    private final RacunRepository racunRepository;
    private final SredstvaKapitalService sredstvaKapitalService;
    private final TransakcijaRepository transakcijaRepository;
    private final ValutaRepository valutaRepository;

    @Autowired
    public TransakcijaService(RacunRepository racunRepository, SredstvaKapitalService sredstvaKapitalService, TransakcijaRepository transakcijaRepository, ValutaRepository valutaRepository) {
        this.racunRepository = racunRepository;
        this.sredstvaKapitalService = sredstvaKapitalService;
        this.transakcijaRepository = transakcijaRepository;
        this.valutaRepository = valutaRepository;
    }

    public List<Transakcija> getAll(String username){
        return transakcijaRepository.findByUsername(username);
    }

    public Transakcija dodajTransakciju(String username, UUID brojRacuna, String opis, String valuta, long iznos){
        Transakcija t = new Transakcija();
        Racun racun = racunRepository.findByBrojRacuna(brojRacuna);
        t.setRacun(racun);
        t.setUsername(username);
        t.setValuta(valutaRepository.findValutaByOznakaValute(valuta));
        t.setOpis(opis);
        if (iznos>0){
            t.setUplata(iznos);
        }else {
            t.setIsplata(iznos);
        }

        sredstvaKapitalService.updateStanje(username,brojRacuna,iznos,0,0);
        return transakcijaRepository.save(t);
    }



}
