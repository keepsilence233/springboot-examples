package qx.leizige.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getDepartmentManager(String user) {
        log.info("getDepartmentManager params : {}", user);
        return user;
    }
}
