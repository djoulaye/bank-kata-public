package fr.ing.interview.exposition.adapter;

import fr.ing.interview.domain.Operation;
import fr.ing.interview.exposition.config.DtoMapperConfig;
import fr.ing.interview.exposition.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DtoMapperConfig.class)
public interface OperationAdapter {

    @Mapping(target = "date", source = "operationDate")
    OperationDto convert(Operation operation);

}
