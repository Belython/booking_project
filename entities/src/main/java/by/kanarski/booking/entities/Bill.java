package by.kanarski.booking.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class Bill implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long billId;
    private Long bookingDate;
    private User client;
    private Integer totalPersons;
    private Long checkInDate;
    private Long checkOutDate;
    private Set<Room> roomSet;
    private Double paymentAmount;
    private String billStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @Column
    public Long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Long bookingDate) {
        this.bookingDate = bookingDate;
    }

    @ManyToOne
    @JoinColumn(
            name = "USER_ID",
            foreignKey = @ForeignKey(name = "USER_BILLS")
    )
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Column
    public Integer getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(Integer totalPersons) {
        this.totalPersons = totalPersons;
    }

    @Column
    public Long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Long checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Column
    public Long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "BILL_ROOMS",
            joinColumns = {
                    @JoinColumn(
                            name = "BILL_ID",
                            foreignKey = @ForeignKey(name = "BILL_ROOMS")
                    )
            },
            inverseJoinColumns = @JoinColumn(
                    name = "ROOM_ID",
                    foreignKey = @ForeignKey(name = "ROOM_BILLS")
            )
    )
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> bookedRoomSet) {
        this.roomSet = bookedRoomSet;
    }

    @Column
    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column
    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (!billId.equals(bill.billId)) return false;
        if (!bookingDate.equals(bill.bookingDate)) return false;
        if (!client.equals(bill.client)) return false;
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
        result = 31 * result + client.hashCode();
        result = 31 * result + totalPersons.hashCode();
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        result = 31 * result + roomSet.hashCode();
        result = 31 * result + paymentAmount.hashCode();
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}

