package cn.wengzi.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grade implements Serializable {
    private static final long serialVersionUID = 7952005566052582447L;

    private Integer id;
    private String gradeName;

}
