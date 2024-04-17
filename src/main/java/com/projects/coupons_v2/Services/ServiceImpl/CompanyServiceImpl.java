package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyMsg;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;
import com.projects.coupons_v2.Repositories.CompanyRepo;
import com.projects.coupons_v2.Repositories.CouponRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;
    private Integer companyID;


    @Override
    public Boolean login(String email, String password) throws CompanyException {
        if (companyRepo.existsByEmailAndPassword(email, password)) {
            Company company = companyRepo.findByEmailAndPassword(email, password);
            this.companyID = company.getId();
            return true;
        } else {
            throw new CompanyException(CompanyMsg.COMPANY_LOGIN_FAILED);
        }
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponException {
        if (couponRepo.existsByCompanyIDAndTitle(coupon.getCompanyID(), coupon.getTitle())) {
            throw new CouponException(CouponMsg.COUPON_TITLE_ALREADY_EXISTS);
        }
        couponRepo.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponException {
        Coupon check = couponRepo.findById(coupon.getId()).orElseThrow(() -> new CouponException(CouponMsg.COUPON_DOES_NOT_EXIST));
        if (Objects.equals(check.getCompanyID(), coupon.getCompanyID())) {
            couponRepo.saveAndFlush(coupon);
        } else throw new CouponException(CouponMsg.ILLEGAL_COUPON_UPDATE);
    }

    @Override
    public void deleteCoupon(int couponID) throws CompanyException {
        if (couponRepo.findById(couponID).isPresent()) {
            couponRepo.delete(couponRepo.findById(couponID).get());
        }
        else throw new CompanyException(CompanyMsg.COUPON_DOES_NOT_EXIST);
    }


    @Override
    public List<Coupon> getCompanyCoupons() throws CompanyException {
        if (companyRepo.findById(companyID).isPresent()) {
//            return companyRepo.findById(companyID).get().getCoupons();
            return couponRepo.findByCompanyID(companyID);
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) throws CompanyException {
        if (companyRepo.findById(companyID).isPresent()) {
            return couponRepo.findByCompanyIDAndCategory(companyID, category);
//            return companyRepo.findById(companyID).get().getCoupons();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) throws CompanyException {
        if (companyRepo.findById(companyID).isPresent()) {
            return couponRepo.findByCompanyIDAndPriceLessThan(companyID, maxPrice);
//            return companyRepo.findById(companyID).get().getCoupons();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public Company getCompanyDetails() throws CompanyException {
        if (companyRepo.findById(companyID).isPresent()) {
            return companyRepo.findById(companyID).get();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }


}
