package org.group5.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Neha on 7/10/2016.
 */
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity = 1;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    public OrderLine() {

    }

    public OrderLine(Product product, int quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getPrice() {
        if (this.product != null) {
            return this.product.getPrice() * this.getQuantity();
        }
        return 0.0;
    }

}
