package me.wonwoo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wonwoolee on 2017. 7. 17..
 */
@ConfigurationProperties("hello")
public class HelloProperties {
  /**
   * you`re name
   */
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
