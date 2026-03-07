package net.brightroom.example.eventlistener;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.brightroom.featureflag.core.event.FeatureFlagChangedEvent;
import net.brightroom.featureflag.core.event.FeatureFlagRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventListener {

  private static final Logger log = LoggerFactory.getLogger(AuditEventListener.class);

  private final List<String> auditLog = new CopyOnWriteArrayList<>();

  @EventListener
  public void onChanged(FeatureFlagChangedEvent event) {
    String entry =
        String.format(
            "[%s] CHANGED: feature=%s, enabled=%s, rollout=%s",
            Instant.now(), event.featureName(), event.enabled(), event.rolloutPercentage());
    auditLog.add(entry);
    log.info(entry);
  }

  @EventListener
  public void onRemoved(FeatureFlagRemovedEvent event) {
    String entry = String.format("[%s] REMOVED: feature=%s", Instant.now(), event.featureName());
    auditLog.add(entry);
    log.info(entry);
  }

  public List<String> getAuditLog() {
    return List.copyOf(auditLog);
  }
}
