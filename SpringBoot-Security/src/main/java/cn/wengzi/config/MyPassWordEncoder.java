package cn.wengzi.config.eventbus;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wengzi
 * @date 2019/11/8 wee hours 00:06
 * @description 对输入密码进行编码
 */
public class MyPassWordEncoder implements PasswordEncoder {
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
