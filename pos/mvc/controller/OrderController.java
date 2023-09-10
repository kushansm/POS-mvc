/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pos.mvc.db.DBConnection;
import pos.mvc.model.OrderDetailModel;
import pos.mvc.model.OrderModel;

/**
 *
 * @author Kushan Dileep
 */
public class OrderController {

    public String PlaceOder(OrderModel orderModel, ArrayList<OrderDetailModel> orderDetailModels) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String orderquery = "INSERT INTO Orders VALUES(?,?,?)";
            PreparedStatement StatementForOrder = connection.prepareCall(orderquery);
            StatementForOrder.setString(1, orderModel.getOrderID());
            StatementForOrder.setString(2, orderModel.getOrderDate());
            StatementForOrder.setString(3, orderModel.getCustID());

            if (StatementForOrder.executeUpdate() > 0) {
                boolean isOrderDetailSaved =true;
                String orderdetailquery = "INSERT INTO orderdetail VALUES(?,?,?,?)";
               for (OrderDetailModel orderDetailModel : orderDetailModels) {
                    PreparedStatement statementForOrderDetail = connection.prepareStatement(orderdetailquery );
                    statementForOrderDetail.setString(1, orderModel.getOrderID());
                    statementForOrderDetail.setString(2, orderDetailModel.getItemCode());
                    statementForOrderDetail.setInt(3, orderDetailModel.getOrderQTY());
                    statementForOrderDetail.setDouble(4, orderDetailModel.getDiscount());
                    
                    if(!(statementForOrderDetail.executeUpdate() > 0)){
                        isOrderDetailSaved = false;
                        
                        }
                        
                    } 
                    
                    if(isOrderDetailSaved){
                        
                        boolean isItemUpdate =true;
                        String itemquery = "UPDATE Item SET QtyOnHand = QtyOnHand - ? WHERE ItemCode = ?";
                        for (OrderDetailModel orderDetailModel : orderDetailModels) {
                            PreparedStatement statementForitem = connection.prepareCall(itemquery);
                            statementForitem.setInt(1, orderDetailModel.getOrderQTY());
                            statementForitem.setString(2, orderDetailModel.getItemCode());
                            if(!(statementForitem.executeUpdate()>=0)){
                                isItemUpdate= false;
                            }
                    }
                        if(isItemUpdate){
                            connection.commit();
                            return "Success";
                        }else{
                            connection.rollback();
                         return "order Detail Update Error";
                        }
                    }
                    else{
                         connection.rollback();
                         return "order Detail Save Error";
                    }
                    

            } else {
                connection.rollback();
                return "order Save Error";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return e.getMessage();
                
        } finally {
            connection.setAutoCommit(true);
        }
        
    }
    

}
