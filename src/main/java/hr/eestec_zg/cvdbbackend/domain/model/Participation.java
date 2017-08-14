package hr.eestec_zg.cvdbbackend.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "participation")
public class Participation {

    @EmbeddedId
    private ParticipationId id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(name = "cv")
    private String cv;

    public Participation() {
        this.id = new ParticipationId();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public Participation student(Student student) {
        this.id.studentId = student.getId();
        this.student = student;

        return this;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participation event(Event event) {
        this.id.eventId = event.getId();
        this.event = event;

        return this;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Participation cv(String cv) {
        this.cv = cv;

        return this;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "student=" + student +
                ", event=" + event +
                '}';
    }

    @Embeddable
    public static class ParticipationId implements Serializable {

        @Column(name = "student_id")
        Integer studentId;

        @Column(name = "event_id")
        Integer eventId;

        public ParticipationId() {

        }

        public ParticipationId(Integer studentId, Integer eventId) {
            this.studentId = studentId;
            this.eventId = eventId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParticipationId that = (ParticipationId) o;

            if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
            return eventId != null ? eventId.equals(that.eventId) : that.eventId == null;
        }

        @Override
        public int hashCode() {
            int result = studentId != null ? studentId.hashCode() : 0;
            result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ParticipationId{" +
                    "studentId=" + studentId +
                    ", eventId=" + eventId +
                    '}';
        }
    }
}
