package com.worldpay.service;

import com.worldpay.config.WorldpayConfig;
import com.worldpay.service.model.MerchantInfo;
import com.worldpay.service.request.AddBackOfficeCodeServiceRequest;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

@UnitTest
public class AddBackOfficeCodeServiceRequestTest {

    private static final WorldpayConfig WORLD_PAY_CONFIG = WorldpayTestConfigHelper.getWorldpayTestConfig();
    private static final String BACK_OFFICE_CODE = "BOC-1234567890";
    private static final String MERCHANT1ECOM = "MERCHANT1ECOM";
    private static final String MERCHANT_PASSWORD = "3l3ph4nt_&_c4st!3";
    private static final MerchantInfo MERCHANT_INFO = new MerchantInfo(MERCHANT1ECOM, MERCHANT_PASSWORD);
    private static final String ORDER_CODE = "orderCode";

    @SuppressWarnings("PMD.MemberScope")
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createAddBackOfficeCodeRequestShouldContainBackOfficeCodeAndWorldpayConfigAndMerchantInfoAndOrderCode() {

        final AddBackOfficeCodeServiceRequest request = AddBackOfficeCodeServiceRequest.createAddBackOfficeCodeRequest(WORLD_PAY_CONFIG, MERCHANT_INFO, ORDER_CODE, BACK_OFFICE_CODE);

        assertEquals(BACK_OFFICE_CODE, request.getBackOfficeCode());
        assertEquals(WORLD_PAY_CONFIG, request.getWorldpayConfig());
        assertEquals(MERCHANT_INFO, request.getMerchantInfo());
        assertEquals(ORDER_CODE, request.getOrderCode());
    }

    @Test
    public void createAddBackOfficeCodeRequestShouldRaiseIllegalArgumentExceptionWhenWorldpayConfigIsNull() {
        expectedException.expect(IllegalArgumentException.class);

        AddBackOfficeCodeServiceRequest.createAddBackOfficeCodeRequest(null, MERCHANT_INFO, ORDER_CODE, BACK_OFFICE_CODE);
    }

    @Test
    public void createAddBackOfficeCodeRequestShouldRaiseIllegalArgumentExceptionWhenMerchantInfoIsNull() {
        expectedException.expect(IllegalArgumentException.class);

        AddBackOfficeCodeServiceRequest.createAddBackOfficeCodeRequest(WORLD_PAY_CONFIG, null, ORDER_CODE, BACK_OFFICE_CODE);
    }

    @Test
    public void createAddBackOfficeCodeRequestShouldRaiseIllegalArgumentExceptionWhenOrderCodeIsNull() {
        expectedException.expect(IllegalArgumentException.class);

        AddBackOfficeCodeServiceRequest.createAddBackOfficeCodeRequest(WORLD_PAY_CONFIG, MERCHANT_INFO, null, BACK_OFFICE_CODE);
    }

    @Test
    public void createAddBackOfficeCodeRequestShouldRaiseIllegalArgumentExceptionWhenBackOfficeCodeIsNull() {
        expectedException.expect(IllegalArgumentException.class);

        AddBackOfficeCodeServiceRequest.createAddBackOfficeCodeRequest(WORLD_PAY_CONFIG, MERCHANT_INFO, ORDER_CODE, null);
    }
}
