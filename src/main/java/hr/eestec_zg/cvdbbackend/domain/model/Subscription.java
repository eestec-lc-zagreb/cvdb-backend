package hr.eestec_zg.cvdbbackend.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "subscription")
public class Subscription {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.TABLE)
    private SubscriptionId id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(name = "subscription_start", nullable = false)
    private ZonedDateTime subscriptionStart;

    @Column(name = "subscription_end", nullable = false)
    private ZonedDateTime subscriptionEnd;

    public Subscription() {
        this.id = new SubscriptionId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription user(User user) {
        this.id.userId = user.getId();
        this.user = user;

        return this;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Subscription event(Event event) {
        this.id.eventId = event.getId();
        this.event = event;

        return this;
    }

    public ZonedDateTime getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(ZonedDateTime subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }

    public Subscription subscriptionStart(ZonedDateTime subscriptionStart) {
        this.subscriptionStart = subscriptionStart;

        return this;
    }

    public ZonedDateTime getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(ZonedDateTime subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public Subscription subscriptionEnd(ZonedDateTime subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;

        return this;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", subscriptionStart=" + subscriptionStart +
                ", subscriptionEnd=" + subscriptionEnd +
                '}';
    }

    @Embeddable
    public static class SubscriptionId implements Serializable {

        @Column(name = "user_id")
        Integer userId;
        @Column(name = "event_id")
        Integer eventId;

        public SubscriptionId() {

        }

        public SubscriptionId(Integer userId, Integer eventId) {
            this.userId = userId;
            this.eventId = eventId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubscriptionId that = (SubscriptionId) o;

            if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
            return eventId != null ? eventId.equals(that.eventId) : that.eventId == null;
        }

        @Override
        public int hashCode() {
            int result = userId != null ? userId.hashCode() : 0;
            result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ParticipationId{" +
                    "userId=" + userId +
                    ", eventId=" + eventId +
                    '}';
        }
    }
}
