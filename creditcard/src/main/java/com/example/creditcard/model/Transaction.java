package com.example.creditcard.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * Transaction Entity
 * @author Diego Umana
 *
 */
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String transactionNumber;
	@NotBlank
	private String localDateTime;
	@NotNull
	private Long transactionTimeStamp;
	@NotNull
	private BigDecimal amount;
	@NotBlank
	private String currency;
	@NotNull
	@ManyToOne
	private CreditCard creditCard;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the transactionNumber
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}
	/**
	 * @param transactionNumber the transactionNumber to set
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	/**
	 * @return the localDateTime
	 */
	public String getLocalDateTime() {
		return localDateTime;
	}
	/**
	 * @param localDateTime the localDateTime to set
	 */
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	/**
	 * @return the transactionTimeStamp
	 */
	public Long getTransactionTimeStamp() {
		return transactionTimeStamp;
	}
	/**
	 * @param transactionTimeStamp the transactionTimeStamp to set
	 */
	public void setTransactionTimeStamp(Long transactionTimeStamp) {
		this.transactionTimeStamp = transactionTimeStamp;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

}
