package net.brightroom.example.actuator;

import net.brightroom.featureflag.core.event.FeatureFlagChangedEvent;
import net.brightroom.featureflag.core.event.FeatureFlagRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FeatureFlagEventListener {

  private static final Logger log = LoggerFactory.getLogger(FeatureFlagEventListener.class);

  @EventListener
  public void onFeatureFlagChanged(FeatureFlagChangedEvent event) {
    if (event.rolloutPercentage() != null) {
      log.info(
          "[FeatureFlagChangedEvent] featureName={}, enabled={}, rollout={}",
          event.featureName(),
          event.enabled(),
          event.rolloutPercentage());
    } else {
      log.info(
          "[FeatureFlagChangedEvent] featureName={}, enabled={}",
          event.featureName(),
          event.enabled());
    }
  }

  @EventListener
  public void onFeatureFlagRemoved(FeatureFlagRemovedEvent event) {
    log.info("[FeatureFlagRemovedEvent] featureName={}", event.featureName());
  }
}
