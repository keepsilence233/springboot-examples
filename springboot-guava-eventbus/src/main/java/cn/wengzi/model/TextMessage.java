package cn.wengzi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class TextMessage implements Serializable {

    private String message;
    private String mobile;
}
