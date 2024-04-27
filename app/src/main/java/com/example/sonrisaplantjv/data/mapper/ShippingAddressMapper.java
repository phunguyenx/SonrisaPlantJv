package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.ShippingAddress.ShippingAddressDto;
import com.example.sonrisaplantjv.domain.model.ShippingAddress.ShippingAddress;

public class ShippingAddressMapper implements Mapper<ShippingAddressDto, ShippingAddress>{
    @Override
    public ShippingAddress Map(ShippingAddressDto shippingAddressDto) {
        ShippingAddress address = new ShippingAddress();
        address.Id = shippingAddressDto.Id;
        address.Description = shippingAddressDto.Description;
        address.Name = shippingAddressDto.Name;
        address.Phone = shippingAddressDto.Phone;
        address.Status = shippingAddressDto.Status;
        return address;
    }
}
