package net.brightroom.example;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("feature_management")
class FeatureManagement {

  @Id String featureName;
  Boolean enabled;

  FeatureManagement(String featureName, Boolean enabled) {
    this.featureName = featureName;
    this.enabled = enabled;
  }

  Boolean enabled() {
    return enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FeatureManagement that = (FeatureManagement) o;
    return Objects.equals(featureName, that.featureName) && Objects.equals(enabled, that.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureName, enabled);
  }

  @Override
  public String toString() {
    return "FeatureManagement{"
        + "featureName='"
        + featureName
        + '\''
        + ", enabled="
        + enabled
        + '}';
  }

  FeatureManagement() {}
}
