package com.payday.trade.listStocks.service;

import com.payday.trade.listStocks.dto.TickerInfoDTO;
import com.payday.trade.listStocks.dto.TickerListDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface ListStockService {
    List<TickerListDTO> findTickersLists() throws JSONException, IOException;
  TickerInfoDTO findTickerByName(String tickerName, String date1, String date2) throws JSONException, IOException;
}
