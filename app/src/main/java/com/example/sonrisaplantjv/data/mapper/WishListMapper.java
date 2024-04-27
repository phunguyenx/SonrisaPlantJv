package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.WishList.WishListDto;
import com.example.sonrisaplantjv.domain.model.WishList.WishList;

public class WishListMapper implements Mapper<WishListDto, WishList>{
    @Override
    public WishList Map(WishListDto wishListDto) {
        WishList wishList = new WishList();
        wishList.IdPlant = wishListDto.IdPlant;
        wishList.IdUser = wishListDto.IdUser;
        return wishList;
    }
}
