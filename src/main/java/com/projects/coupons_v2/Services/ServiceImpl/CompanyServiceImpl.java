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
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Primary
@RequiredArgsConstructor
@Data
public class CompanyServiceImpl implements CompanyService {

    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;


    @Override
    public Boolean login(String email, String password) throws CompanyException {
        if (companyRepo.existsByEmailAndPassword(email, password)) {
            Company company = companyRepo.findByEmailAndPassword(email, password);
            return true;
        } else {
            throw new CompanyException(CompanyMsg.COMPANY_LOGIN_FAILED);
        }
    }

    @Override
    public Coupon addCoupon(Coupon coupon, Integer companyId) throws CouponException {
        if (couponRepo.existsByCompanyIDAndTitle(coupon.getCompanyID(), coupon.getTitle())) {
            throw new CouponException(CouponMsg.COUPON_TITLE_ALREADY_EXISTS);
        }
        coupon.setCompanyID(companyId);
        return couponRepo.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon, Integer companyId) throws CouponException {
        System.out.println(coupon);
        Coupon check = couponRepo.findById(coupon.getId()).orElseThrow(() -> new CouponException(CouponMsg.COUPON_DOES_NOT_EXIST));
        //check that coupon has the correct company id
        if (Objects.equals(check.getCompanyID(),  companyId)) {
            couponRepo.saveAndFlush(coupon);
        } else throw new CouponException(CouponMsg.ILLEGAL_COUPON_UPDATE);

    }

    @Override
    public void deleteCoupon(int couponID, Integer companyId) throws CompanyException {
        if (couponRepo.findById(couponID).isEmpty()) {
         throw new CompanyException(CompanyMsg.COUPON_DOES_NOT_EXIST);
        }
        Coupon coupon = couponRepo.findById(couponID).get();
        if (!coupon.getCompanyID().equals(companyId)){
            throw new CompanyException((CompanyMsg.ILLEGAL_COUPON_DELETE));
        }
        couponRepo.delete(coupon);
    }


    @Override
    public List<Coupon> getCompanyCoupons(Integer companyId) throws CompanyException {
        if (companyRepo.findById(companyId).isPresent()) {
//            return companyRepo.findById(companyID).get().getCoupons();
            return couponRepo.findByCompanyID(companyId);
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category, Integer companyId) throws CompanyException {
        if (companyRepo.findById(companyId).isPresent()) {
            return couponRepo.findByCompanyIDAndCategory(companyId, category);
//            return companyRepo.findById(companyID).get().getCoupons();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice, Integer companyId) throws CompanyException {
        if (companyRepo.findById(companyId).isPresent()) {
            return couponRepo.findByCompanyIDAndPriceLessThan(companyId, maxPrice);
//            return companyRepo.findById(companyID).get().getCoupons();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }

    @Override
    public Company getCompanyDetails(Integer companyId) throws CompanyException {
        if (companyRepo.findById(companyId).isPresent()) {
            return companyRepo.findById(companyId).get();
        }
        throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }
    @Override
    public Company getByDetails(String email, String name){
        return companyRepo.findByEmailAndName(email, name);
    }


}
