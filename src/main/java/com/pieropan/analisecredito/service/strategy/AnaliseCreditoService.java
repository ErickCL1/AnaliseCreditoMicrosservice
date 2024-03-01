package com.pieropan.analisecredito.service.strategy;

import com.pieropan.analisecredito.Domain.Proposta;
import com.pieropan.analisecredito.exceptions.StrategyException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnaliseCreditoService {

    @Autowired
    private List<CalculoPonto> calculoPontoList;

    @Value("{rabbitmq.propostaconcluida.exchange}")
    private String exchangePropostaConcluida;

    @Autowired
    private NotificacaoRabbitService notificacaoRabbitService;

    public void analistar(Proposta proposta){
        try{
            boolean aprovada = calculoPontoList.stream()
                    .mapToInt(impl -> impl.calcular(proposta)).sum() > 350;
            proposta.setAprovada(aprovada);
        } catch (StrategyException ex){
            proposta.setAprovada(false);
        }

        notificacaoRabbitService.notificar(exchangePropostaConcluida,proposta);
    }
}
