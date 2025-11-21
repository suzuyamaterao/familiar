// ...existing code...
package com.example.lateArrivalReportingApp.service;

import org.springframework.stereotype.Service;
import com.example.lateArrivalReportingApp.repository.UserRepository;
import com.example.lateArrivalReportingApp.model.UsersMst;
import java.util.Optional;

/**
 * ログイン認証サービス
 */
@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 指定した empId と password で認証を行う。
     * 空/ヌルの入力や、ユーザ未存在時は false を返す。
     */
    public boolean authenticate(String empId, String password) {
        if (empId == null || empId.isBlank() || password == null) {
            return false;
        }

        Optional<UsersMst> opt = userRepository.findByEmpId(empId.trim());
        if (opt.isEmpty()) {
            return false;
        }

        String storedHash = opt.get().getPassword();
        return password.toString().equalsIgnoreCase(storedHash);
    }

}