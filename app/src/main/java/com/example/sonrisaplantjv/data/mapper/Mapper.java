package com.example.sonrisaplantjv.data.mapper;

public interface Mapper<TEntityDto,TEntity> {
    TEntity Map(TEntityDto entityDto);
}
