package com.worldpay.service.model.payment;

import com.worldpay.internal.model.CardAddress;
import com.worldpay.service.model.Address;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.worldpay.service.model.payment.PaymentType.CSEDATA;

public class Cse extends AbstractPayment {

    private String encryptedData;
    private Address address;

    public Cse(final String encryptedData, final Address address) {
        this.encryptedData = encryptedData;
        this.address = address;
        this.setPaymentType(CSEDATA);
    }

    @Override
    public void invokeSetter(final Method method, final Object targetObject) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        String methodName = method.getName();
        if (methodName.equals("setEncryptedData") && encryptedData != null) {
            method.invoke(targetObject, encryptedData);
        }
        if (methodName.equals("setCardAddress") && address != null) {
            CardAddress intCardAddress = new CardAddress();
            intCardAddress.setAddress((com.worldpay.internal.model.Address) address.transformToInternalModel());
            method.invoke(targetObject, intCardAddress);
        }
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(final String encrypedData) {
        this.encryptedData = encrypedData;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
