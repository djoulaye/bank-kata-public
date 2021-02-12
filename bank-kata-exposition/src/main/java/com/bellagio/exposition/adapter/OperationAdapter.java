package com.bellagio.exposition.adapter;

import com.bellagio.domain.Operation;
import com.bellagio.exposition.config.DtoMapperConfig;
import com.bellagio.exposition.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DtoMapperConfig.class)
public interface OperationAdapter {

    @Mapping(target = "date", source = "operationDate")
    OperationDto convert(Operation operation);

}
