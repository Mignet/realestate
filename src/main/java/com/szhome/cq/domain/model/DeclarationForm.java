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
@Table(name = "BDC_TAX_REPORT_INFO")
public class DeclarationForm extends BaseDomain<DeclarationForm> {
	private String unit_no;
	private String building_no;
	private String use;
	private String house_id;
	private String transefer_type;
	private double build_area;
	private double inside_space;
	private String rjlsfdy1;
	private double average_price;
	private String general_house;
	private Date con_date;
	private Date transefer_accdate;
	private double transaction_price;
	private double orig_reg_price;
	private double reference_price;
	private double relief_file_no;
	private String intax_charge_type;
	private double intax_che_fileno;
	private String land_incarment_tax_charge_type;
	private double tzshswsh;
	private double jsjgfhtzswh;
	private double fhjg;
	private String jsjgly;
	private String orig_get_mode;
	private double applicable_taxrate;
	private String tax_items;
	private String charge_kind;
	private double caltax_price;
	private double deduct_money;
	private double payable_tax;
	private double relief_tax;
	private double paidin_tax;
	private double declare_proof_no;
	private String declare_no;
	private String bus_id;
	private String payer_type;
	private String cer_type;
	private String cer_no;
	private String transferee_name;
	private String transferee_address;
	private String transferee_phonenum;
	private String unique_house;
	private double transferee_share;
	private String transferor_name;
	private String transferor_address;
	private String transferor_phonenum;
	private String orig_cer_no;
	private Date orig_buy_date;
	private String con_no;
	private double transferor_share;
	private String sfgm5nys;
	private String parcel_no;
	private String location;
	private String land_location;
	@Id
	private String dec_id;
	private String b_deleteflag;

	public DeclarationForm(){
		super();
		this.t = DeclarationForm.class;
	}

	public DeclarationForm(String dec_id){
		super();
		this.dec_id = dec_id;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public void setTransefer_type(String transefer_type) {
		this.transefer_type = transefer_type;
	}

	public void setBuild_area(double build_area) {
		this.build_area = build_area;
	}

	public void setInside_space(double inside_space) {
		this.inside_space = inside_space;
	}

	public void setRjlsfdy1(String rjlsfdy1) {
		this.rjlsfdy1 = rjlsfdy1;
	}

	public void setAverage_price(double average_price) {
		this.average_price = average_price;
	}

	public void setGeneral_house(String general_house) {
		this.general_house = general_house;
	}

	public void setCon_date(Date con_date) {
		this.con_date = con_date;
	}

	public void setTransefer_accdate(Date transefer_accdate) {
		this.transefer_accdate = transefer_accdate;
	}

	public void setTransaction_price(double transaction_price) {
		this.transaction_price = transaction_price;
	}

	public void setOrig_reg_price(double orig_reg_price) {
		this.orig_reg_price = orig_reg_price;
	}

	public void setReference_price(double reference_price) {
		this.reference_price = reference_price;
	}

	public void setRelief_file_no(double relief_file_no) {
		this.relief_file_no = relief_file_no;
	}

	public void setIntax_charge_type(String intax_charge_type) {
		this.intax_charge_type = intax_charge_type;
	}

	public void setIntax_che_fileno(double intax_che_fileno) {
		this.intax_che_fileno = intax_che_fileno;
	}

	public void setLand_incarment_tax_charge_type(String land_incarment_tax_charge_type) {
		this.land_incarment_tax_charge_type = land_incarment_tax_charge_type;
	}

	public void setTzshswsh(double tzshswsh) {
		this.tzshswsh = tzshswsh;
	}

	public void setJsjgfhtzswh(double jsjgfhtzswh) {
		this.jsjgfhtzswh = jsjgfhtzswh;
	}

	public void setFhjg(double fhjg) {
		this.fhjg = fhjg;
	}

	public void setJsjgly(String jsjgly) {
		this.jsjgly = jsjgly;
	}

	public void setOrig_get_mode(String orig_get_mode) {
		this.orig_get_mode = orig_get_mode;
	}

	public void setApplicable_taxrate(double applicable_taxrate) {
		this.applicable_taxrate = applicable_taxrate;
	}

	public void setTax_items(String tax_items) {
		this.tax_items = tax_items;
	}

	public void setCharge_kind(String charge_kind) {
		this.charge_kind = charge_kind;
	}

	public void setCaltax_price(double caltax_price) {
		this.caltax_price = caltax_price;
	}

	public void setDeduct_money(double deduct_money) {
		this.deduct_money = deduct_money;
	}

	public void setPayable_tax(double payable_tax) {
		this.payable_tax = payable_tax;
	}

	public void setRelief_tax(double relief_tax) {
		this.relief_tax = relief_tax;
	}

	public void setPaidin_tax(double paidin_tax) {
		this.paidin_tax = paidin_tax;
	}

	public void setDeclare_proof_no(double declare_proof_no) {
		this.declare_proof_no = declare_proof_no;
	}

	public void setDeclare_no(String declare_no) {
		this.declare_no = declare_no;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setPayer_type(String payer_type) {
		this.payer_type = payer_type;
	}

	public void setCer_type(String cer_type) {
		this.cer_type = cer_type;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setTransferee_name(String transferee_name) {
		this.transferee_name = transferee_name;
	}

	public void setTransferee_address(String transferee_address) {
		this.transferee_address = transferee_address;
	}

	public void setTransferee_phonenum(String transferee_phonenum) {
		this.transferee_phonenum = transferee_phonenum;
	}

	public void setUnique_house(String unique_house) {
		this.unique_house = unique_house;
	}

	public void setTransferee_share(double transferee_share) {
		this.transferee_share = transferee_share;
	}

	public void setTransferor_name(String transferor_name) {
		this.transferor_name = transferor_name;
	}

	public void setTransferor_address(String transferor_address) {
		this.transferor_address = transferor_address;
	}

	public void setTransferor_phonenum(String transferor_phonenum) {
		this.transferor_phonenum = transferor_phonenum;
	}

	public void setOrig_cer_no(String orig_cer_no) {
		this.orig_cer_no = orig_cer_no;
	}

	public void setOrig_buy_date(Date orig_buy_date) {
		this.orig_buy_date = orig_buy_date;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public void setTransferor_share(double transferor_share) {
		this.transferor_share = transferor_share;
	}

	public void setSfgm5nys(String sfgm5nys) {
		this.sfgm5nys = sfgm5nys;
	}

	public void setParcel_no(String parcel_no) {
		this.parcel_no = parcel_no;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLand_location(String land_location) {
		this.land_location = land_location;
	}

	public void setDec_id(String dec_id) {
		this.dec_id = dec_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public String getBuilding_no() {
		return building_no;
	}

	public String getUse() {
		return use;
	}

	public String getHouse_id() {
		return house_id;
	}

	public String getTransefer_type() {
		return transefer_type;
	}

	public double getBuild_area() {
		return build_area;
	}

	public double getInside_space() {
		return inside_space;
	}

	public String getRjlsfdy1() {
		return rjlsfdy1;
	}

	public double getAverage_price() {
		return average_price;
	}

	public String getGeneral_house() {
		return general_house;
	}

	public Date getCon_date() {
		return con_date;
	}

	public Date getTransefer_accdate() {
		return transefer_accdate;
	}

	public double getTransaction_price() {
		return transaction_price;
	}

	public double getOrig_reg_price() {
		return orig_reg_price;
	}

	public double getReference_price() {
		return reference_price;
	}

	public double getRelief_file_no() {
		return relief_file_no;
	}

	public String getIntax_charge_type() {
		return intax_charge_type;
	}

	public double getIntax_che_fileno() {
		return intax_che_fileno;
	}

	public String getLand_incarment_tax_charge_type() {
		return land_incarment_tax_charge_type;
	}

	public double getTzshswsh() {
		return tzshswsh;
	}

	public double getJsjgfhtzswh() {
		return jsjgfhtzswh;
	}

	public double getFhjg() {
		return fhjg;
	}

	public String getJsjgly() {
		return jsjgly;
	}

	public String getOrig_get_mode() {
		return orig_get_mode;
	}

	public double getApplicable_taxrate() {
		return applicable_taxrate;
	}

	public String getTax_items() {
		return tax_items;
	}

	public String getCharge_kind() {
		return charge_kind;
	}

	public double getCaltax_price() {
		return caltax_price;
	}

	public double getDeduct_money() {
		return deduct_money;
	}

	public double getPayable_tax() {
		return payable_tax;
	}

	public double getRelief_tax() {
		return relief_tax;
	}

	public double getPaidin_tax() {
		return paidin_tax;
	}

	public double getDeclare_proof_no() {
		return declare_proof_no;
	}

	public String getDeclare_no() {
		return declare_no;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getPayer_type() {
		return payer_type;
	}

	public String getCer_type() {
		return cer_type;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getTransferee_name() {
		return transferee_name;
	}

	public String getTransferee_address() {
		return transferee_address;
	}

	public String getTransferee_phonenum() {
		return transferee_phonenum;
	}

	public String getUnique_house() {
		return unique_house;
	}

	public double getTransferee_share() {
		return transferee_share;
	}

	public String getTransferor_name() {
		return transferor_name;
	}

	public String getTransferor_address() {
		return transferor_address;
	}

	public String getTransferor_phonenum() {
		return transferor_phonenum;
	}

	public String getOrig_cer_no() {
		return orig_cer_no;
	}

	public Date getOrig_buy_date() {
		return orig_buy_date;
	}

	public String getCon_no() {
		return con_no;
	}

	public double getTransferor_share() {
		return transferor_share;
	}

	public String getSfgm5nys() {
		return sfgm5nys;
	}

	public String getParcel_no() {
		return parcel_no;
	}

	public String getLocation() {
		return location;
	}

	public String getLand_location() {
		return land_location;
	}

	public String getDec_id() {
		return dec_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}
