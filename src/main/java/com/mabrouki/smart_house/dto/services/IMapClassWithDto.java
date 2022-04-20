package com.mabrouki.smart_house.dto.services;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface IMapClassWithDto<E,D> {

    D convertToDto(E entity, Class<D> dtoClass);

    E convertToEntity(D dto, Class<E> entityClass);

    List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass);

    List<D> convertPageToListDto(Page<E> entityList, Class<D> outCLass);

    List<E> convertListToListEntity(Collection<D> dtoList, Class<E> outCLass);
}
