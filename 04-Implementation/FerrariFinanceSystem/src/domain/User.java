package domain;

public class User<T> {
   private String username;
   private T entity;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public T getEntity() {
      return entity;
   }

   public void setEntity(T entity) {
      this.entity = entity;
   }
}
