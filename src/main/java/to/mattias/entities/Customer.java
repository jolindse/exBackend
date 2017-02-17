package to.mattias.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "customers")
public class Customer extends Notable {

    private String customerName;

    public Customer() {
        super();
    }

    public Customer(String customerName) {
        super();
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerName.equals(customer.customerName);
    }

    @Override
    public int hashCode() {
        return customerName.hashCode();
    }
}
