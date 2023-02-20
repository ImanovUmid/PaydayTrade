package com.payday.trade.listStocks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payday.trade.listStocks.dto.TickerInfoDTO;
import com.payday.trade.listStocks.dto.TickerListDTO;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListStockServiceImpl implements ListStockService {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public List<TickerListDTO> findTickersLists() throws JSONException, IOException {
        JSONObject json = readJsonFromUrl("https://api.polygon.io/v3/reference/tickers?active=true&apiKey=6fOsmTr7jgiwECvpUmufytNETj53B5ou");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<TickerListDTO>> mapType = new TypeReference<List<TickerListDTO>>() {
        };
        List<TickerListDTO> jsonToTickerListDTO = objectMapper.readValue(json.get("results").toString(), mapType);
        return jsonToTickerListDTO;
    }

    @Override
    public TickerInfoDTO findTickerByName(String tickerName, String date1, String date2) throws JSONException, IOException {
        JSONObject json = readJsonFromUrl("https://api.polygon.io/v2/aggs/ticker/" + tickerName + "/range/1/day/" + date1 + "/" + date2 + "?apiKey=HpvJarUrnoE5CB0dji3S7bKZVXORftxb");
        ObjectMapper objectMapper = new ObjectMapper();
        TickerInfoDTO jsonToTickerInfoDTO = objectMapper.readValue(json.toString(), TickerInfoDTO.class);
        return jsonToTickerInfoDTO;
    }
}
