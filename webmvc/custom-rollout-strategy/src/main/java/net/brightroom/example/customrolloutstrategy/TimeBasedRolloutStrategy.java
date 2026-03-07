package net.brightroom.example.customrolloutstrategy;

import java.time.LocalTime;
import net.brightroom.featureflag.core.context.FeatureFlagContext;
import net.brightroom.featureflag.core.rollout.DefaultRolloutStrategy;
import net.brightroom.featureflag.core.rollout.RolloutStrategy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("time-based")
public class TimeBasedRolloutStrategy implements RolloutStrategy {

  @Override
  public boolean isInRollout(String featureName, FeatureFlagContext context, int percentage) {
    int hour = LocalTime.now().getHour();
    if (hour < 9 || hour >= 17) {
      return false;
    }
    return new DefaultRolloutStrategy().isInRollout(featureName, context, percentage);
  }
}
