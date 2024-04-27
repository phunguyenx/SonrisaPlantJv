package com.example.sonrisaplantjv.data.remote.dto.ShippingAddress;


import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.domain.model.ShippingAddress.AddressStatus;
import com.example.sonrisaplantjv.domain.model.ShippingAddress.ShippingAddress;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShippingAddressDto extends ActionResponse<ShippingAddressDto> {
    public UUID Id;
    public String Name;
    public String Phone;
    public String Description;
    public AddressStatus Status;
}

