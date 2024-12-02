package edu.ntnu.idi.idatt.model;

import java.util.HashMap;

/**
 * Class that extends HashMap. Overrides all functions used in the program so keys are handled
 * as lower case Strings at all times.
 *
 * @author @aardv44rk
 * @since November 30th 2024
 * @version 2.0
 */
public class LowerCaseMap<V> extends HashMap<String, V> {

  @Override
  public V put(String key, V value) {
    return super.put(key.toLowerCase(), value);
  }

  @Override
  public V putIfAbsent(String key, V value) {
    return super.putIfAbsent(key.toLowerCase(), value);
  }

  @Override
  public V get(Object key) {
    return super.get(key.toString().toLowerCase());
  }

  @Override
  public V getOrDefault(Object key, V defaultValue) {
    return super.getOrDefault(key.toString().toLowerCase(), defaultValue);
  }

  @Override
  public boolean containsKey(Object key) {
    return super.containsKey(key.toString().toLowerCase());
  }

  @Override
  public V remove(Object key) {
    return super.remove(key.toString().toLowerCase());
  }
}
