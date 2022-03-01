package clients.facade;

import clients.CustomExceptions;
import clients.beans.Company;
import java.util.ArrayList;
import java.util.List;

public class AdminFacade extends ClientFacade {
    private final String email;
    private final String password;
    private boolean isLoginCorrect;

    public AdminFacade() {
        this.email = "admin@admin.com";
        this.password = "admin";
    }

    @Override
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void addCompany(Company company){
        try {
            this.companiesDBDAO.addCompany(company);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());;
        }
    }

    public void updateCompany (Company company) {
        this.companiesDBDAO.updateCompany(company);
    }

    public void deleteCompany (int companyID) {
        try {
            this.companiesDBDAO.deleteCompany(companyID);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Company> getAllCompanies(){
        try {
            return this.companiesDBDAO.getAllCompanies();
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Company getOneCompany (int companyID) {
        Company comp = null;
        try {
            comp = this.companiesDBDAO.getOneCompany(companyID);

        assert comp != null;
        comp.setCoupons(this.companiesDBDAO.getCompanyCoupons(companyID));
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());;
        }
        return comp;
    }

}
