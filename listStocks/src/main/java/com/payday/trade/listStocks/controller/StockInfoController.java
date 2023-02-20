package com.payday.trade.listStocks.controller;

import com.payday.trade.listStocks.dto.TickerInfoDTO;

import com.payday.trade.listStocks.dto.TickerListDTO;
import com.payday.trade.listStocks.service.ListStockService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "listStockAPI")
@RequiredArgsConstructor
public class StockInfoController {
    private final ListStockService listStockService;

    @GetMapping()
    public List<TickerListDTO> findTickersLists() throws IOException, JSONException {
        return listStockService.findTickersLists();
    }

    @GetMapping(value = "/{tickerName}/{date1}/{date2}")
    public TickerInfoDTO findTickerDataByName(@PathVariable String tickerName, @PathVariable String date1, @PathVariable String date2) throws IOException, JSONException {
        return listStockService.findTickerByName(tickerName, date1, date2);
    }

}
