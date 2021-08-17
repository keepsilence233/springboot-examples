package cn.wengzi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wengzi
 * @date 2019/11/5 night 22:09
 * @description 学生证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCard {
    private Integer cardId;
    private String cardInfo;
}
