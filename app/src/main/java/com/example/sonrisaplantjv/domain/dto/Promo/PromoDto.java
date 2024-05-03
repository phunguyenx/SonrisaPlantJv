package com.example.sonrisaplantjv.domain.dto.Promo;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.util.UUID;

public class PromoDto extends ActionResponse<PromoDto> {
    public UUID Id;
    public String Name;
    public byte ValueDiscount;
    
}
