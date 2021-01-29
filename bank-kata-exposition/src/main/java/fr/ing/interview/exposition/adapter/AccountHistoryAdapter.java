package fr.ing.interview.exposition.adapter;

import fr.ing.interview.domain.Account;
import fr.ing.interview.exposition.config.DtoMapperConfig;
import fr.ing.interview.exposition.dto.AccountHistoryDto;
import org.mapstruct.Mapper;

@Mapper(config = DtoMapperConfig.class, uses = {OperationAdapter.class})
public interface AccountHistoryAdapter {

    AccountHistoryDto convert(Account account);
}
