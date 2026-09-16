package com.guilhermemendes.treinadorvirtual;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treinador")
@CrossOrigin(origins = "*")
public class TreinadorController {

    @GetMapping("/gerar")
    public TreinoResponse gerarTreino(@RequestParam String objetivo, @RequestParam int horaAtual) {
        TreinoResponse resposta = new TreinoResponse();

        String htmlTreino = "";
        String htmlRota = "";
        String htmlClima = "";

        // 1. Lógica do Treino e Rota
        if (objetivo.equals("iniciante")) {
            htmlTreino = "Caminhada leve (20 min) / Trote leve (5 min)";
            htmlRota = "Praça do Centro (Ambiente plano e ideal para iniciantes).";
        } else if (objetivo.equals("5k")) {
            htmlTreino = "Trote contínuo (20 min) / Tiros de 1 min correndo forte.";
            htmlRota = "Avenida principal até a região do Letreiro da Cidade.";
        } else if (objetivo.equals("10k")) {
            htmlTreino = "Corrida moderada (5 km) / Treino de ritmo (40 min).";
            htmlRota = "Trajeto longo pelas ruas e avenidas de Currais Novos.";
        }

        // 2. Lógica do Clima baseada na hora atual
        if (horaAtual >= 5 && horaAtual < 9) {
            htmlClima = "Clima matinal excelente (" + horaAtual + "h). Aproveite o sol fraco!";
        } else if (horaAtual >= 9 && horaAtual < 16) {
            htmlClima = "Atenção ao sol forte (" + horaAtual + "h)! Redobre a hidratação.";
        } else if (horaAtual >= 16 && horaAtual < 22) {
            htmlClima = "Fim de tarde/noite (" + horaAtual + "h). Ótimo momento para correr com temperatura mais amena.";
        } else {
            htmlClima = "Madrugada (" + horaAtual + "h). Cuidado com a iluminação das vias.";
        }

        resposta.setPlanilha(htmlTreino);
        resposta.setRota(htmlRota);
        resposta.setClima(htmlClima);

        return resposta;
    }
}