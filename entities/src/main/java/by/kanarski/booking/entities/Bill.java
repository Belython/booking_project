package by.kanarski.booking.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long billId;
    private Long bookingDate;
    private User user;
    private Integer totalPersons;
    private Long checkInDate;
    private Long checkOutDate;
    private Set<Room> roomSet;
    private Double paymentAmount;
    private State paymentStatus;
    private State billStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    @Column(
            unique = true,
            nullable = false
    )
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @Column(
            nullable = false
    )
    public Long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Long bookingDate) {
        this.bookingDate = bookingDate;
    }

    @ManyToOne
    @JoinColumn(
            name = "USER_ID"
    )
    public User getUser() {
        return user;
    }

    public void setUser(User client) {
        this.user = client;
    }

    @Column(
            nullable = false
    )
    public Integer getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(Integer totalPersons) {
        this.totalPersons = totalPersons;
    }

    @Column(
            nullable = false
    )
    public Long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Long checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Column(
            nullable = false
    )
    public Long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @ManyToMany
    @JoinTable(
            name = "BILL_ROOMS",
            joinColumns = @JoinColumn(name = "BILL_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID")
    )
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> bookedRoomSet) {
        this.roomSet = bookedRoomSet;
    }

    @Column(
            nullable = false
    )
    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @ManyToOne
    @JoinColumn(
            name = "PAYMENT_STATUS_ID",
            nullable = false
    )
    public State getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(State paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false
    )
    public State getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(State billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (!billId.equals(bill.billId)) return false;
        if (!bookingDate.equals(bill.bookingDate)) return false;
        if (!user.equals(bill.user)) return false;
        if (!totalPersons.equals(bill.totalPersons)) return false;
        if (!checkInDate.equals(bill.checkInDate)) return false;
        if (!checkOutDate.equals(bill.checkOutDate)) return false;
        if (!roomSet.equals(bill.roomSet)) return false;
        if (!paymentAmount.equals(bill.paymentAmount)) return false;
        return billStatus.equals(bill.billStatus);

    }

    @Override
    public int hashCode() {
        int result = billId.hashCode();
        result = 31 * result + bookingDate.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + totalPersons.hashCode();
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        result = 31 * result + roomSet.hashCode();
        result = 31 * result + paymentAmount.hashCode();
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}

