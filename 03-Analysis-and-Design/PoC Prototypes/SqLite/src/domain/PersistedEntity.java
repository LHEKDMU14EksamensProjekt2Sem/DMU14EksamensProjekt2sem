package domain;

public abstract class PersistedEntity<K> {
  private K key;
  
  private boolean changed = false;

  public K getKey() {
     return key;
  }

  public void setKey(K key) {
     this.key = key;
  }
  
  public boolean isPersisted() {
     return (key != null);
  }
  
  public boolean hasChanged() {
     return changed;
  }
  
  public void setChanged(boolean b) {
     changed = b;
  }
}