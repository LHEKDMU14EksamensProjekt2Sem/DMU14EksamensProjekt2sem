package data.access;

import domain.Employee;
import domain.Person;
import domain.User;
import util.jdbc.ConnectionHandler;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserAccessImpl implements UserAccess {
   private static final String HASH_FUNCTION = "SHA-512";

   private final ConnectionHandler con;

   public UserAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createUsers(List<User<Employee>> users, List<char[]> passwords) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (int i = 0; i < users.size(); i++) {
            User<Employee> user = users.get(i);
            st.setInt(1, user.getEntity().getId());
            st.setString(2, user.getUsername());

            byte[] salt = createSalt();
            byte[] hash = createPasswordHash(passwords.get(i), salt);
            st.setBytes(3, hash);
            st.setBytes(4, salt);
            st.executeUpdate();
         }
      } catch (NoSuchAlgorithmException e) {
         throw new SQLException("User creation failed", e);
      }
   }

   @Override
   public void createUser(User<Employee> user, char[] password) throws SQLException {
      createUsers(Collections.singletonList(user), Collections.singletonList(password));
   }

   @Override
   public Optional<User<Employee>> login(String username, char[] password) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
         st.setString(1, username);

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
               byte[] hash = rs.getBytes("password");
               byte[] salt = rs.getBytes("salt");

               if (Arrays.equals(hash, createPasswordHash(password, salt))) {
                  Person p = new Person();
                  p.setId(rs.getInt("id"));

                  Employee em = new Employee();
                  em.setPerson(p);

                  User<Employee> user = new User<>();
                  user.setEntity(em);
                  // Set username as it was stored originally
                  user.setUsername(rs.getString("username"));
                  return Optional.of(user);
               } else {
                  return Optional.empty();
               }
            } else {
               return Optional.empty();
            }
         } catch (NoSuchAlgorithmException e) {
            throw new SQLException("User authentication failed", e);
         }
      }
   }

   private byte[] createPasswordHash(char[] password, byte[] salt) throws NoSuchAlgorithmException {
      Charset charset = Charset.forName("UTF-8");
      byte[] pwd = charset.encode(CharBuffer.wrap(password)).array();
      clear(password);

      MessageDigest digest = MessageDigest.getInstance(HASH_FUNCTION);
      byte[] hash = digest.digest(concat(pwd, salt));
      clear(pwd);

      return hash;
   }

   private byte[] createSalt() {
      byte[] salt = new byte[512];
      new Random().nextBytes(salt);
      return salt;
   }

   private byte[] concat(byte[] a, byte[] b) {
      byte[] c = new byte[a.length + b.length];
      System.arraycopy(a, 0, c, 0, a.length);
      System.arraycopy(b, 0, c, a.length, b.length);
      return c;
   }

   private void clear(byte[] b) {
      Arrays.fill(b, (byte) 0);
   }

   private void clear(char[] c) {
      Arrays.fill(c, (char) 0);
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO \"user\""
              + " (id, username, password, salt)"
              + " VALUES (?, ?, ?, ?)";

      static final String SELECT_ONE
              = "SELECT id, username, password, salt"
              + " FROM \"user\""
              + " WHERE username = ?";
   }
}
