package com.example.bookstore.controller;

import com.example.bookstore.dto.StatisticDto;
import com.example.bookstore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/getMonthlyOrderStatistic")
    public ResponseEntity<List<StatisticDto>> getMonthlyOrderStatistic() {
        List<StatisticDto> monthlyOrderStatistic = statisticService.getMonthlyOrderStatistic();
        return new ResponseEntity<>(monthlyOrderStatistic, HttpStatus.OK);
    }
}
