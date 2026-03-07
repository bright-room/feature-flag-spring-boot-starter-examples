package net.brightroom.example.eventlistener;

import net.brightroom.featureflag.core.event.FeatureFlagChangedEvent;
import net.brightroom.featureflag.core.event.FeatureFlagRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CacheInvalidationListener {

  private static final Logger log = LoggerFactory.getLogger(CacheInvalidationListener.class);

  private final FeatureFlagCache cache;

  public CacheInvalidationListener(FeatureFlagCache cache) {
    this.cache = cache;
  }

  @EventListener
  public void onChanged(FeatureFlagChangedEvent event) {
    log.info("Invalidating cache for feature: {}", event.featureName());
    cache.invalidate(event.featureName());
  }

  @EventListener
  public void onRemoved(FeatureFlagRemovedEvent event) {
    log.info("Invalidating cache for removed feature: {}", event.featureName());
    cache.invalidate(event.featureName());
  }
}
