package logic.service;

import domain.Identity;
import domain.Person;

import java.sql.SQLException;

public interface IdentityService {
   Identity readIdentity(Person person) throws SQLException;
}
