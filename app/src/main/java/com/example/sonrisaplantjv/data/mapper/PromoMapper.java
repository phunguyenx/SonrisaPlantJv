package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.Promo.PromoDto;
import com.example.sonrisaplantjv.domain.model.Promo.Promo;

public class PromoMapper implements Mapper<PromoDto, Promo>{

    @Override
    public Promo Map(PromoDto promoDto) {
        Promo promo = new Promo();
        promo.Id = promoDto.Id;
        promo.Name = promoDto.Name;
        promo.ValueDiscount = promoDto.ValueDiscount;
        return promo;
    }
}
