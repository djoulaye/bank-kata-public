package com.bellagio.exposition.adapter;

import com.bellagio.domain.Account;
import com.bellagio.exposition.config.DtoMapperConfig;
import com.bellagio.exposition.dto.AccountHistoryDto;
import org.mapstruct.Mapper;

@Mapper(config = DtoMapperConfig.class, uses = {OperationAdapter.class})
public interface AccountHistoryAdapter {

    AccountHistoryDto convert(Account account);
}
