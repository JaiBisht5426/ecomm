package com.ecommerce.ecomm.dto;

public class CheckoutRequest {

    private AddressDTO address;

    private String paymentMethod;

    public CheckoutRequest() {
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
