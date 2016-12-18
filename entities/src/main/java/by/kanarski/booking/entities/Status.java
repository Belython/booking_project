package by.kanarski.booking.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */
//
//@Entity
//@GenericGenerator(
//        name = "increment",
//        strategy = "increment"
//)
//@DynamicUpdate
//@DynamicInsert
public class Status implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long statusId;
    private String statusName;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Column
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String status) {
        this.statusName = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (!statusId.equals(status.statusId)) return false;
        return statusName.equals(status.statusName);

    }

    @Override
    public int hashCode() {
        int result = statusId.hashCode();
        result = 31 * result + statusName.hashCode();
        return result;
    }
}
