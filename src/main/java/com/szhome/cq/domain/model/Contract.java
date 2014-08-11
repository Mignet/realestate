package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "BDC_CONTRACT_INFO")
public class Contract extends BaseDomain<Contract> {
	private String con_no;
	@Id
	private String id;
	private String con_type;
	private Date con_date;
	private String b_deleteflag;
	private String con_area;
	private String pre_sale_no;
	private float con_areas;
	private String con_unit_price;
	private String con_pric;
	private String con_price_cn;
	private String shift_mode;
	private String house_buy_pro;
	private Date con_sign_date;
	private String is_mortgage;
	private String is_nota;
	private String currency;
	private String exchange_rate;
	private String down_payment;
	private String payment_method;
	private String mortgage_years;
	private Date occupation_date;
	private String is_new_contract;
	private String is_sup_contract;
	private String is_cer;
	private String home_buyers_qua;
	private String home_buyers_cer_no;
	private Date home_buyers_cer_date;
	private String home_buyers_number;
	private String within_export;
	private String cancellation_managers;
	private Date cancellation_date;
	private String cancellation_reason;
	private String house_id;

	public Contract(){
		super();
		this.t = Contract.class;
	}

	public Contract(String id){
		super();
		this.id = id;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCon_type(String con_type) {
		this.con_type = con_type;
	}

	public void setCon_date(Date con_date) {
		this.con_date = con_date;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setCon_area(String con_area) {
		this.con_area = con_area;
	}

	public void setPre_sale_no(String pre_sale_no) {
		this.pre_sale_no = pre_sale_no;
	}

	public void setCon_areas(float con_areas) {
		this.con_areas = con_areas;
	}

	public void setCon_unit_price(String con_unit_price) {
		this.con_unit_price = con_unit_price;
	}

	public void setCon_pric(String con_pric) {
		this.con_pric = con_pric;
	}

	public void setCon_price_cn(String con_price_cn) {
		this.con_price_cn = con_price_cn;
	}

	public void setShift_mode(String shift_mode) {
		this.shift_mode = shift_mode;
	}

	public void setHouse_buy_pro(String house_buy_pro) {
		this.house_buy_pro = house_buy_pro;
	}

	public void setCon_sign_date(Date con_sign_date) {
		this.con_sign_date = con_sign_date;
	}

	public void setIs_mortgage(String is_mortgage) {
		this.is_mortgage = is_mortgage;
	}

	public void setIs_nota(String is_nota) {
		this.is_nota = is_nota;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setExchange_rate(String exchange_rate) {
		this.exchange_rate = exchange_rate;
	}

	public void setDown_payment(String down_payment) {
		this.down_payment = down_payment;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public void setMortgage_years(String mortgage_years) {
		this.mortgage_years = mortgage_years;
	}

	public void setOccupation_date(Date occupation_date) {
		this.occupation_date = occupation_date;
	}

	public void setIs_new_contract(String is_new_contract) {
		this.is_new_contract = is_new_contract;
	}

	public void setIs_sup_contract(String is_sup_contract) {
		this.is_sup_contract = is_sup_contract;
	}

	public void setIs_cer(String is_cer) {
		this.is_cer = is_cer;
	}

	public void setHome_buyers_qua(String home_buyers_qua) {
		this.home_buyers_qua = home_buyers_qua;
	}

	public void setHome_buyers_cer_no(String home_buyers_cer_no) {
		this.home_buyers_cer_no = home_buyers_cer_no;
	}

	public void setHome_buyers_cer_date(Date home_buyers_cer_date) {
		this.home_buyers_cer_date = home_buyers_cer_date;
	}

	public void setHome_buyers_number(String home_buyers_number) {
		this.home_buyers_number = home_buyers_number;
	}

	public void setWithin_export(String within_export) {
		this.within_export = within_export;
	}

	public void setCancellation_managers(String cancellation_managers) {
		this.cancellation_managers = cancellation_managers;
	}

	public void setCancellation_date(Date cancellation_date) {
		this.cancellation_date = cancellation_date;
	}

	public void setCancellation_reason(String cancellation_reason) {
		this.cancellation_reason = cancellation_reason;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public String getCon_no() {
		return con_no;
	}

	public String getId() {
		return id;
	}

	public String getCon_type() {
		return con_type;
	}

	public Date getCon_date() {
		return con_date;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getCon_area() {
		return con_area;
	}

	public String getPre_sale_no() {
		return pre_sale_no;
	}

	public float getCon_areas() {
		return con_areas;
	}

	public String getCon_unit_price() {
		return con_unit_price;
	}

	public String getCon_pric() {
		return con_pric;
	}

	public String getCon_price_cn() {
		return con_price_cn;
	}

	public String getShift_mode() {
		return shift_mode;
	}

	public String getHouse_buy_pro() {
		return house_buy_pro;
	}

	public Date getCon_sign_date() {
		return con_sign_date;
	}

	public String getIs_mortgage() {
		return is_mortgage;
	}

	public String getIs_nota() {
		return is_nota;
	}

	public String getCurrency() {
		return currency;
	}

	public String getExchange_rate() {
		return exchange_rate;
	}

	public String getDown_payment() {
		return down_payment;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public String getMortgage_years() {
		return mortgage_years;
	}

	public Date getOccupation_date() {
		return occupation_date;
	}

	public String getIs_new_contract() {
		return is_new_contract;
	}

	public String getIs_sup_contract() {
		return is_sup_contract;
	}

	public String getIs_cer() {
		return is_cer;
	}

	public String getHome_buyers_qua() {
		return home_buyers_qua;
	}

	public String getHome_buyers_cer_no() {
		return home_buyers_cer_no;
	}

	public Date getHome_buyers_cer_date() {
		return home_buyers_cer_date;
	}

	public String getHome_buyers_number() {
		return home_buyers_number;
	}

	public String getWithin_export() {
		return within_export;
	}

	public String getCancellation_managers() {
		return cancellation_managers;
	}

	public Date getCancellation_date() {
		return cancellation_date;
	}

	public String getCancellation_reason() {
		return cancellation_reason;
	}

	public String getHouse_id() {
		return house_id;
	}

}

