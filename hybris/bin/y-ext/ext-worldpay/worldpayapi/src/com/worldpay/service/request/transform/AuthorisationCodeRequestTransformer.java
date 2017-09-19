package com.worldpay.service.request.transform;

import com.worldpay.exception.WorldpayModelTransformationException;
import com.worldpay.internal.model.Authorise;
import com.worldpay.internal.model.Modify;
import com.worldpay.internal.model.OrderModification;
import com.worldpay.internal.model.PaymentService;
import com.worldpay.service.request.AuthorisationCodeServiceRequest;
import com.worldpay.service.request.ServiceRequest;

/**
 * Specific class for transforming an {@link AuthorisationCodeServiceRequest} into a {@link PaymentService} object
 * <p/>
 * <p>The external model objects each know how to transform themselves into an internal model object representation. This class adds the surrounding classes that are required
 * to generate xml in the form:
 * <pre>
 *  &lt;paymentService merchantCode="MYMERCHANT" version="1.4"&gt;
 *      &lt;modify&gt;
 *          &lt;orderModification orderCode="1234"&gt;
 *              &lt;authorise authorisationCode="acbsdf"/&gt;
 *          &lt;/orderModification&gt;
 *      &lt;/modify&gt;
 *  &lt;/paymentService&gt;
 * </pre>
 * </p>
 */
public class AuthorisationCodeRequestTransformer implements ServiceRequestTransformer {

    /**
     * (non-Javadoc)
     *
     * @see com.worldpay.service.request.transform.ServiceRequestTransformer#transform(com.worldpay.service.request.ServiceRequest)
     */
    @Override
    public PaymentService transform(ServiceRequest request) throws WorldpayModelTransformationException {
        if (request == null || request.getMerchantInfo() == null || request.getWorldpayConfig() == null || request.getOrderCode() == null) {
            throw new WorldpayModelTransformationException("Request provided to do the authorisation code is invalid.");
        }
        AuthorisationCodeServiceRequest authorisationCodeRequest = (AuthorisationCodeServiceRequest) request;

        PaymentService paymentService = new PaymentService();
        paymentService.setMerchantCode(request.getMerchantInfo().getMerchantCode());

        paymentService.setVersion(request.getWorldpayConfig().getVersion());

        if (authorisationCodeRequest.getAuthorisationCode() == null) {
            throw new WorldpayModelTransformationException("No authorisation code object to transform on the authorisation code request");
        }
        Modify modify = new Modify();
        OrderModification orderModification = new OrderModification();
        orderModification.setOrderCode(request.getOrderCode());
        Authorise authorise = new Authorise();
        authorise.setAuthorisationCode(authorisationCodeRequest.getAuthorisationCode());
        orderModification.getCancelOrCaptureOrRefundOrRevokeOrAddBackOfficeCodeOrAuthoriseOrIncreaseAuthorisationOrCancelOrRefundOrDefendOrShopperWebformRefundDetailsOrExtendExpiryDateOrCancelRefund().add(authorise);
        modify.getOrderModificationOrBatchModificationOrAccountBatchModificationOrFuturePayAgreementModificationOrPaymentTokenUpdateOrPaymentTokenDelete().add(orderModification);
        paymentService.getSubmitOrModifyOrInquiryOrReplyOrNotifyOrVerify().add(modify);
        return paymentService;
    }
}
