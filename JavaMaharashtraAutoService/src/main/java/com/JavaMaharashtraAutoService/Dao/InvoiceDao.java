package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Invoice;
import com.JavaMaharashtraAutoService.Model.InvoiceProductDetails;
import com.JavaMaharashtraAutoService.Util.ConverterUtil;
import com.JavaMaharashtraAutoService.Util.DBConnection;

public class InvoiceDao {

	Connection con;

	public InvoiceDao() {
		con = DBConnection.GetConnection();
	}

	public String GetInvoiceData() {

		String invNo = null;
		try {
			String sql = "SELECT max(invNo) as invNo FROM masdb.invoice where delInd='N'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				invNo = rs.getString("invNo");
			}
			if (invNo == null) {
				return "1";

			} else {

				invNo = Integer.toString(Integer.parseInt(invNo) + 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invNo;
	}

	public String AddInvoice(Invoice inv) {
		String output = "";
		try {
			Date date = new Date();
			String sqlInvoice = "INSERT INTO masdb.invoice(invDate,invType,serDate,\r\n"
					+ "custName,custAddress,custMob,\r\n" + "custVehNo,custVehType,custVehModel,\r\n"
					+ "remark,nextSerDate,totalBillAmount,\r\n"
					+ "disAmount,grossTotal,delInd,updateUser,updateDate)\r\n"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pre = con.prepareStatement(sqlInvoice);

			pre.setDate(1, ConverterUtil.convertFromJAVADateToSQLDate(inv.getInvDate()));
			pre.setString(2, inv.getInvType());
			pre.setDate(3, ConverterUtil.convertFromJAVADateToSQLDate(inv.getSerDate()));
			pre.setString(4, inv.getCustName());
			pre.setString(5, inv.getCustAddress());
			pre.setString(6, inv.getCustMob());
			pre.setString(7, inv.getCustVehNo());
			pre.setString(8, inv.getCustVehType());
			pre.setString(9, inv.getCustVehModel());
			pre.setString(10, inv.getRemark());
			pre.setDate(11, ConverterUtil.convertFromJAVADateToSQLDate(inv.getNextSerDate()));
			pre.setString(12, inv.getTotalBillAmount());
			pre.setString(13, inv.getDisAmount());
			pre.setString(14, inv.getGrossTotal());
			pre.setString(15, "N");
			pre.setString(16, inv.getUpdateUser());
			pre.setDate(17, ConverterUtil.convertFromJAVADateToSQLDate(date));
			pre.executeUpdate();

			// @SuppressWarnings("unused")
			String sqlProduct = "INSERT INTO masdb.invoiceproductdetails\r\n"
					+ "(sno,proId,proName,remark,manDate,qty,rate,dis,total,cgst,sgst,grossTotal,invNo)\r\n"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pro = con.prepareStatement(sqlProduct);

			for (InvoiceProductDetails item : inv.getListProducts()) {
				pro.setString(1, item.getSno());
				pro.setString(2, item.getProId());
				pro.setString(3, item.getProName());
				pro.setString(4, item.getRemark());
				pro.setString(5, item.getManDate());
				pro.setString(6, item.getQty());
				pro.setString(7, item.getRate());
				pro.setString(8, item.getDis());
				pro.setString(9, item.getTotal());
				pro.setString(10, item.getCgst());
				pro.setString(11, item.getSgst());
				pro.setString(12, item.getGrossTotal());
				pro.setString(13, item.getInvNo());
				pro.addBatch();
			}
			pro.executeBatch();
			output = "Data has been inserted;";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return output;
	}

	public List<Invoice> GetInvoice(String startDate, String endDate) {
		List<Invoice> invoicelist = new ArrayList<Invoice>();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT invNo,invDate,custName,custMob,custVehNo,custVehModel,totalBillAmount FROM invoice where delInd='N' and invDate between '"
					+ startDate + "' and '" + endDate + "' order by invDate DESC;";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Invoice invoice = new Invoice();
				invoice.setInvNo(rs.getString("invNo"));
				invoice.setInvDate(rs.getDate("invDate"));
				invoice.setCustName(rs.getString("custName"));
				invoice.setCustName(rs.getString("custName"));
				invoice.setCustMob(rs.getString("custMob"));
				invoice.setCustVehNo(rs.getString("custVehNo"));
				invoice.setCustVehModel(rs.getString("custVehModel"));
				invoice.setTotalBillAmount(rs.getString("totalBillAmount"));
				invoicelist.add(invoice);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return invoicelist;

	}

	public List<InvoiceProductDetails> GetInvoiceProductDetails(String invNo) {
		List<InvoiceProductDetails> invoiceprolist = new ArrayList<InvoiceProductDetails>();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT invNo,proName,manDate,qty,rate,dis,total,cgst,sgst,grossTotal FROM invoiceproductdetails where invNo='"
					+ invNo + "';";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				InvoiceProductDetails invoiceprodetails = new InvoiceProductDetails();
				invoiceprodetails.setInvNo(rs.getString("invNo"));
				invoiceprodetails.setProName(rs.getString("proName"));
				invoiceprodetails.setManDate(rs.getString("manDate"));
				invoiceprodetails.setQty(rs.getString("qty"));
				invoiceprodetails.setRate(rs.getString("rate"));
				invoiceprodetails.setDis(rs.getString("dis"));
				invoiceprodetails.setTotal(rs.getString("total"));
				invoiceprodetails.setCgst(rs.getString("cgst"));
				invoiceprodetails.setSgst(rs.getString("sgst"));
				invoiceprodetails.setGrossTotal(rs.getString("grossTotal"));

				invoiceprolist.add(invoiceprodetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceprolist;
	}

	public Invoice GetInvoiceByInvNo(String invNo) {
		Invoice invoice = new Invoice();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT invNo,invDate,invType,serDate,custName,custMob,custAddress,custVehNo,custVehModel,custVehType,totalBillAmount FROM invoice where delInd='N' and invNo= '"
					+ invNo + "';";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				invoice.setInvNo(rs.getString("invNo"));
				invoice.setInvType(rs.getString("invType"));
				invoice.setInvDate(rs.getDate("invDate"));
				invoice.setSerDate(rs.getDate("serDate"));
				invoice.setCustName(rs.getString("custName"));
				invoice.setCustMob(rs.getString("custMob"));
				invoice.setCustVehNo(rs.getString("custVehNo"));
				invoice.setCustAddress(rs.getString("custAddress"));
				invoice.setCustVehType(rs.getString("custVehType"));
				invoice.setCustVehModel(rs.getString("custVehModel"));
				invoice.setTotalBillAmount(rs.getString("totalBillAmount"));

			}
			invoice.setListProducts(GetInvoiceProductDetails(invNo));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return invoice;

	}

}
