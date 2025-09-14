package Service.impl;

import Service.TokenService;
import Entity.PasswordResetToken;
import Configs.JPAConfig; 

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;

public class TokenServiceImpl implements TokenService {

    @Override
    public boolean isValid(String token) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<PasswordResetToken> query = em.createQuery(
                    "SELECT t FROM PasswordResetToken t WHERE t.token = :token", PasswordResetToken.class);
            query.setParameter("token", token);

            PasswordResetToken prt = query.getResultStream().findFirst().orElse(null);
            if (prt != null && prt.getExpiryTime() != null) {
                return !prt.getExpiryTime().isBefore(LocalDateTime.now());
            }
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public int getUserIdByToken(String token) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<PasswordResetToken> query = em.createQuery(
                    "SELECT t FROM PasswordResetToken t WHERE t.token = :token", PasswordResetToken.class);
            query.setParameter("token", token);

            PasswordResetToken prt = query.getResultStream().findFirst().orElse(null);
            return (prt != null) ? prt.getUserId() : -1;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteToken(String token) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<PasswordResetToken> query = em.createQuery(
                    "SELECT t FROM PasswordResetToken t WHERE t.token = :token", PasswordResetToken.class);
            query.setParameter("token", token);
            PasswordResetToken prt = query.getResultStream().findFirst().orElse(null);

            if (prt != null) {
                em.remove(prt);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void saveToken(int userId, String token, LocalDateTime expiry) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            PasswordResetToken prt = new PasswordResetToken(userId, token, expiry);
            em.persist(prt);
            tx.commit();

            System.out.println("Token đã lưu cho userId=" + userId + ", hết hạn lúc " + expiry);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Hàm tiện ích: tạo token mặc định hết hạn sau 15 phút
    public void saveTokenWithDefaultExpiry(int userId, String token) {
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);
        saveToken(userId, token, expiry);
    }
}
