package com.pieropan.analisecredito.service.strategy.impl;

import com.pieropan.analisecredito.Domain.Proposta;
import com.pieropan.analisecredito.exceptions.StrategyException;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {


    @Override
    public int calcular(Proposta proposta) {
        int score = score();

        if (score <= 200) {
            throw new StrategyException("Score baixo");

        } else if (score <= 400) {
            return 150;
        } else if (score <= 600) {
            return 200;
        } else {
            return 250;
        }
    }

    private int score() {
        return new Random().nextInt(0,1000);
    }
}
