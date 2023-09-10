/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.model;

/**
 *
 * @author Kushan Dileep
 */
public class OrderDetailModel {
    private String OrderID;
    private String ItemCode;
    private Integer OrderQTY;
    private Double Discount;

    public OrderDetailModel() {
    }

    public OrderDetailModel(String OrderID, String ItemCode, Integer OrderQTY, Double Discount) {
        this.OrderID = OrderID;
        this.ItemCode = ItemCode;
        this.OrderQTY = OrderQTY;
        this.Discount = Discount;
    }

    /**
     * @return the OrderID
     */
    public String getOrderID() {
        return OrderID;
    }

    /**
     * @param OrderID the OrderID to set
     */
    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    /**
     * @return the ItemCode
     */
    public String getItemCode() {
        return ItemCode;
    }

    /**
     * @param ItemCode the ItemCode to set
     */
    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    /**
     * @return the OrderQTY
     */
    public Integer getOrderQTY() {
        return OrderQTY;
    }

    /**
     * @param OrderQTY the OrderQTY to set
     */
    public void setOrderQTY(Integer OrderQTY) {
        this.OrderQTY = OrderQTY;
    }

    /**
     * @return the Discount
     */
    public Double getDiscount() {
        return Discount;
    }

    /**
     * @param Discount the Discount to set
     */
    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    @Override
    public String toString() {
        return "OrderDetailModel{" + "OrderID=" + OrderID + ", ItemCode=" + ItemCode + ", OrderQTY=" + OrderQTY + ", Discount=" + Discount + '}';
    }
    
    
    
}
