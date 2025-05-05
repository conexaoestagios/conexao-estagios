package br.com.conexaoestagios.enums;

import lombok.Getter;

@Getter
public enum Modality {

    PRESENCIAL("vaga totalmente presencial"),
    REMOTO("vaga totalmente remota"),
    HIBRIDO("vaga presencial com pelo menos um dia de trabalho remoto");

    private final String description;

    Modality(String description) {
        this.description = description;
    }

}
