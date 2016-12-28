package by.kanarski.booking.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */
@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class State implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long stateId;
    private String stateName;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    @Column(
            unique = true,
            nullable = false
    )
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Column(
            unique = true,
            nullable = false
    )
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!stateId.equals(state.stateId)) return false;
        return stateName.equals(state.stateName);

    }

    @Override
    public int hashCode() {
        int result = stateId.hashCode();
        result = 31 * result + stateName.hashCode();
        return result;
    }

}
