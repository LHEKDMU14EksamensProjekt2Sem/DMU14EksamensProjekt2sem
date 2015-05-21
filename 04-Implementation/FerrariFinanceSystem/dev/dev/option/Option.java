package dev.option;

public enum Option implements Value<Boolean> {
   DESTROY(false),
   SAMPLE(false);

   private Boolean value;

   Option(Boolean value) {
      this.value = value;
   }

   public Boolean get() {
      return value;
   }

   public void set(Boolean value) {
      this.value = value;
   }
}
