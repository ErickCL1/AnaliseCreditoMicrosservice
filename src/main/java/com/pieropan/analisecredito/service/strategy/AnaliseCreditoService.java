package com.pieropan.analisecredito.service.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnaliseCreditoService {

    private List<CalculoPonto> calculoPontoList;

}
