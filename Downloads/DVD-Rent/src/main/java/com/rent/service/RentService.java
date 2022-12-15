package com.rent.service;

import com.rent.model.entity.Rent;
import com.rent.model.request.RentConfirmRequest;
import com.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class RentService extends AbstractService<Rent> {

    @Autowired
    private PriceService priceService;

    @Autowired
    private PenaltyService penaltyService;

    @Autowired
    private RentRepository repository;

    public RentService(JpaRepository<Rent, Long> repository) {
        super(repository);
    }

    public List<Rent> findAllRent(){
        return repository.findAllByReturnDateIsNull();
    }

    public Long income(){
        Long income = 0L;

        for (Rent rent : repository.findAllByReturnDateIsNotNull()) {
            income += rent.getTotalPaid();
        }
        return income;
    }

    public RentConfirmRequest rentConfirm(Rent rent){
        RentConfirmRequest response = new RentConfirmRequest(rent);
        Long total = 0L;
        Long denda = 0L;

        if (!Objects.isNull(rent.getDvdCondition())){
            denda = penaltyService.condition(rent.getDvdCondition()).getPrice();
        }

        Long price = priceService.status(rent.getDvd().getNewFilm()).getPrice();
        Long jumlahHari = ChronoUnit.DAYS.between(rent.getRentDate(), LocalDate.now());
        total += jumlahHari * price ;
        String ketJumlah = jumlahHari + " HARI x " + price + " = " + total + " - MEMBERSHIP( " +
                (rent.getMember().getMembership() ? "MEMBERSHIP" : "TIDAK") + " )";
        Long discount = rent.getMember().getMembership() ? (total/10) : 0;
        System.out.println("DISCOUNT " + discount);
        total -= discount;
        total += denda;
        response.setDenda(denda);
        response.setKetJumlah(ketJumlah);
        response.setTotal(total);
        response.setTotalPaid(total - rent.getDeposit());

        return response;
    }
}
