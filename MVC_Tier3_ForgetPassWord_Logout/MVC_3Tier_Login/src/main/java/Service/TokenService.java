package Service;

import java.time.LocalDateTime;

public interface TokenService {
    boolean isValid(String token);
    int getUserIdByToken(String token);
    void deleteToken(String token);
    void saveToken(int userId, String token, LocalDateTime expiry);
}
