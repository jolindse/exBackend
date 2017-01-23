package to.mattias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "customers")
public class Customer implements Notable {
    @Id
    @GeneratedValue
    private Long customerId;
    private String customerName;

    public Customer() {
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
