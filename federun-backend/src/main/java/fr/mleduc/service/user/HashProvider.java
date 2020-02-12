package fr.mleduc.service.user;

public interface HashProvider {
    boolean checkPassword(String password, String hashedPassword);

    String hashPassword(String password);
}
