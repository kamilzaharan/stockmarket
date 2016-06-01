package pl.lodz.p.model;

import javax.persistence.*;

/**
 * Created by m.a.szymczynska on 2016-05-29.
 */

@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAZWA_WALUTY")
    private String nazwaWaluty;

    @Column(name = "KOD_WALUTY")
    private String kodWaluty;

    @Column(name = "KURS_SREDNI")
    private double kursSredni;

    @Column(name = "NBP_ID_PLIKU")
    private String idPliku;
}
