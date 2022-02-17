package clients.facade;

import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dao.CustomerFacadeDao;
import clients.dbDao.CouponsDBDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerFacade extends ClientFacade implements CustomerFacadeDao {
    private int customerID;

    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("customer") && password.equals("customer");
    }

    @Override
    public void purchaseCoupon(Coupon coupon){
        if(LocalDate.now().isBefore(coupon.getEndDate().toLocalDate())){
            couponsDBDAO.deleteCoupon(coupon.getId());
        }

    }

    @Override
    public List<Coupon> getCustomerCoupons(){
        return CouponsDBDAO.getCouponsByCustomerId(customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category){
        return getCustomerCoupons().stream()
                .filter(item->item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return getCustomerCoupons().stream()
                .filter(item->item.getPrice()<=maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerDetails(){
      return customersDBDAO.getOneCustomer(customerID);
    }



}
